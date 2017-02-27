@file:Suppress("NOTHING_TO_INLINE")
package me.stepy.app.presentation.view.ui

import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.View

inline fun View.colorInt(colorInt: Int): Int = ContextCompat.getColor(context, colorInt)

inline fun View.attribute(value: Int): TypedValue {
    val ret = TypedValue()
    context.theme.resolveAttribute(value, ret, true)
    return ret
}

inline fun View.attrAsDimen(value: Int): Int {
    return TypedValue.complexToDimensionPixelSize(attribute(value).data, resources.displayMetrics)
}
