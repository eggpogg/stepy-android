package me.stepy.app.presentation.view.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import me.stepy.app.R
import me.stepy.app.domain.model.Item
import me.stepy.app.presentation.view.ui.colorInt
import org.jetbrains.anko.*

class ItemViewHolder(box: View) : RecyclerView.ViewHolder(box) {
    var actionView = box.findViewById(R.id.action) as TextView
    var createdAtView: TextView = box.findViewById(R.id.createdAt) as TextView

    fun bind(item: Item, position: Int) {
        itemView.tag = item
        actionView.text = item.title
        createdAtView.text = "0"
    }

    companion object {
        val actionViewId = View.generateViewId()
        val createdViewId = View.generateViewId()

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
                                gravity = Gravity.CENTER_VERTICAL
                                setPadding(dip(16), 0, dip(16), 0)
                                imageResource = R.drawable.ic_done_white_24dp
                            }
                        }
                    }
                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL
                        lparams {
                            width = matchParent
                            height = matchParent
                            backgroundResource = R.drawable.item_bg
                            padding = dip(16)
                        }
                        textView {
                            id = actionViewId
                            lparams {
                                weight = 1F
                                width = dip(0)
                                height = wrapContent
                                textColor = colorInt(R.color.gray_deep)
                            }
                        }
                        textView {
                            id = createdViewId
                        }
                    }
                }
            }
        }
    }
}