package com.techugo.bottomnavigation.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.techugo.sidemenu.BaseFragment
import com.techugo.sidemenu.R

/**
 * A simple [Fragment] subclass.
 */
class Fragment1 : BaseFragment() {

    lateinit var callback: OnNotifyRelodPage

    fun setOnHeadlineSelectedListener(callback: OnNotifyRelodPage) {
        this.callback = callback
    }

    // This interface can be implemented by the Activity, parent Fragment,
    // or a separate test implementation.
    interface OnNotifyRelodPage {
        fun reloadPage(position: Int)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment1, container, false)
    }


}
