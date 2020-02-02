package com.example.app_25_weightapp.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.app_25_weightapp.application.WeightApplication
import com.example.app_25_weightapp.model.ProfileEntity
import com.example.app_25_weightapp.model.Repository
import com.example.app_25_weightapp.toFloatOrZero
import java.util.*
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    val profileHeightData: MutableLiveData<String> = MutableLiveData()

    val profileDateData: MutableLiveData<String> = MutableLiveData()

    fun getProfile() = repository.getProfile()

    fun onProfileChange(profileEntity: ProfileEntity) {
        profileHeightData.value = profileEntity.height
        profileDateData.value = profileEntity.date
    }

    fun validateInputs() = profileHeightData.value != null &&
                profileDateData.value != null &&
                profileHeightData.value != "" &&
                profileDateData.value != "" &&
                profileHeightData.value!!.toFloatOrZero() > 0f

    fun createProfile() = repository.createProfile(ProfileEntity(1, profileHeightData.value!!, profileDateData.value!!))

}