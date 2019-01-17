package com.vdbo.rateitia.ui.main

import androidx.lifecycle.ViewModel
import com.vdbo.core.data.movie.MovieRepository
import javax.inject.Inject
import javax.inject.Scope

class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getMovies() {
        movieRepository.getMovies()
    }

}