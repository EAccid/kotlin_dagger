package com.dive.inkotlin

import android.app.Application
import android.support.v7.app.AppCompatActivity
import com.dive.inkotlin.di.ActivityComponentBuilder
import com.dive.inkotlin.di.HasActivitySubcomponentBuilders
import com.dive.inkotlin.di.components.AppComponent
import com.dive.inkotlin.di.components.DaggerAppComponent
import com.dive.inkotlin.di.modules.AppModule
import com.orhanobut.hawk.Hawk
import javax.inject.Inject
import javax.inject.Provider


private var component: AppComponent? = null

class App : Application() {

    @Inject
    lateinit var activityComponentBuilders: Map<Class<out AppCompatActivity>, Provider<ActivityComponentBuilder<*, *>>>


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

