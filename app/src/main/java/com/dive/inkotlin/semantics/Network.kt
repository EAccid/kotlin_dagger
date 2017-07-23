package com.dive.inkotlin.semantics

import android.content.Context
import android.net.ConnectivityManager
import com.dive.inkotlin.App
import com.dive.inkotlin.di.ApplicationContext
import javax.inject.Inject

class Network {

    @Inject
    @field:[ApplicationContext] lateinit var context: Context

    init {
        App.getComponent().inject(this)
    }

    fun isNetworkAvailable(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}

