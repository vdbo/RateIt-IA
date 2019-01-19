package com.vdbo.rateitia.movies.detail

import androidx.lifecycle.ViewModel
import com.vdbo.core.data.movie.MovieRepository
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {



}