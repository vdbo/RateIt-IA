package com.vdbo.rateitia.movies.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vdbo.core.common.RandomRatingsManager
import com.vdbo.core.common.SortType
import com.vdbo.core.dagger.SchedulerProvider
import com.vdbo.core.data.movie.Movie
import com.vdbo.core.data.movie.MovieRepository
import com.vdbo.rateitia.common.BaseViewModel
import com.vdbo.rateitia.common.SingleEvent
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MoviesOverviewViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val randomRatingsManager: RandomRatingsManager,
    private val schedulers: SchedulerProvider
) : BaseViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    private val _navigateToMovieDetails = MutableLiveData<SingleEvent<Movie>>()
    private var moviesOverviewViewData = MoviesOverviewViewData.DEFAULT
    val movies: LiveData<List<Movie>> get() = _movies
    val navigateToMovieDetails: LiveData<SingleEvent<Movie>> get() = _navigateToMovieDetails

    init {
        loadMovies(moviesOverviewViewData.sortType)

        randomRatingsManager.ratings
            .flatMap { (movieIndex, movieRating) ->
                _movies.value?.let {
                    val movieToEdit = it[movieIndex].copy(rating = movieRating)
                    movieRepository.edit(movieToEdit)
                        .andThen(Observable.just(movieIndex to movieRating))
                } ?: throw IllegalAccessException("Movies doesn't exist :(")
            }
            .observeOn(schedulers.ui())
            .subscribeBy(
                onNext = { loadMovies(moviesOverviewViewData.sortType) },
                onError = { print(it.localizedMessage) }
            )
            .addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        randomRatingsManager.stopRatings()
    }

    fun onDescSortChosen() {
        moviesOverviewViewData = moviesOverviewViewData.copy(sortType = SortType.DESC)
        loadMovies(SortType.DESC)
    }

    fun onAscSortChosen() {
        moviesOverviewViewData = moviesOverviewViewData.copy(sortType = SortType.ASC)
        loadMovies(SortType.ASC)
    }

    fun onRandomRatingChosen() {
        moviesOverviewViewData = when (moviesOverviewViewData.randomRatingsState) {
            RandomRatingsManager.State.STARTED -> {
                randomRatingsManager.stopRatings()
                moviesOverviewViewData.copy(
                    randomRatingsState = RandomRatingsManager.State.STOPPED
                )
            }
            RandomRatingsManager.State.STOPPED -> {
                randomRatingsManager.startRatings(_movies.value?.size ?: 0)
                moviesOverviewViewData.copy(
                    randomRatingsState = RandomRatingsManager.State.STARTED
                )
            }
        }
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