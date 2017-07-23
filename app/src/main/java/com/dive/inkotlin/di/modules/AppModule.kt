package com.dive.inkotlin.di.modules

import android.app.Application
import android.content.Context
import com.dive.inkotlin.App
import com.dive.inkotlin.di.ApplicationContext
import com.dive.inkotlin.di.components.ActivityComponent
import com.dive.inkotlin.provider.IBalanceManager
import com.dive.inkotlin.provider.IBalancePreferences
import com.dive.inkotlin.provider.balance.BalanceManager
import com.dive.inkotlin.provider.balance.BalancePreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton



@Module(subcomponents = arrayOf(ActivityComponent::class))
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
    fun provideBalanceManager(): IBalanceManager {
        return BalanceManager()
    }

    @Provides
    @Singleton
    fun provideBalancePreferences(balanceManager: BalanceManager): IBalancePreferences {
        return BalancePreferences(balanceManager)
    }

}



