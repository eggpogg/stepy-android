package me.stepy.app.presentation.view.compornent

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View

class CustomScrollingViewBehavior : AppBarLayout.ScrollingViewBehavior {
    constructor() : super()

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: View?, dependency: View?): Boolean {
        val isAppBarLayout = super.layoutDependsOn(parent, child, dependency)
        return isAppBarLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: View?, dependency: View?): Boolean {
        if (child == null || dependency == null) return super.onDependentViewChanged(parent, child, dependency)
        return super.onDependentViewChanged(parent, child, dependency)
    }
}