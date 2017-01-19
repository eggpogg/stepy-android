package me.stepy.app.presentation.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import me.stepy.app.domain.model.Item
import me.stepy.app.presentation.view.adapter.holder.ItemViewHolder
import me.stepy.app.presentation.view.fragment.ItemListFragment


class ItemListAdapter: RecyclerView.Adapter<ItemViewHolder>() {
    var items: MutableList<Item> = mutableListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder? {
        parent ?: return null
        val view = ItemViewHolder.createView(parent)
        return ItemViewHolder(view)
    }

    fun addItemAndRefresh(item: Item) {
        items.add(item)
        notifyItemInserted(items.size)
    }
}