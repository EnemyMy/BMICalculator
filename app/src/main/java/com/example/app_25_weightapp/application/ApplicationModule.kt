package com.example.app_25_weightapp.application

import android.content.Context
import androidx.room.Room
import com.example.app_25_weightapp.di.ApplicationScope
import com.example.app_25_weightapp.model.ProfileDao
import com.example.app_25_weightapp.model.WeightDatabase
import com.example.app_25_weightapp.model.WeightLogDao
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class ApplicationModule {

    @Module
    companion object {

        @JvmStatic
        @ApplicationScope
        @Provides
        fun provideWeightDatabase(application: WeightApplication): WeightDatabase =
            Room.databaseBuilder(application.applicationContext, WeightDatabase::class.java, WeightDatabase.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

        @JvmStatic
        @ApplicationScope
        @Provides
        fun provideProfileDao(weightDatabase: WeightDatabase) = weightDatabase.getProfileDao()

        @JvmStatic
        @ApplicationScope
        @Provides
        fun provideWeightDao(weightDatabase: WeightDatabase) = weightDatabase.getWeightLogDao()


    }

}