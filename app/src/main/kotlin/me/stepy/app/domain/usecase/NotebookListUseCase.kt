package me.stepy.app.domain.usecase

import me.stepy.app.domain.model.Device
import me.stepy.app.domain.model.Notebook
import rx.Observable

interface NotebookListUseCase : UseCase {
    fun checkDevice(): Observable<Device>
    fun loadNoteList(userId: Long): Observable<MutableList<Notebook>>
    fun createNotebook(title: String): Observable<Notebook>
}
