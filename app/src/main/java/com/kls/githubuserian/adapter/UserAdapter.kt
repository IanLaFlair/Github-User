package com.kls.githubuserian.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kls.githubuserian.DetailActivity
import com.kls.githubuserian.R
import com.kls.githubuserian.model.UserModel
import java.util.*

class UserAdapter(private val listUser: ArrayList<UserModel>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listUser.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (id, username, avatar, url_github) = listUser[position]
        holder.txtname.text = username
        holder.txtvisit.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Hola $id", Toast.LENGTH_SHORT).show()
        }
        Glide.with(holder.itemView.context)
            .load(avatar)
            .circleCrop()
            .into(holder.imgUser)
        val user = UserModel(
            id,
            username,
            avatar,
            url_github
        )
        holder.itemView.setOnClickListener{
            val moveWithObjectIntent = Intent(holder.itemView.context, DetailActivity::class.java)
            moveWithObjectIntent.putExtra(DetailActivity.EXTRA_USER, user)
            holder.itemView.context.startActivity(moveWithObjectIntent)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtname: TextView = itemView.findViewById(R.id.txt_name)
        var imgUser: ImageView = itemView.findViewById(R.id.img_avatar)
        var txtvisit: TextView = itemView.findViewById(R.id.txt_visit)

    }

}