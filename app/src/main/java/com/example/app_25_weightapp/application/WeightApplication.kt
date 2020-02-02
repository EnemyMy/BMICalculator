package com.example.app_25_weightapp.application

import android.app.Application
import com.example.app_25_weightapp.di.ApplicationComponent
import com.example.app_25_weightapp.di.DaggerApplicationComponent

class WeightApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        applicationComponent = DaggerApplicationComponent.factory().create(this)
        super.onCreate()
    }
}