package com.dive.inkotlin.di

import android.support.v7.app.AppCompatActivity


/**
 * Created by olena on 7/24/17.
 */

interface HasActivitySubcomponentBuilders {
    fun getActivityComponentBuilder(activityClass: Class<out AppCompatActivity>): ActivityComponentBuilder<*, *>
}