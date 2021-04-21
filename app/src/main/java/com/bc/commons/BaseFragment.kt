package com.bc.commons

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bc.utils.getSoftInputMode
import com.bc.utils.setupSnackbar
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment: Fragment() {

    private var originalMode: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        originalMode = activity?.window?.getSoftInputMode()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupSnackbar(viewLifecycleOwner, getViewModel().snackBar, Snackbar.LENGTH_LONG)
    }

    override fun onDestroy() {
        super.onDestroy()
        originalMode?.let { activity?.window?.setSoftInputMode(it) }
    }

    abstract fun getViewModel(): BaseViewModel

}