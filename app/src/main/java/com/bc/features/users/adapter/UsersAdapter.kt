package com.bc.features.users.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bc.R
import com.bc.models.User
import com.bc.utils.setOnSafeClickListener

class UsersAdapter(private val listener: Listener) : RecyclerView.Adapter<UsersAdapter.ViewHolder>(){

    lateinit var context: Context
    private var users: List<User> = emptyList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView = view.findViewById(R.id.txtName)
        val txtEmail: TextView = view.findViewById(R.id.txtEmail)
        val txtAddress: TextView = view.findViewById(R.id.txtAddress)
        val txtSingle: TextView = view.findViewById(R.id.txtSingle)
        val cardUser: CardView = view.findViewById(R.id.cardUser)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder  {
        context = viewGroup.context
        return ViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.user_item,
                viewGroup,
                false
            )
        )
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val user = users[position]

        val address = "${user.address?.street}, ${user.address?.suite}, ${user.address?.city}"

        viewHolder.txtName.text = user.name
        viewHolder.txtEmail.text = user.email
        viewHolder.txtAddress.text = address
        viewHolder.txtSingle.text = user.name?.substring(0,1)

        viewHolder.cardUser.setOnSafeClickListener {
            listener.showDetails(user)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = users.size

    fun loadUsers(list : List<User>){
        users = list
        notifyDataSetChanged()
    }

    interface Listener {
        fun showDetails(user: User)
    }

}