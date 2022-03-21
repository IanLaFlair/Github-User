package com.kls.githubuserian.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kls.githubuserian.adapter.UserAdapter
import com.kls.githubuserian.databinding.FragmentFollowingBinding
import com.kls.githubuserian.model.UserModel
import com.kls.githubuserian.viewModel.FollowingViewModel


class FollowingFragment(private val username: String) : Fragment() {

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private var followingViewModel: FollowingViewModel = FollowingViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        val view = binding.root
        followingViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowingViewModel::class.java)
        followingViewModel.getListFollowing(username)
        followingViewModel.userList.observe(viewLifecycleOwner){listUser ->
            getFollowing(listUser)
        }

        followingViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
        return view
    }

    private fun getFollowing(listUser: ArrayList<UserModel>){
        val adapter = UserAdapter(listUser)
        binding.rvFollowing.adapter = adapter
        binding.rvFollowing.layoutManager = LinearLayoutManager(activity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        binding.pbFollowing.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}