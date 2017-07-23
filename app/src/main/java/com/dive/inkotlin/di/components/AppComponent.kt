package com.dive.inkotlin.di.components

import com.dive.inkotlin.di.ActivityComponentBuilder
import com.dive.inkotlin.di.components.AppComponent.ActivityBindingModule
import com.dive.inkotlin.di.modules.AppModule
import com.dive.inkotlin.di.modules.TestModule
import com.dive.inkotlin.presentation.base.impl.BasePresenter
import com.dive.inkotlin.presentation.home.impl.HomeActivity
import com.dive.inkotlin.semantics.Network
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton


@Component(modules = arrayOf(AppModule::class, TestModule::class, ActivityBindingModule::class))
@Singleton
interface AppComponent {

    fun inject(basePresenter: BasePresenter)

    fun inject(network: Network)

    fun inject(homeActivity: HomeActivity)

    @Module
    abstract class ActivityBindingModule {

//        @Binds
//        @IntoMap
//        abstract fun activityComponentBuilder(impl: ActivityComponent.Builder): ActivityComponentBuilder<*, *>

    }

}
