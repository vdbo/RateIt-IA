package com.vdbo.core.data.movie

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Single

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun get(): Single<List<Movie>>

}