package com.dive.inkotlin.di.modules

import android.support.v7.app.AppCompatActivity
import com.dive.inkotlin.di.ActivityScope
import com.dive.inkotlin.presentation.CustomDialog
import com.dive.inkotlin.presentation.dialogs.Dialog
import dagger.Module
import dagger.Provides



@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityScope
    fun provideCustomDialog(): CustomDialog {
        return Dialog(activity)
    }

}