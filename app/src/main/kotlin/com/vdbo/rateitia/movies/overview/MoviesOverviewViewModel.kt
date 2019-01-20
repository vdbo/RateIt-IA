package com.vdbo.rateitia.movies.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vdbo.core.dagger.SchedulerProvider
import com.vdbo.core.data.movie.Movie
import com.vdbo.core.data.movie.MovieRepository
import com.vdbo.core.data.movie.SortType
import com.vdbo.rateitia.common.SingleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MoviesOverviewViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val schedulers: SchedulerProvider
) : ViewModel() {

    private val disposables = CompositeDisposable()
    private val _movies = MutableLiveData<List<Movie>>()
    private val _navigateToMovieDetails = MutableLiveData<SingleEvent<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies
    val navigateToMovieDetails: LiveData<SingleEvent<Movie>>
        get() = _navigateToMovieDetails

    init {
        loadMovies()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun onDescSortChosen() {
        movieRepository.get(SortType.DESC)
            .observeOn(schedulers.ui())
            .subscribeBy(
                onSuccess = ::showMovies
            )
            .addTo(disposables)
    }

    fun onAscSortChosen() {
        movieRepository.get(SortType.ASC)
            .observeOn(schedulers.ui())
            .subscribeBy(
                onSuccess = ::showMovies
            )
            .addTo(disposables)
    }

    fun onMovieClick(movie: Movie) {
        _navigateToMovieDetails.value = SingleEvent(movie)
    }

    private fun loadMovies() {
        movieRepository.get()
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