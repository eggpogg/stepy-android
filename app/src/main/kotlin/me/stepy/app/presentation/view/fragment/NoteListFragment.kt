package me.stepy.app.presentation.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.stepy.app.App
import me.stepy.app.domain.model.Notebook
import me.stepy.app.presentation.internal.di.component.DaggerNoteComponent
import me.stepy.app.presentation.internal.di.component.NoteComponent
import me.stepy.app.presentation.presenter.NotebookListPresenter
import me.stepy.app.presentation.view.adapter.NoteListAdapter
import me.stepy.app.presentation.view.fragment.compornent.NotebookListLoadData
import me.stepy.app.presentation.view.ui.NoteListFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import javax.inject.Inject

class NoteListFragment : BaseFragment(), NotebookListLoadData {

    private val TAG = NoteListFragment::class.java.name
    private lateinit var noteComponent: NoteComponent
    @Inject lateinit var noteListPresenter: NotebookListPresenter
    private lateinit var ui: NoteListFragmentUI
    private lateinit var adapter: NoteListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ui = NoteListFragmentUI()
        return ui.createView(AnkoContext.Companion.create(ctx, this))
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        initializeInjector()
        noteListPresenter.setView(this)
        noteListPresenter.execute()
        setOnKeyEvent()

        adapter = ui.list.adapter as NoteListAdapter
        adapter.notebookClickListener = { notebook -> appearNotebookFragment(notebook) }

        ui.createNotebookClickListener = { mainActivity.fm.openAsAdd(ItemListFragment::class.java) }
        ui.swipeRefreshListener = { noteListPresenter.execute() }
    }

    override fun onResume() {
        super.onResume()
        noteListPresenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        noteListPresenter.onPause()
    }

    override fun appearNotebook(notebooks: MutableList<Notebook>) {
        adapter.notebooks = notebooks
    }

    override fun addNotebook(notebook: Notebook) {
        adapter.addNotebookAndRefresh(notebook)
    }

    override fun showLoading() {
        Log.d(TAG, "hideLoading")
    }

    override fun hideLoading() {
        Log.d(TAG, "hideLoading")
    }

    private fun initializeInjector() {
        noteComponent = DaggerNoteComponent.builder()
                .activityModule(mainActivity.activityModule)
                .applicationComponent(App.component).build()
        noteComponent.inject(this)
    }

    private fun appearNotebookFragment(notebook: Notebook) {
        val arg = Bundle().apply {
            putString("notebook", notebook.toJson())
        }
        mainActivity.fm.openAsAdd(ItemListFragment::class.java, arg)
    }

    private fun setOnKeyEvent() {
        view?.setOnKeyListener({ view, keyCode, keyEvent ->
            if (keyEvent.action != KeyEvent.ACTION_DOWN) return@setOnKeyListener false
            return@setOnKeyListener false
        })
    }

}
