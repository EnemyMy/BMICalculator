package com.example.app_25_weightapp.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProfileEntity::class, WeightLogEntity::class], version = 1)
abstract class WeightDatabase: RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "Weight Database"
    }

    abstract fun getProfileDao() : ProfileDao
    abstract  fun getWeightLogDao() : WeightLogDao
}