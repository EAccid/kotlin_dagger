package com.dive.inkotlin.di.components

import android.support.v7.app.AppCompatActivity
import dagger.MembersInjector

interface ActivityComponent<A : AppCompatActivity> : MembersInjector<A>