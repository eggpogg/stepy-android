@file:Suppress("NOTHING_TO_INLINE")
package me.stepy.app.presentation.view.ui

import android.support.v4.content.ContextCompat
import android.view.View

inline fun View.colorInt(colorInt: Int): Int = ContextCompat.getColor(context, colorInt)
