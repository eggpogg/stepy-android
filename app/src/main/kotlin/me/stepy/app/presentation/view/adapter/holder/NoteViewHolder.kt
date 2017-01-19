package me.stepy.app.presentation.view.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout.HORIZONTAL
import android.widget.TextView
import me.stepy.app.R
import me.stepy.app.domain.model.Notebook
import me.stepy.app.presentation.view.ui.colorInt
import org.jetbrains.anko.*

class NoteViewHolder(box: View) : RecyclerView.ViewHolder(box) {

    val listName: TextView = box.findViewById(notebookNameId) as TextView
    val listChildCount: TextView = box.findViewById(notebookChildCountId) as TextView

    fun bind(notebook: Notebook, position: Int) {
        itemView.tag = notebook
        listName.text = notebook.title
        //listChildCount.text = notebook.items.size.toString()
    }

    companion object {
        val notebookNameId = View.generateViewId()
        val notebookChildCountId = View.generateViewId()
        fun createView(parent: View): View {
            return with(parent.context) {
                frameLayout {
                    lparams {
                        width = matchParent
                        height = wrapContent
                        minimumHeight = dip(48)
                    }
                    frameLayout {
                        lparams {
                            width = matchParent
                            height = matchParent
                            backgroundColor = colorInt(R.color.black)
                        }
                        imageView {
                            lparams {
                                width = wrapContent
                                height = wrapContent
                                gravity = Gravity.CENTER_VERTICAL
                                setPadding(dip(16), 0, dip(16), 0)
                                imageResource = R.drawable.ic_done_white_24dp
                            }
                        }
                    }
                }
                linearLayout {
                    lparams {
                        orientation = HORIZONTAL
                        width = matchParent
                        height = wrapContent
                        backgroundResource = R.drawable.item_bg
                    }
                    textView {
                        id = notebookNameId
                        lparams {
                            weight = 1F
                            width = dip(0)
                            height = wrapContent
                            backgroundResource = R.drawable.bg_list
                            setPadding(dip(16), dip(8), dip(16), dip(8))
                        }
                        gravity = Gravity.CENTER_VERTICAL
                    }
                    textView {
                        id = notebookChildCountId
                        lparams {
                            width = dip(48)
                            height = matchParent
                            text = "0"
                            backgroundResource = R.drawable.bg_list
                        }
                        gravity = Gravity.CENTER
                    }
                }
            }
        }
    }
}
