package com.example.boardofmessageapp_kt2.base

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onDestroy() {
        super.onDestroy()
    }

    protected fun setActionBarTitle(title: String) {
        activity?.title = title
    }
}