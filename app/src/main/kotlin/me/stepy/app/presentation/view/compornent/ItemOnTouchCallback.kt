package me.stepy.app.presentation.view.compornent

import android.app.Activity
import android.graphics.Canvas
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

class ItemOnTouchCallback(val activtiy: Activity) : ItemTouchHelper.Callback() {

    var onSwipeListener: OnSwipeListener? = null

    interface OnSwipeListener {
        fun onSwiped(view: RecyclerView.ViewHolder, direction: Int)
    }

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val type = viewHolder.itemViewType

        return makeMovementFlags(0, ItemTouchHelper.END)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val type = viewHolder.itemViewType

        if (direction == ItemTouchHelper.END) {
            viewHolder.itemView?.tag ?: return

            var id = viewHolder.itemView.tag
            if (id !is String) return

        }

    }

    override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        if (viewHolder != null) {
            val type = viewHolder.itemViewType
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}