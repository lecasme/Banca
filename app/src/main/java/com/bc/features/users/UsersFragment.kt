package com.bc.features.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bc.commons.BaseFragment
import com.bc.commons.BaseViewModel
import com.bc.databinding.FragmentUsersBinding
import com.bc.features.users.adapter.UsersAdapter
import com.bc.models.User
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class UsersFragment : BaseFragment(), UsersAdapter.Listener {

    private val viewModel: UsersViewModel by viewModel()
    lateinit var binding: FragmentUsersBinding
    private var pictureAdapter: UsersAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pictureAdapter = UsersAdapter(this)
        binding.rcvUsers.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = pictureAdapter
        }

        arguments?.let { args ->
            val users = UsersFragmentArgs.fromBundle(args).users
            users?.toList()?.let {
                pictureAdapter?.loadUsers(it)
            }
        }

        val date = Calendar.getInstance().time
        val dateFormat: DateFormat = SimpleDateFormat("EEEE, dd MMM yyyy", Locale.ENGLISH)
        val strDate: String = dateFormat.format(date)

        binding.txtDay.text = strDate

    }

    override fun getViewModel(): BaseViewModel = viewModel

    override fun showDetails(user: User) {
        findNavController().navigate(
            UsersFragmentDirections.actionUsersFragmentToDetailFragment(
                user
            )
        )
    }
}