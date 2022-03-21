package com.kls.githubuserian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.kls.githubuserian.adapter.SectionPagerAdapter
import com.kls.githubuserian.database.UserDatabase
import com.kls.githubuserian.databinding.ActivityDetailBinding
import com.kls.githubuserian.model.UserDetailResponse
import com.kls.githubuserian.model.UserModel
import com.kls.githubuserian.viewModel.UserAddUpdateViewModel
import com.kls.githubuserian.viewModel.UserDetailViewModel
import com.kls.githubuserian.viewModel.ViewModelFactory

class DetailActivity : AppCompatActivity() {


    private lateinit var userAddUpdateViewModel: UserAddUpdateViewModel
    private lateinit var binding: ActivityDetailBinding
    private var userDatabase: UserDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userDatabase = UserDatabase()

        val user = intent.getParcelableExtra<UserModel>(EXTRA_USER) as UserModel
        userAddUpdateViewModel = obtainViewModel(this)

        val sectionsPagerAdapter = SectionPagerAdapter(this,user.username.toString())
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f

        val detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserDetailViewModel::class.java)
        detailViewModel.getDetailUser(user.username.toString())
        detailViewModel.userDetail.observe(this) { userDetail ->
            setUserDetailData(userDetail)
        }

        binding.fabAdd.setOnClickListener {

            userAddUpdateViewModel.insert(userDatabase as UserDatabase)
            showToast(getString(R.string.added))
        }

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun obtainViewModel(activity: AppCompatActivity): UserAddUpdateViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(UserAddUpdateViewModel::class.java)
    }

    private fun setUserDetailData(user: UserDetailResponse){
        binding.txtDetailName.text = user.name.toString()
        binding.txtDetailUsername.text = user.username.toString()
        binding.txtDetailLocation.text = user.location.toString()
        binding.txtDetailCompany.text = user.company.toString()
        binding.txtDetailFollower.text = "${user.followers} Followers"
        binding.txtDetailFollowing.text = "${user.following} Following"
        binding.txtDetailRepository.text = "${user.repository} Repository"
        Glide.with(this)
            .load(user.avatar)
            .circleCrop()
            .into(binding.imageView)
        userDatabase.let {
            userDatabase?.name = user.name.toString()
            userDatabase?.avatar = user.avatar.toString()
            userDatabase?.username = user.username.toString()
            userDatabase?.location = user.location.toString()
            userDatabase?.company = user.company.toString()
            userDatabase?.following = user.following
            userDatabase?.followers = user.followers
            userDatabase?.repository = user.repository
        }
    }

    companion object {
        const val EXTRA_USER = "extra_user"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

}