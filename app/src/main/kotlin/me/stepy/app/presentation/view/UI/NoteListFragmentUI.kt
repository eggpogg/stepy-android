package me.stepy.app.presentation.view.ui

import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import me.stepy.app.R
import me.stepy.app.presentation.view.adapter.NoteListAdapter
import me.stepy.app.presentation.view.compornent.CustomScrollingViewBehavior
import me.stepy.app.presentation.view.compornent.DividerItemDecoration
import me.stepy.app.presentation.view.fragment.NoteListFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout


class NoteListFragmentUI : AnkoComponent<NoteListFragment> {

    lateinit var list: RecyclerView
    var createNotebookClickListener: ((View?) -> Unit)? = null
    var swipeRefreshListener: (() -> Unit)? = null

    private val coordinatorId = View.generateViewId()
    private val listId = View.generateViewId()

    override fun createView(ui: AnkoContext<NoteListFragment>): View {
        return with(ui) {
            val createListBtnHeight = dip(42)

            frameLayout {
                coordinatorLayout {
                    id = coordinatorId
                    lparams {
                        width = matchParent
                        height = matchParent
                    }
                    swipeRefreshLayout {
                        id = listId
                        lparams {
                            width = matchParent
                            height = matchParent
                            behavior = CustomScrollingViewBehavior()
                            bottomPadding = createListBtnHeight
                        }
                        onRefresh {
                            swipeRefreshListener?.invoke()
                            isRefreshing = false
                        }
                        recyclerView {
                            lparams(matchParent, matchParent)
                            adapter = NoteListAdapter()
                            layoutManager = LinearLayoutManager(ctx)
                            addItemDecoration(DividerItemDecoration(ctx))
                            list = this@recyclerView
                        }
                    }
                    appBarLayout {
                        lparams {
                            width = matchParent
                            height = dimen(R.dimen.abc_action_bar_default_height_material)
                            scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or
                                    AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
                        }
                        minimumHeight = 0
                        toolbar {
                            lparams {
                                width = matchParent
                                height = dimen(R.dimen.abc_action_bar_default_height_material)
                                backgroundColor = colorInt(R.color.cyan_400)
                            }
                            setTitleTextColor(colorInt(R.color.white))
                        }
                    }
                    frameLayout {
                        layoutParams = CoordinatorLayout.LayoutParams(matchParent, createListBtnHeight).apply {
                            anchorId = listId
                            anchorGravity = Gravity.BOTTOM
                            backgroundColor = colorInt(R.color.white)
                        }
                        textView {
                            gravity = Gravity.CENTER_VERTICAL
                            text = "リストを作成..."
                            lparams {
                                width = matchParent
                                height = matchParent
                                leftPadding = dip(16)
                            }
                            onClick { view -> createNotebookClickListener?.invoke(view) }
                        }
                    }
                }
            }
        }
    }
}
