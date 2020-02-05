package com.example.app_25_weightapp.model

import com.example.app_25_weightapp.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ApplicationScope
class Repository @Inject constructor(private val profileDao: ProfileDao, private val weightLogDao: WeightLogDao){

    fun createProfile(profileEntity: ProfileEntity) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                profileDao.deleteProfile()
                profileDao.createProfile(profileEntity)
            }
        }
    }

    fun getProfile() = profileDao.getProfile()

    fun createWeightLog(weightLogEntity: WeightLogEntity) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                weightLogDao.createLog(weightLogEntity)
            }
        }
    }

    fun getWeightLogs() = weightLogDao.getLogs()

    fun deleteWeightLogs() = weightLogDao.deleteLogs()

}