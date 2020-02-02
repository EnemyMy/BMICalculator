package com.example.app_25_weightapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeightLogEntity(var weight : String, var date : String) {

    @PrimaryKey(autoGenerate = true) var id : Int = 0

}