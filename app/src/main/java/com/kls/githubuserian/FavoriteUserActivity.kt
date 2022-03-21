package com.kls.githubuserian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kls.githubuserian.adapter.FavoriteUserAdapter
import com.kls.githubuserian.databinding.ActivityFavoriteUserBinding
import com.kls.githubuserian.viewModel.UserFavoriteViewModel
import com.kls.githubuserian.viewModel.ViewModelFactory

class FavoriteUserActivity : AppCompatActivity() {

    private var _activityFavoriteUserBinding: ActivityFavoriteUserBinding? = null
    private val binding get() = _activityFavoriteUserBinding
    private lateinit var adapter: FavoriteUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_user)

        _activityFavoriteUserBinding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val favoriteViewModel = obtainViewModel(this)
        favoriteViewModel.getAllNotes().observe(this) { noteList ->
            if (noteList != null) {
                adapter.setListNotes(noteList)
            }
        }

        adapter = FavoriteUserAdapter()
        binding?.rvUserFav?.layoutManager = LinearLayoutManager(this)
        binding?.rvUserFav?.setHasFixedSize(true)
        binding?.rvUserFav?.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityFavoriteUserBinding = null
    }

    private fun obtainViewModel(activity: AppCompatActivity): UserFavoriteViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(UserFavoriteViewModel::class.java)
    }

}