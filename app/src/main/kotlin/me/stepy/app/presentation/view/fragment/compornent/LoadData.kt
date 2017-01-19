package me.stepy.app.presentation.view.fragment.compornent

import android.content.Context

interface LoadData {
    fun showLoading()
    fun hideLoading()
    fun getContext(): Context
}
