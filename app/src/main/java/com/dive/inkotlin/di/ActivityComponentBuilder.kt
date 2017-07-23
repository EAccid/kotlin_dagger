package com.dive.inkotlin.di



interface ActivityComponentBuilder<ActivityModule, ActivityComponent> {
    fun activityModule(activityModule: ActivityModule): ActivityComponentBuilder<ActivityModule, ActivityComponent>
    fun build(): ActivityComponent
}