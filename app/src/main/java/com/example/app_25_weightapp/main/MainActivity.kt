package com.example.app_25_weightapp.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_25_weightapp.R
import com.example.app_25_weightapp.application.WeightApplication
import com.example.app_25_weightapp.databinding.ActivityMainBinding
import com.example.app_25_weightapp.model.ProfileEntity
import com.example.app_25_weightapp.model.WeightLogEntity
import com.example.app_25_weightapp.profile.ProfileActivity
import com.example.app_25_weightapp.viewmodels.MainActivityViewModel
import com.example.app_25_weightapp.weight_log.CreateWeightActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var profileData: LiveData<ProfileEntity>
    private lateinit var weightLogData: LiveData<List<WeightLogEntity>>

    override fun onCreate(savedInstanceState: Bundle?) {
        // Switch to AppTheme
        setTheme(R.style.AppTheme)

        mainActivityViewModel = (application as WeightApplication).applicationComponent.mainActivityViewModel()
        profileData = mainActivityViewModel.getProfileData()
        weightLogData = mainActivityViewModel.getWeightLogData()
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        profileData.observe(this, Observer {
            if (it == null) {
                binding.fab.visibility = View.GONE
            }
            else {
                (binding.recyclerView.adapter as RecyclerAdapter).submitProfile(it)
                showExistingProfile()
            }
        })
        setRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        if (binding.infoWarning.visibility == View.GONE)
            menu?.getItem(1)?.isVisible = true
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add_profile) {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        else if (item.itemId == R.id.delete_logs) {
            mainActivityViewModel.deleteWeightLogs()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showExistingProfile() {
        binding.infoWarning.visibility = View.GONE
        binding.fab.visibility = View.VISIBLE
        binding.fab.setOnClickListener {
            startActivity(Intent(this, CreateWeightActivity::class.java))
        }
        val adapter = binding.recyclerView.adapter as RecyclerAdapter
        weightLogData.observe(this, Observer { adapter.submitList(it) })
        invalidateOptionsMenu()
    }

    private fun setRecyclerView() {
        val recyclerView = binding.recyclerView
        val adapter = RecyclerAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }
}