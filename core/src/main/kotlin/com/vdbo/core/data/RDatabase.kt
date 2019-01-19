package com.vdbo.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vdbo.core.data.movie.Movie
import com.vdbo.core.data.movie.MovieLocalDao

@Database(entities = [Movie::class], version = 1)
abstract class RDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieLocalDao

}