package com.vdbo.core.dagger

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vdbo.core.data.RDatabase
import com.vdbo.core.data.movie.MovieLocalDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module class CoreModule(private val context: Context) {

    @Singleton @Provides fun provideContext() = context

    @Singleton @Provides fun provideDb(context: Context) = Room.databaseBuilder(
        context.applicationContext,
        RDatabase::class.java,
        "RateItIADatabase"
    ).addCallback(object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            db.execSQL(MovieLocalDao.SCRIPT_PREFILL)
        }
    }).build()

    @Singleton @Provides
    fun provideSchedulerProvider(appSchedulers: CoreSchedulers): SchedulerProvider = appSchedulers

}