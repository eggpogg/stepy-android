package me.stepy.app.presentation.internal.di.module

import android.app.Activity

import dagger.Module
import dagger.Provides
import me.stepy.app.presentation.internal.di.PerActivity

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @PerActivity
    internal fun activity(): Activity {
        return this.activity
    }
}
