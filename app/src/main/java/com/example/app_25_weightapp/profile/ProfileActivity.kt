package com.example.app_25_weightapp.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.app_25_weightapp.application.WeightApplication
import com.example.app_25_weightapp.createViewModel
import com.example.app_25_weightapp.databinding.ActivityProfileBinding
import com.example.app_25_weightapp.model.ProfileEntity
import com.example.app_25_weightapp.showDatePickerDialog
import com.example.app_25_weightapp.viewmodels.ProfileViewModel

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProfileBinding
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var profileData : LiveData<ProfileEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        profileViewModel = createViewModel { (application as WeightApplication).applicationComponent.profileViewModel() }
        binding = ActivityProfileBinding.inflate(layoutInflater)
        binding.viewmodel = profileViewModel
        binding.lifecycleOwner = this
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        profileData = profileViewModel.getProfile()
        profileData.observe(this, Observer {
            if (it != null) profileViewModel.onProfileChange(it)
        })
        binding.profileDate.setOnClickListener {
            showDatePickerDialog(binding.profileDate)
        }
        binding.fab.setOnClickListener {
            if (profileViewModel.validateInputs()) {
                profileViewModel.createProfile()
                finish()
            }
            else
                Toast.makeText(this, "You must enter correct data", Toast.LENGTH_SHORT).show()
        }
    }
}