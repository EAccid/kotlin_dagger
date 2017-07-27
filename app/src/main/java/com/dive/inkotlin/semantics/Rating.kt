package com.dive.inkotlin.semantics

import android.app.Activity
import android.content.Intent
import android.net.Uri
import rx.Observable
import java.util.concurrent.TimeUnit


class Rating {

    fun rate(activity: Activity, milliSeconds: Long): Observable<Boolean> {
        val appPackageName = activity.packageName
        val intentFromApp: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName))
        val intentFromWeb: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName))
        try {
            activity.startActivity(intentFromApp)
        } catch (anfe: android.content.ActivityNotFoundException) {
            activity.startActivity(intentFromWeb)
        }
        return Observable.just(true).delay(milliSeconds, TimeUnit.MILLISECONDS)
    }

    fun sendFeedback(activity: Activity, vararg email: String, subject: String, text: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "plain/text"
        intent.putExtra(Intent.EXTRA_EMAIL, email)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, text)
        activity.startActivity(Intent.createChooser(intent, "Send"))
    }

}
