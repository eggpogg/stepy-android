package me.stepy.app.presentation.presenter

import android.util.Log
import me.stepy.app.App
import me.stepy.app.domain.usecase.ItemListUseCase
import me.stepy.app.presentation.internal.di.PerActivity
import me.stepy.app.presentation.view.fragment.compornent.ItemListLoadData
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

@PerActivity
class ItemListPresenter @Inject constructor(val itemListUseCase: ItemListUseCase) : Presenter {

    private val TAG = NotebookListPresenter::class.java.name
    val subscription = CompositeSubscription()
    private lateinit var itemListLoadData: ItemListLoadData
    private var logThrowable: ((throwable: Throwable) -> Unit) = { throwable -> Log.e(TAG, throwable.message) }

    fun setView(itemListLoadData: ItemListLoadData) {
        this.itemListLoadData = itemListLoadData
    }

    fun execute() {
        loadItem()
    }

    fun createItem(title: String) {
        val _subscription = itemListUseCase.createNotebook(title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ },
                        logThrowable,
                        { /* do nothing */ })
        subscription.add(_subscription)
    }

    private fun loadItem() {
        // val action2 = Action2<MutableList<Notebook>, Notebook> { list, note -> list.add(note) }
        // val notebooks = Func0<MutableList<Notebook>> { mutableListOf() }
        val _subscription = itemListUseCase.loadNoteList(App.userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ },
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
