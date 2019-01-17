package com.vdbo.core.data.movie

import android.util.Log
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieDao: MovieDao
) {
    fun getMovies() {
        Log.w(MovieRepository::class.java.simpleName, "Movie repo triggered")
    }


}