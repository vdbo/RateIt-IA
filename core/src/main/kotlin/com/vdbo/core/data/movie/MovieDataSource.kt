package com.vdbo.core.data.movie

import io.reactivex.Completable
import io.reactivex.Single

interface MovieDataSource {

    fun get(): Single<List<Movie>>

    fun edit(movie: Movie): Completable

}