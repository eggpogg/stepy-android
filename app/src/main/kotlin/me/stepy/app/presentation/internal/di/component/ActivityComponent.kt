package me.stepy.app.presentation.internal.di.component

import android.app.Activity
import dagger.Component
import me.stepy.app.presentation.internal.di.PerActivity
import me.stepy.app.presentation.internal.di.module.ActivityModule

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun activity(): Activity
}
