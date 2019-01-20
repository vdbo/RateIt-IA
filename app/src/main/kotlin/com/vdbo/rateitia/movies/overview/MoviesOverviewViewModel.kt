package com.vdbo.rateitia.movies.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vdbo.core.dagger.SchedulerProvider
import com.vdbo.core.data.movie.Movie
import com.vdbo.core.data.movie.MovieRepository
import com.vdbo.core.data.movie.SortType
import com.vdbo.rateitia.common.BaseViewModel
import com.vdbo.rateitia.common.SingleEvent
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MoviesOverviewViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val schedulers: SchedulerProvider
) : BaseViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    private val _navigateToMovieDetails = MutableLiveData<SingleEvent<Movie>>()
    private var moviesOverviewViewData = MoviesOverviewViewData.DEFAULT
    val movies: LiveData<List<Movie>> get() = _movies
    val navigateToMovieDetails: LiveData<SingleEvent<Movie>> get() = _navigateToMovieDetails

    init {
        loadMovies(moviesOverviewViewData.sortType)
    }

    fun onDescSortChosen() {
        moviesOverviewViewData = moviesOverviewViewData.copy(sortType = SortType.DESC)
        loadMovies(SortType.DESC)
    }

    fun onAscSortChosen() {
        moviesOverviewViewData = moviesOverviewViewData.copy(sortType = SortType.ASC)
        loadMovies(SortType.ASC)
    }

    fun onMovieClick(movie: Movie) {
        _navigateToMovieDetails.value = SingleEvent(movie)
    }

    fun onMovieEdited() {
        loadMovies(moviesOverviewViewData.sortType)
    }

    private fun loadMovies(sortType: SortType) {
        movieRepository.get(sortType)
            .observeOn(schedulers.ui())
            .subscribeBy(
                onSuccess = ::showMovies
            )
            .addTo(disposables)
    }

    private fun showMovies(movies: List<Movie>) {
        _movies.value = movies
    }

}