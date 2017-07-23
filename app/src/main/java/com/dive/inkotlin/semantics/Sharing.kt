package com.dive.inkotlin.semantics

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.support.annotation.DrawableRes
import android.support.v4.app.ShareCompat
import rx.Observable
import java.util.concurrent.TimeUnit

class Sharing {

    /**
     * @return with delay true if app is installed or @param useNativeSharing is set as true,
     * * or return false if app doesn't exist and @param useNativeSharing is set as false
     */
    fun shareNativePost(activity: Activity, appPackage: AppPackage, delayMilliseconds: Int, useNativeSharing: Boolean): Observable<Boolean> {
        var succeed = false
        if (isAppInstalled(activity, appPackage)) {
            if (appPackage == AppPackage.FACEBOOK) {
                val clipboard = activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("DESCRIPTION", appPackage.textDescription)
                clipboard.primaryClip = clip
            }
            startApp(appPackage, activity)
            succeed = true
        } else {
            if (useNativeSharing) {
                startNativeSharing(activity)
                succeed = true
            }
        }
        return Observable.just(succeed).delay((if (succeed) delayMilliseconds else 0).toLong(), TimeUnit.MILLISECONDS)
    }

    /**
     * @return with delay true (uses only native sharing)
     */
    fun shareNativePost(activity: Activity, delayMilliseconds: Int): Observable<Boolean> {
        startNativeSharing(activity)
        return Observable.just(true).delay(delayMilliseconds.toLong(), TimeUnit.MILLISECONDS)
    }

    private fun startApp(appPackage: AppPackage, activity: Activity) {
        if (appPackage.isWithImage) {
            val share = Intent(Intent.ACTION_SEND)
            share.`package` = appPackage.packageDescription
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                share.type = "image/*"
                val b = BitmapFactory.decodeResource(activity.resources, appPackage.imageRes)
                val path = MediaStore.Images.Media.insertImage(activity.contentResolver, b, null, null)
                val imageUri = Uri.parse(path)
                share.putExtra(Intent.EXTRA_STREAM, imageUri)
            } else {
                share.type = "text/plain"
            }
            share.putExtra(Intent.EXTRA_TEXT, appPackage.textDescription)
            activity.startActivity(share)
            return
        }
        val shareIntent = ShareCompat.IntentBuilder.from(activity)
                .setType("text/plain")
                .setText(appPackage.textDescription)
                .intent
                .putExtra(Intent.EXTRA_TEXT, appPackage.textDescription)
                .setPackage(appPackage.packageDescription)
        activity.startActivity(shareIntent)
    }

    private fun startNativeSharing(activity: Activity) {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_TEXT, AppPackage.NATIVE.textDescription)
        val SHARE_HEADER = "Share via"
        activity.startActivity(Intent.createChooser(sharingIntent, SHARE_HEADER))
    }

    private fun isAppInstalled(activity: Activity, appPackage: AppPackage): Boolean {
        return activity.packageManager.getLaunchIntentForPackage(appPackage.packageDescription) != null
    }

    enum class AppPackage constructor(val packageDescription: String, val isWithImage: Boolean) {
        NATIVE("", false),
        GOOGLE_PLUS("com.google.android.apps.plus", false),
        FACEBOOK("com.facebook.katana", false);

        val imageRes: Int
            @DrawableRes
            get() = when (this) {
                else -> throw RuntimeException("You shod define resource for this package!")
            }

        val textDescription: String
            get() = ""
    }

}