package com.example.app_25_weightapp.di

import android.content.Context
import com.example.app_25_weightapp.application.ApplicationModule
import com.example.app_25_weightapp.application.WeightApplication
import com.example.app_25_weightapp.viewmodels.CreateWeightViewModel
import com.example.app_25_weightapp.viewmodels.MainActivityViewModel
import com.example.app_25_weightapp.viewmodels.ProfileViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@ApplicationScope
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun mainActivityViewModel() : MainActivityViewModel
    fun profileViewModel() : ProfileViewModel
    fun createWeightViewModel() : CreateWeightViewModel

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: WeightApplication): ApplicationComponent
    }

}