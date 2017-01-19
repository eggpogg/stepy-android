package me.stepy.app.presentation.presenter

import android.util.Log
import me.stepy.app.App
import me.stepy.app.domain.usecase.NotebookListUseCase
import me.stepy.app.presentation.internal.di.PerActivity
import me.stepy.app.presentation.util.LocalData
import me.stepy.app.presentation.view.fragment.compornent.NotebookListLoadData
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

@PerActivity
class NotebookListPresenter @Inject constructor(val noteListsUseCase: NotebookListUseCase) : Presenter {

    private val TAG = NotebookListPresenter::class.java.name
    val subscription = CompositeSubscription()
    private lateinit var notebookListData: NotebookListLoadData
    private var logThrowable: ((throwable: Throwable) -> Unit) = { throwable -> Log.e(TAG, throwable.message) }

    fun setView(notebookListData: NotebookListLoadData) {
        this.notebookListData = notebookListData
    }

    fun execute() {
        checkDevice()
    }

    fun createNotebook(title: String) {
        val _subscription = noteListsUseCase.createNotebook(title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ notebook -> notebookListData.addNotebook(notebook) },
                        logThrowable,
                        { /* do nothing */ })
        subscription.add(_subscription)
    }

    private fun checkDevice() {
        if (LocalData.deviceId.isNotEmpty()) {
            loadNoteList()
            return
        }

        val _subscription = noteListsUseCase.checkDevice()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({ device -> LocalData.deviceId = device.id },
                        logThrowable,
                        { loadNoteList() })
        subscription.add(_subscription)
    }

    private fun loadNoteList() {
        // val action2 = Action2<MutableList<Notebook>, Notebook> { list, note -> list.add(note) }
        // val notebooks = Func0<MutableList<Notebook>> { mutableListOf() }
        val _subscription = noteListsUseCase.loadNoteList(App.userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ notebooks -> notebookListData.appearNotebook(notebooks) },
                        logThrowable,
                        { })
        subscription.add(_subscription)
    }

    override fun onResume() {
    }

    override fun onPause() {
        subscription.clear()
    }

}
