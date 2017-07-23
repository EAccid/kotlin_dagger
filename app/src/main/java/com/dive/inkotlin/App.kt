package com.dive.inkotlin

import android.app.Application
import com.dive.inkotlin.di.components.AppComponent
import com.dive.inkotlin.di.components.DaggerAppComponent
import com.dive.inkotlin.di.modules.AppModule
import com.orhanobut.hawk.Hawk


private var component: AppComponent? = null

class App : Application() {

    companion object {
        @JvmStatic fun getComponent(): AppComponent {
            return component!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        createGlobalConfigurations()
        buildComponent()
    }

    private fun createGlobalConfigurations() {
        Hawk.init(this).build()
    }

    private fun buildComponent() {
        if (component == null) {
            component = DaggerAppComponent.builder()
                    .appModule(AppModule(this))
                    .build()
        }
    }

}

