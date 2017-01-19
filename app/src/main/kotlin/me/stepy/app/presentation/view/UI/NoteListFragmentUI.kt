package me.stepy.app.presentation.view.ui

import android.content.res.ColorStateList
import android.support.design.widget.AppBarLayout
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import me.stepy.app.R
import me.stepy.app.domain.model.Notebook
import me.stepy.app.presentation.view.adapter.NoteListAdapter
import me.stepy.app.presentation.view.compornent.CustomScrollingViewBehavior
import me.stepy.app.presentation.view.compornent.DividerItemDecoration
import me.stepy.app.presentation.view.fragment.NoteListFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class NoteListFragmentUI : AnkoComponent<NoteListFragment> {

    lateinit var list: RecyclerView
    var fabClickListener: ((View?) -> Unit)? = null
    var swipeRefreshListener: (() -> Unit)? = null

    override fun createView(ui: AnkoContext<NoteListFragment>): View {
        return with(ui) {
            verticalLayout {
                coordinatorLayout {
                    lparams(matchParent, matchParent)
                    swipeRefreshLayout {
                        lparams {
                            width = matchParent
                            height = matchParent
                            behavior = CustomScrollingViewBehavior()
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
                    floatingActionButton {
                        lparams {
                            width = wrapContent
                            height = wrapContent
                            margin = dip(16)
                            size = FloatingActionButton.SIZE_NORMAL
                            gravity = Gravity.BOTTOM or Gravity.END
                            imageResource = R.drawable.ic_add_white_18dp
                        }
                        backgroundTintList = ColorStateList.valueOf(R.color.primary)
                        onClick { view -> fabClickListener?.invoke(view) }
                    }
                }
            }
        }
    }
}
