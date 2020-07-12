package com.bruno.teste.core.ui

import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment(){

    private lateinit var fragmentView: View

    fun setFragmentView(view: View){
        fragmentView = view
    }

    fun getFragmentView() = fragmentView
}