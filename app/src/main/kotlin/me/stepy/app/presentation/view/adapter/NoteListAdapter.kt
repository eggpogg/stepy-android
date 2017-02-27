package me.stepy.app.presentation.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import me.stepy.app.domain.model.Notebook
import me.stepy.app.presentation.view.adapter.holder.NoteViewHolder

class NoteListAdapter : RecyclerView.Adapter<NoteViewHolder>() {
    var notebookClickListener: ((Notebook) -> Unit)? = null

    var notebooks: MutableList<Notebook> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: NoteViewHolder?, position: Int) {
        val notebook = notebooks[position]
        holder?.bind(notebook, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NoteViewHolder? {
        parent ?: return null
        return NoteViewHolder(NoteViewHolder.createView(parent)).apply {
            itemView.setOnClickListener { view ->
                val notebook = view.tag as Notebook
                notebookClickListener?.invoke(notebook)
            }
        }
    }

    override fun getItemCount(): Int {
        return notebooks.size
    }

    fun addNotebookAndRefresh(notebook: Notebook) {
        notebooks.add(notebook)
        notifyItemInserted(notebooks.size)
    }

}