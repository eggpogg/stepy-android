package me.stepy.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.inputmethod.InputMethodManager
import me.stepy.app.presentation.internal.di.component.ApplicationComponent
import me.stepy.app.presentation.internal.di.component.DaggerApplicationComponent
import me.stepy.app.presentation.internal.di.module.ApplicationModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        injectApp()
        App.context = this
    }

    fun injectApp() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    companion object {
        lateinit var context: Application
        lateinit var component: ApplicationComponent

        fun getColor(id: Int) = ContextCompat.getColor(context, id)
        val userId: Long = 1

        fun hideKeyboard(activity: Activity) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(activity.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }

        fun showKeyboard(focusView: View, activity: Activity) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(focusView, 0)
        }

    }

}