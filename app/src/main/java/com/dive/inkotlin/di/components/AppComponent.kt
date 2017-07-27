package com.dive.inkotlin.di.components

import com.dive.inkotlin.di.modules.ActivityBindingModule
import com.dive.inkotlin.di.modules.AppModule
import com.dive.inkotlin.di.modules.TestModule
import com.dive.inkotlin.presentation.base.impl.BasePresenter
import com.dive.inkotlin.presentation.home.impl.HomeActivity
import com.dive.inkotlin.semantics.Network
import dagger.Component
import javax.inject.Singleton


@Component(modules = arrayOf(AppModule::class, TestModule::class, ActivityBindingModule::class))
@Singleton
interface AppComponent {

    fun inject(basePresenter: BasePresenter)

    fun inject(network: Network)

    fun inject(homeActivity: HomeActivity)

}
