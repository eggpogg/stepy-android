package me.stepy.app.presentation.internal.di.module

import dagger.Module
import dagger.Provides
import me.stepy.app.domain.usecase.ItemListUseCase
import me.stepy.app.domain.usecase.ItemListUseCaseImpl
import me.stepy.app.domain.usecase.NotebookListUseCase
import me.stepy.app.domain.usecase.NotebookListUseCaseImpl
import me.stepy.app.presentation.internal.di.PerActivity

@Module
class NoteModule {

    @Provides
    @PerActivity
    fun provideNoteListUseCase(notebookListUseCase: NotebookListUseCaseImpl): NotebookListUseCase = notebookListUseCase

    @Provides
    @PerActivity
    fun provideItemListUseCase(itemListsUseCase: ItemListUseCaseImpl): ItemListUseCase = itemListsUseCase

}