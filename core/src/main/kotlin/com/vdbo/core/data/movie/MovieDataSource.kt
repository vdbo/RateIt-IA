package com.vdbo.core.data.movie

import io.reactivex.Completable
import io.reactivex.Single

interface MovieDataSource {

    fun get(sortType: SortType): Single<List<Movie>>

    fun get(id: Int): Single<Movie>

    fun edit(movie: Movie): Completable

}