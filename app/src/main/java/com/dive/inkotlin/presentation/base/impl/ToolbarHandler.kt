package com.dive.inkotlin.presentation.base.impl

import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.view.View
import com.dive.inkotlin.R


fun setupToolbar(toolbar: Toolbar?, isHomeEnabled: Boolean, title: String) {
    if (isHomeEnabled) {
        fun settingsVisibility(): Int {
            if (isHomeEnabled)
                return View.GONE
            else
                return View.VISIBLE
        }
        toolbar?.setNavigationIcon(R.drawable.ic_keyboard_arrow_left)
        toolbar?.findViewById(R.id.settings)?.visibility = settingsVisibility()
    }
    if (!title.isEmpty())
        toolbar?.title = title
    toolbar?.setTitleTextColor(ContextCompat.getColor(toolbar.context, R.color.text_color_primary))
}

