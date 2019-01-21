package com.vdbo.core.common

import com.vdbo.core.dagger.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random

class RandomRatingsManager @Inject constructor(
    private val schedulers: SchedulerProvider
) {

    private val _randomRatings = PublishSubject.create<Pair<Int, Float>>()
    private val disposables: CompositeDisposable = CompositeDisposable()
    val ratings: Observable<Pair<Int, Float>> get() = _randomRatings

    fun startRatings(itemToRateSize: Int) {
        Observable.timer(Random.nextLong(3, 10), TimeUnit.SECONDS)
            .flatMap { Observable.just(Random.nextDouble(0.0, 5.0).toFloat()) }
            .flatMap { itemRating ->
                Observable.just(Random.nextInt(0, itemToRateSize) to itemRating)
            }
            .repeat()
            .subscribeOn(schedulers.io())
            .subscribeBy(
                onNext = {
                    _randomRatings.onNext(it)
                }
            )
            .addTo(disposables)
    }

    fun stopRatings() {
        disposables.clear()
    }

    enum class State {
        STARTED, STOPPED
    }

}