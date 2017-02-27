package me.stepy.app.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import me.stepy.app.presentation.view.activity.MainActivity

abstract class BaseFragment : Fragment() {

    val mainActivity: MainActivity
        get() = activity as MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    interface Create<out T : BaseFragment> {
        fun create(arg: Bundle? = null): T
        val TAG: String
    }

    open fun onResumeFromBackStack() {
    }

}
