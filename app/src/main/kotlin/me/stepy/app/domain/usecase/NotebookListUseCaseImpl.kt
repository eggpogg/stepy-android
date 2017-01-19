package me.stepy.app.domain.usecase

import me.stepy.app.data.api.DeviceApiFactory
import me.stepy.app.data.api.NoteApiFactory
import me.stepy.app.domain.model.Device
import me.stepy.app.domain.model.Notebook
import rx.Observable
import javax.inject.Inject

class NotebookListUseCaseImpl @Inject constructor() : NotebookListUseCase {
    private val TAG = NotebookListUseCaseImpl::class.java.name
    private var userId: Long = 0

    override fun checkDevice(): Observable<Device> {
        return DeviceApiFactory.create().call().flatMap({ response ->
            Observable.from(arrayOf(response.body))
        })
    }

    override fun loadNoteList(userId: Long): Observable<MutableList<Notebook>> {
        if (userId < 0) throw IllegalArgumentException("invalid userId.")
        this.userId = userId

        return NoteApiFactory.create().call().flatMap { response ->
            Observable.from(mutableListOf(response.body))
        }
    }

    override fun createNotebook(title: String): Observable<Notebook> {
        if (title.isEmpty()) throw IllegalArgumentException("invalid title")
        return NoteApiFactory.create().create(title = title).flatMap { response ->
            Observable.from(arrayOf(response.body))
        }
    }
}