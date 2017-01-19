package me.stepy.app.presentation.internal.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import me.stepy.app.App
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: App) {

    @Provides
    @Singleton
    internal fun provideApplicationContext(): Context {
        return this.app
    }
}
