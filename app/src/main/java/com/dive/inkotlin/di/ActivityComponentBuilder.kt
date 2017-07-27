package com.dive.inkotlin.di


interface ActivityComponentBuilder<M, C> {
    fun activityModule(activityModule: M): ActivityComponentBuilder<M, C>
    fun build(): C
}