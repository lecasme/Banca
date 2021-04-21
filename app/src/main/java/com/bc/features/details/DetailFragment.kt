package com.bc.features.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bc.R
import com.bc.commons.BaseFragment
import com.bc.commons.BaseViewModel
import com.bc.databinding.FragmentDetailBinding
import com.bc.databinding.FragmentSplashBinding
import com.bc.features.splash.SplashFragmentDirections
import com.bc.features.splash.SplashViewModel
import com.bc.features.users.UsersFragmentArgs
import com.bc.utils.setOnSafeClickListener
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment() {

    private val viewModel: DetailViewModel by viewModel()
    lateinit var binding: FragmentDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lnlBack.setOnSafeClickListener {
            findNavController().navigateUp()
        }

        arguments?.let { args ->

            val user = DetailFragmentArgs.fromBundle(args).user
            val address = "${user.address?.street}, ${user.address?.suite}, ${user.address?.city}"
            val company = "${user.company?.name}, ${user.company?.catchPhrase}, ${user.company?.bs}"

            binding.txtName.text = user.name
            binding.txtEmail.text = user.email
            binding.txtAddress.text = address
            binding.txtSingle.text = user.name?.substring(0,1)

            binding.txtUsername.text = user.username
            binding.txtPhone.text = user.phone
            binding.txtWebsite.text = user.website
            binding.txtCompany.text = company

        }

    }

    override fun getViewModel(): BaseViewModel = viewModel

}