package com.dive.inkotlin.di.modules

import com.dive.inkotlin.di.ActivityComponentBuilder
import com.dive.inkotlin.di.ActivityKey
import com.dive.inkotlin.di.components.HomeActivityComponent
import com.dive.inkotlin.di.components.HomeActivityModule
import com.dive.inkotlin.presentation.home.impl.HomeActivity
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


/**
 * Created by olena on 7/24/17.
 */

@Module(subcomponents = arrayOf(HomeActivityComponent::class))
abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(HomeActivity::class)
    abstract fun mainActivityComponentBuilder(impl: HomeActivityComponent.Builder):
            ActivityComponentBuilder<HomeActivityModule, HomeActivityComponent>

}

