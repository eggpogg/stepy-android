package me.stepy.app.presentation.internal.di.component

import dagger.Component
import me.stepy.app.presentation.internal.di.PerActivity
import me.stepy.app.presentation.internal.di.module.ActivityModule
import me.stepy.app.presentation.internal.di.module.NoteModule
import me.stepy.app.presentation.view.fragment.ItemListFragment
import me.stepy.app.presentation.view.fragment.NoteListFragment

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class),
        modules = arrayOf(ActivityModule::class, NoteModule::class))
interface NoteComponent : ActivityComponent{
    fun inject(noteListFragment: NoteListFragment)
    fun inject(itemListFragment: ItemListFragment)
}