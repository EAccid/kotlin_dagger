package com.dive.inkotlin.di.components

import com.dive.inkotlin.di.ActivityComponentBuilder
import com.dive.inkotlin.di.ActivityScope
import com.dive.inkotlin.di.modules.ActivityModule
import com.dive.inkotlin.presentation.base.impl.BaseActivity
import dagger.Subcomponent


@ActivityScope
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    @Subcomponent.Builder
    interface Builder : ActivityComponentBuilder<ActivityModule, AppComponent>


    fun inject(baseActivity: BaseActivity)
}