package com.vdbo.core.data.movie

import io.reactivex.Completable
import io.reactivex.Single

interface MovieDataSource {

    fun get(sortType: SortType = SortType.DESC): Single<List<Movie>>

    fun edit(movie: Movie): Completable

}