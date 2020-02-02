package com.example.app_25_weightapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_25_weightapp.computeBmi
import com.example.app_25_weightapp.model.Repository
import com.example.app_25_weightapp.model.WeightLogEntity
import com.example.app_25_weightapp.toDateOrToday
import com.example.app_25_weightapp.toFloatOrZero
import java.util.*
import javax.inject.Inject

class CreateWeightViewModel  @Inject constructor(private val repository: Repository): ViewModel() {

    val newWeightData: MutableLiveData<String> = MutableLiveData()

    val newDateData: MutableLiveData<String> = MutableLiveData()

    private lateinit var profileDate: String
    private lateinit var profileHeight: String

    fun validateInputs() = newWeightData.value != null &&
            newDateData.value != null &&
            newWeightData.value != "" &&
            newDateData.value != "" &&
            newWeightData.value!!.toFloatOrZero() > 0f &&
            newDateData.value!!.toDateOrToday() <= Date() &&
            newDateData.value!!.toDateOrToday() > profileDate.toDateOrToday()

    fun getProfileData() = repository.getProfile()

    fun setProfileData(date: String, height: String) {
        profileDate = date
        profileHeight = height
    }

    fun createWeightLog() = repository.createWeightLog(WeightLogEntity(newWeightData.value!!, newDateData.value!!))

}