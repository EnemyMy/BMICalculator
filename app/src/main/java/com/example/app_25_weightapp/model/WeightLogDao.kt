package com.example.app_25_weightapp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeightLogDao {

    @Insert
    fun createLog(weightLogEntity: WeightLogEntity)

    @Query("SELECT * FROM WeightLogEntity")
    fun getLogs() : LiveData<List<WeightLogEntity>>

    @Query("DELETE FROM WeightLogEntity")
    fun deleteLogs()

}