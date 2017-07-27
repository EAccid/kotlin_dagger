package com.dive.inkotlin.di

import android.app.Activity
import dagger.MapKey
import kotlin.reflect.KClass


/**
 * Created by olena on 7/24/17.
 */
@MapKey
annotation class ActivityKey(val value: KClass<out Activity>)