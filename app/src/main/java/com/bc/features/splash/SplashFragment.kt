package com.bc.features.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bc.R
import com.bc.commons.BaseFragment
import com.bc.commons.BaseViewModel
import com.bc.databinding.FragmentSplashBinding
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment() {

    private val viewModel: SplashViewModel by viewModel()
    lateinit var binding: FragmentSplashBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.users.observe(viewLifecycleOwner, {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToUsersFragment(it.toTypedArray()))
        })

    }

    override fun getViewModel(): BaseViewModel = viewModel

}