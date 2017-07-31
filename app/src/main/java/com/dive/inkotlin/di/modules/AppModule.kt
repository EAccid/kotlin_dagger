package com.dive.inkotlin.di.modules

import android.app.Application
import android.content.Context
import com.dive.inkotlin.App
import com.dive.inkotlin.di.ApplicationContext
import com.dive.inkotlin.provider.IInfoManager
import com.dive.inkotlin.provider.IInfoPreferences
import com.dive.inkotlin.provider.info.InfoManager
import com.dive.inkotlin.provider.info.InfoPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(private val appContext: App) {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context {
        return appContext
    }

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return appContext
    }


    @Provides
    @Singleton
    fun provideInfoManager(): IInfoManager {
        return InfoManager()
    }

    @Provides
    @Singleton
    fun provideInfoPreferences(infoManager: InfoManager): IInfoPreferences {
        return InfoPreferences(infoManager)
    }

}



