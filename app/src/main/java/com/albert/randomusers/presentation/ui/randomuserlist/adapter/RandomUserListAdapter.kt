package com.albert.randomusers.presentation.ui.randomuserlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.albert.randomusers.R
import com.albert.randomusers.presentation.models.RandomUserUIModel
import com.squareup.picasso.Picasso

class RandomUserListAdapter(private var randomUsers: MutableList<RandomUserUIModel>, private val listener: OnItemClickListener) : RecyclerView.Adapter<RandomUserListAdapter.ViewHolder>() {

    // Adapter functions region

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RandomUserListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_random_user_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RandomUserListAdapter.ViewHolder, position: Int) {
        val randomUser = getRandomUser(position)
        holder.randomUserName.text = randomUser.name?.first + " " + randomUser.name?.last
        holder.randomUserEmail.text = randomUser.email
        holder.randomUserPhone.text = randomUser.phone
        Picasso.get().load(randomUser.pictureThumbnail).into(holder.randomUserImage)
        holder.btnDeleteRandomUser.setOnClickListener {
            randomUsers.remove(randomUser)
            listener.onItemDelete(randomUser)
        }

        holder.view.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                listener.onItemClick(randomUser)
            }
        })
    }

    override fun getItemCount(): Int {
        return randomUsers.size
    }

    // end region

    private fun getRandomUser(position: Int): RandomUserUIModel {
        return randomUsers[position]
    }

    fun setRandomUsers(randomUsers: MutableList<RandomUserUIModel>) {
        this.randomUsers = randomUsers
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val randomUserName: TextView = view.findViewById(R.id.tv_random_user_name)
        val randomUserEmail: TextView = view.findViewById(R.id.tv_random_user_email)
        val randomUserPhone: TextView = view.findViewById(R.id.tv_random_user_phone)
        val randomUserImage: ImageView = view.findViewById(R.id.iv_random_user_image)
        val btnDeleteRandomUser: Button = view.findViewById(R.id.btn_delete)
    }

    interface OnItemClickListener {
        fun onItemClick(item: RandomUserUIModel)
        fun onItemDelete(item: RandomUserUIModel)
    }

}