package com.example.app_25_weightapp.weight_log

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.app_25_weightapp.application.WeightApplication
import com.example.app_25_weightapp.createViewModel
import com.example.app_25_weightapp.databinding.ActivityCreateWeightBinding
import com.example.app_25_weightapp.model.ProfileEntity
import com.example.app_25_weightapp.showDatePickerDialog
import com.example.app_25_weightapp.viewmodels.CreateWeightViewModel

class CreateWeightActivity : AppCompatActivity() {
    private lateinit var createWeightViewModel: CreateWeightViewModel
    private lateinit var profileData: LiveData<ProfileEntity>
    private lateinit var binding: ActivityCreateWeightBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCreateWeightBinding.inflate(layoutInflater)
        createWeightViewModel = createViewModel { (application as WeightApplication).applicationComponent.createWeightViewModel() }
        binding.viewmodel = createWeightViewModel
        binding.lifecycleOwner = this
        profileData = createWeightViewModel.getProfileData()

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        profileData.observe(this, Observer {
            if (it != null)
                createWeightViewModel.setProfileData(it.date, it.height)
        })
        binding.profileDate.setOnClickListener {
            showDatePickerDialog(binding.profileDate)
        }
        binding.fab.setOnClickListener {
            if (createWeightViewModel.validateInputs()) {
                createWeightViewModel.createWeightLog()
                finish()
            }
            else
                Toast.makeText(this, "You must enter correct data", Toast.LENGTH_SHORT).show()
        }
    }
}