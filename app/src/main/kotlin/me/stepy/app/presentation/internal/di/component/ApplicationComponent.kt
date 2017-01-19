package me.stepy.app.presentation.internal.di.component

import android.content.Context
import dagger.Component
import me.stepy.app.App
import me.stepy.app.presentation.internal.di.module.ApplicationModule
import me.stepy.app.presentation.view.activity.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(app: App)
    fun inject(mainActivity: MainActivity)
    fun context(): Context
}
