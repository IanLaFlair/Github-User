package com.kls.githubuserian.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kls.githubuserian.adapter.UserAdapter
import com.kls.githubuserian.databinding.FragmentFollowerBinding
import com.kls.githubuserian.model.UserModel
import com.kls.githubuserian.viewModel.FollowersViewModel

class FollowerFragment (private val username: String): Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!
    private var followersViewModel: FollowersViewModel = FollowersViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        val view = binding.root
        followersViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowersViewModel::class.java)
        followersViewModel.getListFollower(username)
        followersViewModel.userList.observe(viewLifecycleOwner){listUser ->
            getFollowers(listUser)
        }

        followersViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        return view
    }

    private fun getFollowers(listUser: ArrayList<UserModel>){
        val adapter = UserAdapter(listUser)
        binding.rvFollower.adapter = adapter
        binding.rvFollower.layoutManager = LinearLayoutManager(activity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbFollower.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


}