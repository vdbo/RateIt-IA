package com.vdbo.core.dagger

import android.content.Context
import androidx.room.Room
import com.vdbo.core.data.RDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module class CoreModule(private val context: Context) {

    @Singleton @Provides fun provideContext() = context

    @Singleton @Provides fun provideDb(context: Context) = Room.databaseBuilder(
        context.applicationContext,
        RDatabase::class.java,
        "RateItIADatabase"
    ).build()

}