package me.stepy.app.domain.usecase

import me.stepy.app.domain.model.Device
import me.stepy.app.domain.model.Notebook
import rx.Observable
import javax.inject.Inject

class ItemListUseCaseImpl @Inject constructor() : ItemListUseCase {
    override fun checkDevice(): Observable<Device> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadNoteList(userId: Long): Observable<MutableList<Notebook>> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createNotebook(title: String): Observable<Notebook> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}