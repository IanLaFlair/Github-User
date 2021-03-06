package com.kls.githubuserian

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kls.githubuserian.adapter.UserAdapter
import com.kls.githubuserian.model.UserModel
import com.kls.githubuserian.utils.SettingPreferences
import com.kls.githubuserian.viewModel.SettingViewModel
import com.kls.githubuserian.viewModel.SettingViewModelFactory
import com.kls.githubuserian.viewModel.UserViewModel
import com.kls.githubuserian.viewModel.ViewModelFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var rvUser: RecyclerView
    private lateinit var progressBar: ProgressBar
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private var userViewModel: UserViewModel = UserViewModel(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(findViewById(R.id.toolbar))
        val pref = SettingPreferences.getInstance(dataStore)

        userViewModel = ViewModelProvider(this, SettingViewModelFactory(pref)).get(
            UserViewModel::class.java
        )

        userViewModel.getThemeSettings().observe(this
        ) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }


        userViewModel.userList.observe(this) { listUser ->
            if (listUser != null) {
                if (listUser.size == 0){
                    Toast.makeText(this, "User Tidak Ditemukan", Toast.LENGTH_SHORT).show()
                }else{
                    getUserList(listUser)
                }
            }
        }

        userViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        rvUser = findViewById(R.id.rv_user)
        progressBar = findViewById(R.id.progresbar)
        rvUser.setHasFixedSize(true)

    }

    private fun showLoading(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuFav -> {
                val intent = Intent(this,FavoriteUserActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.menuSetting -> {
                val intent = Intent(this,SettingActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)


        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                userViewModel.setListUser(query)
                userViewModel.userList.observe(this@HomeActivity) { listUser ->
                    listUser?.let { getUserList(it) }
                }
                searchView.clearFocus()
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun getUserList(listUser: ArrayList<UserModel>) {

        val adapter = UserAdapter(listUser)
        rvUser.adapter = adapter
        rvUser.layoutManager = LinearLayoutManager(this)

    }

}