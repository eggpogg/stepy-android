package me.stepy.app.presentation.view.fragment

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.*
import me.stepy.app.App
import me.stepy.app.R
import me.stepy.app.domain.model.Item
import me.stepy.app.domain.model.Notebook
import me.stepy.app.presentation.internal.di.component.DaggerNoteComponent
import me.stepy.app.presentation.internal.di.component.NoteComponent
import me.stepy.app.presentation.presenter.ItemListPresenter
import me.stepy.app.presentation.view.adapter.ItemListAdapter
import me.stepy.app.presentation.view.compornent.ItemOnTouchCallback
import me.stepy.app.presentation.view.fragment.compornent.ItemListLoadData
import me.stepy.app.presentation.view.ui.ItemListFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx
import javax.inject.Inject

class ItemListFragment : BaseFragment(), ItemListLoadData {
    override fun appearItems(items: MutableList<Item>) {
        adapter.items = items
    }

    override fun addItem(item: Item) {
        adapter.addItemAndRefresh(item)
    }

    private val TAG = ItemListFragment::class.java.name
    private lateinit var noteComponent: NoteComponent
    @Inject lateinit var notebookPresenter: ItemListPresenter
    private lateinit var ui: ItemListFragmentUI
    private lateinit var adapter: ItemListAdapter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ui = ItemListFragmentUI()
        return ui.createView(AnkoContext.Companion.create(ctx, this))
    }

    private var notebook: Notebook? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        initializeInjector()
        notebookPresenter.setView(this)
        adapter = ui.list.adapter as ItemListAdapter

        arguments?.let {
            notebook = Notebook.create(it.getString("notebook"))
        }

        init()
    }

    private fun init() {
        setToolbar()
        // appearAppBar()
        appearItemList()
        // appearCreateItem()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        activity.menuInflater.inflate(R.menu.menu_main, menu)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    private fun initializeInjector() {
        noteComponent = DaggerNoteComponent.builder()
                .activityModule(mainActivity.activityModule)
                .applicationComponent(App.component).build()
        noteComponent.inject(this)
    }

    private fun appearCreateItem() {
//        val toX = createTodoContainer.x
//        val transAnim = ObjectAnimator.ofFloat(createTodoContainer, "translationX", toX, 0F)
//        transAnim.duration = 400
//        transAnim.start()
//        createTodo.isFocusable = true
    }

    private fun appearItemList() {
        val callback = ItemOnTouchCallback(act)
        callback.onSwipeListener = object : ItemOnTouchCallback.OnSwipeListener {
            override fun onSwiped(view: RecyclerView.ViewHolder, direction: Int) {
                //itemAdapter.cleanup()
            }
        }
        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(ui.list)
        ui.list.addItemDecoration(helper)
    }

    private fun appearAppBar() {
        val transAnim = ObjectAnimator.ofFloat(ui.appBar, "translationY", -200F, 0F)
        transAnim.duration = 400

        val fadeAnim = ObjectAnimator.ofFloat(ui.appBar, "alpha", 0F, 1F)
        fadeAnim.duration = 400

        AnimatorSet().run {
            playTogether(listOf(transAnim, fadeAnim))
            start()
        }
    }

    private fun setToolbar() {
        ui.toolbar.run {
            notebook?.let {
                title = it.title
            } ?: let {
                ui.editTitleView.run {
                    visibility = View.VISIBLE
                    isFocusable = true
                    setText("EDIT TEXT")
                }
            }
            setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
            setNavigationOnClickListener { activity.supportFragmentManager.popBackStack() }
            inflateMenu(R.menu.menu_main)
            // val menuTitle = if (baseAc.isLogin) "公開する" else "公開をやめる"
            // toolBar.menu.add(menuTitle)
            setOnMenuItemClickListener { item ->
                if (item.title == "公開する") {
                    // todo update database
                } else if (item.title == "公開をやめる") {
                    // todo remove database
                }
                return@setOnMenuItemClickListener true
            }
        }
    }
}