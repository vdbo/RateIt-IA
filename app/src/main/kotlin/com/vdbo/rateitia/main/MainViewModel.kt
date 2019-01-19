package com.vdbo.rateitia.main

import androidx.lifecycle.ViewModel
import com.vdbo.core.data.movie.MovieRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getMovies() {
        movieRepository.getMovies()
    }

}