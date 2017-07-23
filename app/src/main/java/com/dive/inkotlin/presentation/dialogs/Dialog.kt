package com.dive.inkotlin.presentation.dialogs

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.dive.inkotlin.R
import com.dive.inkotlin.di.ActivityScope
import com.dive.inkotlin.presentation.CustomDialog
import com.dive.inkotlin.presentation.base.TEST


@ActivityScope
class Dialog(private val activity: AppCompatActivity) : CustomDialog {

    override fun showDialog(tag: String) {
        val dialog = dialogFragmentByTag(tag)
        dialog?.show()
    }

    private fun dialogFragmentByTag(tag: String): Dialog? {
        when (tag) {
            TEST -> return blowTestDialog()
            else -> return null
        }
    }

    private fun blowTestDialog(): Dialog {
        val wrapInScrollView = true
        val dialog = MaterialDialog.Builder(activity)
                .backgroundColorRes(R.color.colorBackgroundLight)
                .title("TEST")
                .customView(R.layout.dialog_test, wrapInScrollView)
                .build()
        return dialog
    }

}

