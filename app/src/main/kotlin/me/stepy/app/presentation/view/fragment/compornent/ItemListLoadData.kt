package me.stepy.app.presentation.view.fragment.compornent

import me.stepy.app.domain.model.Item
import me.stepy.app.domain.model.Notebook

interface ItemListLoadData : LoadData {
    fun appearItems(items: MutableList<Item>)
    fun addItem(item: Item)
}
