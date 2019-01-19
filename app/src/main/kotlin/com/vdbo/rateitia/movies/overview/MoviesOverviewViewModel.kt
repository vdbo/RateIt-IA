package com.vdbo.rateitia.movies.overview

import androidx.lifecycle.ViewModel
import com.vdbo.core.data.movie.MovieRepository
import javax.inject.Inject

class MoviesOverviewViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    fun getMovies() {
        movieRepository.getMovies()
    }

}