package com.example.app_25_weightapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProfileEntity (@PrimaryKey var id : Int, var height : String, var date : String)