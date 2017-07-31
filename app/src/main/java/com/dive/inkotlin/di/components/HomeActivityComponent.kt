package com.dive.inkotlin.di.components

import com.dive.inkotlin.di.ActivityComponentBuilder
import com.dive.inkotlin.di.ActivityScope
import com.dive.inkotlin.di.modules.ActivityModule
import com.dive.inkotlin.presentation.home.impl.HomeActivity
import dagger.Module
import dagger.Subcomponent


/**
 * Created by olena on 7/24/17.
 */

@ActivityScope
@Subcomponent(modules = arrayOf(HomeActivityComponent.HomeActivityModule::class))
interface HomeActivityComponent : ActivityComponent<HomeActivity> {

    @Subcomponent.Builder
    interface Builder : ActivityComponentBuilder<HomeActivityModule, HomeActivityComponent>

    @Module
    class HomeActivityModule (activity: HomeActivity) : ActivityModule<HomeActivity>(activity)
}
