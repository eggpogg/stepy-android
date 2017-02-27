package me.stepy.app.presentation.view.ui

import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_PIN
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import android.widget.EditText
import me.stepy.app.R
import me.stepy.app.domain.model.Notebook
import me.stepy.app.presentation.view.adapter.ItemListAdapter
import me.stepy.app.presentation.view.compornent.CustomScrollingViewBehavior
import me.stepy.app.presentation.view.compornent.DividerItemDecoration
import me.stepy.app.presentation.view.fragment.ItemListFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.collapsingToolbarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class ItemListFragmentUI : AnkoComponent<ItemListFragment> {

    lateinit var list: RecyclerView
    lateinit var toolbar: Toolbar
    lateinit var editTitleView: EditText
    lateinit var appBar: AppBarLayout
    var swipeRefreshListener: (() -> Unit)? = null
    var notebookClickListener: ((Notebook) -> Unit)? = null

    override fun createView(ui: AnkoContext<ItemListFragment>): View {
        return with(ui) {
            coordinatorLayout {
                lparams {
                    width = matchParent
                    height = matchParent
                    backgroundColor = colorInt(R.color.white)
                }
                appBarLayout {
                    lparams {
                        width = matchParent
                        height = dip(200)
                        minimumHeight = attrAsDimen(R.attr.actionBarSize)
                        backgroundColor = colorInt(R.color.cyan_400)
                    }
                    appBar = this
                    collapsingToolbarLayout {
                        lparams {
                            width = matchParent
                            height = matchParent
                            expandedTitleGravity = Gravity.CENTER
                            setContentScrimColor(colorInt(R.color.cyan_400))
                            if (this is AppBarLayout.LayoutParams) {
                                scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or
                                        AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
                            }
                        }
                        toolbar {
                            val _height = attrAsDimen(R.attr.actionBarSize)
                            layoutParams = CollapsingToolbarLayout.LayoutParams(matchParent, _height).apply {
                                backgroundColor = colorInt(R.color.cyan_400)
                                minimumHeight = attrAsDimen(R.attr.actionBarSize)
                                collapseMode = COLLAPSE_MODE_PIN
                                setTitleTextColor(colorInt(R.color.white))
                            }
                            toolbar = this
                            editText {
                                val _height = attrAsDimen(R.attr.actionBarSize)
                                visibility = View.GONE
                                layoutParams = CollapsingToolbarLayout.LayoutParams(matchParent, _height).apply {
                                    backgroundColor = colorInt(R.color.cyan_400)
                                    minimumHeight = attrAsDimen(R.attr.actionBarSize)
                                    collapseMode = COLLAPSE_MODE_PIN
                                }
                                editTitleView = this
                            }
                        }
                    }
                }
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
                        lparams {
                            width = matchParent
                            height = matchParent
                        }
                        adapter = ItemListAdapter().apply {
                            notebookClickListener = this@ItemListFragmentUI.notebookClickListener
                        }
                        layoutManager = LinearLayoutManager(ctx)
                        addItemDecoration(DividerItemDecoration(ctx))
                        list = this
                    }
                }
            }
        }
    }
}
