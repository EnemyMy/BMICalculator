package com.example.app_25_weightapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.app_25_weightapp.model.ProfileEntity
import com.example.app_25_weightapp.model.Repository
import com.example.app_25_weightapp.model.WeightLogEntity
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    fun getProfileData() = repository.getProfile()

    fun getWeightLogData() = repository.getWeightLogs()

    fun deleteWeightLogs() = repository.deleteWeightLogs()

}