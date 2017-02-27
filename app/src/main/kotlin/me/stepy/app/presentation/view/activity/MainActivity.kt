package me.stepy.app.presentation.view.activity

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.KeyEvent
import me.stepy.app.R
import me.stepy.app.presentation.internal.di.module.ActivityModule
import me.stepy.app.presentation.view.fragment.BaseFragmentManager
import me.stepy.app.presentation.view.fragment.NoteListFragment

class MainActivity : FragmentActivity() {

    val activityModule: ActivityModule by lazy { ActivityModule(this) }
    lateinit var fm: BaseFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        fm = BaseFragmentManager(supportFragmentManager, R.id.mainFragment)
        fm.openAsRoot(NoteListFragment::class.java)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode != KeyEvent.KEYCODE_BACK) {
            return super.onKeyDown(keyCode, event)
        }

        val fragmentManager = supportFragmentManager
        val fragmentCount = fragmentManager.backStackEntryCount
        return when {
            fragmentCount == 1 -> {
                this@MainActivity.finish()
                true
            }
            fragmentCount > 1 -> {
                fragmentManager.popBackStack()
                true
            }
            else -> {
                super.onKeyDown(keyCode, event)
            }
        }
    }
}
