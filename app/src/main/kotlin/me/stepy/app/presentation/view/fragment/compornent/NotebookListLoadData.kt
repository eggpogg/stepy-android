package me.stepy.app.presentation.view.fragment.compornent

import me.stepy.app.domain.model.Notebook

interface NotebookListLoadData : LoadData {
    fun appearNotebook(notebooks: MutableList<Notebook>)
    fun addNotebook(notebook: Notebook)
}
