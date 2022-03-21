package com.kls.githubuserian.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View.INVISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kls.githubuserian.DetailActivity
import com.kls.githubuserian.database.UserDatabase
import com.kls.githubuserian.databinding.UserItemBinding
import com.kls.githubuserian.model.UserModel
import com.kls.githubuserian.utils.UserDiffCallback

class FavoriteUserAdapter: RecyclerView.Adapter<FavoriteUserViewHolder>() {
    private val listUser = ArrayList<UserDatabase>()
    fun setListNotes(listUsers: List<UserDatabase>) {
        val diffCallback = UserDiffCallback(this.listUser, listUsers)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listUser.clear()
        this.listUser.addAll(listUsers)
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteUserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteUserViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int {
        return listUser.size
    }
}

class FavoriteUserViewHolder(private val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(database: UserDatabase){
        with(binding){
            txtName.text = database.username
            val user = UserModel(
                database.id,
                database.username,
                database.avatar,
                ""
            )
            txtVisit.visibility = INVISIBLE
            root.setOnClickListener {
                val moveWithObjectIntent = Intent(root.context, DetailActivity::class.java)
                moveWithObjectIntent.putExtra(DetailActivity.EXTRA_USER, user)
                binding.root.context.startActivity(moveWithObjectIntent)
            }
            Glide.with(binding.root.context)
                .load(database.avatar)
                .circleCrop()
                .into(imgAvatar)
        }
    }
}
