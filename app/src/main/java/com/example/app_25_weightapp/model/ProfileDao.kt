package com.example.app_25_weightapp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProfileDao {

    @Insert
    fun createProfile(profileEntity: ProfileEntity)

    @Query("SELECT * FROM ProfileEntity")
    fun getProfile() : LiveData<ProfileEntity>

    @Query("DELETE FROM ProfileEntity")
    fun deleteProfile()

}