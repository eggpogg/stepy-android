package me.stepy.app.presentation.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import me.stepy.app.domain.model.Item
import me.stepy.app.presentation.view.adapter.holder.ItemViewHolder


class ItemListAdapter : RecyclerView.Adapter<ItemViewHolder>() {
    var items: MutableList<Item> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {
        holder ?: return

        var item: Item? = null
        if (position - 1 > 0) {
            item = items[position - 1]
        }

        holder.bind(item, position)
    }

    override fun getItemCount(): Int {
        return items.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            (itemCount - 1) -> ItemViewHolder.TYPE_ITEM_CREATE
            else -> ItemViewHolder.TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder? {
        parent ?: return null
        return ItemViewHolder(ItemViewHolder.createView(parent, viewType))
    }

    fun addItemAndRefresh(item: Item) {
        items.add(item)
        notifyItemInserted(items.size)
    }
}