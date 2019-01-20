package com.vdbo.rateitia.movies.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vdbo.core.dagger.SchedulerProvider
import com.vdbo.core.data.movie.Movie
import com.vdbo.core.data.movie.MovieRepository
import com.vdbo.rateitia.common.BaseViewModel
import com.vdbo.rateitia.common.SingleEvent
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val schedulers: SchedulerProvider
) : BaseViewModel() {

    private val _movie = MutableLiveData<Movie>()
    private val _navigateOnSubmit = MutableLiveData<SingleEvent<Unit>>()
    private lateinit var editedMovie: Movie
    val movie: LiveData<Movie> get() = _movie
    val navigateOnSubmit: LiveData<SingleEvent<Unit>> get() = _navigateOnSubmit
    lateinit var rating: Observable<Float>

    override fun onViewActive() {
        rating.subscribeBy(
            onNext = { editedMovie = editedMovie.copy(rating = it) }
        ).addTo(viewDisposables)
    }

    fun onCreate(movieId: Int) {
        loadMovie(movieId)
    }

    fun onSubmit() {
        movieRepository.edit(editedMovie)
            .observeOn(schedulers.ui())
            .subscribeBy(
                onComplete = { _navigateOnSubmit.value = SingleEvent(Unit) }
            )
            .addTo(disposables)
    }

    private fun loadMovie(id: Int) {
        movieRepository.get(id)
            .observeOn(schedulers.ui())
            .subscribeBy(
                onSuccess = {
                    editedMovie = it
                    _movie.value = it
                }
            )
            .addTo(disposables)
    }

}