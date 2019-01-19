package com.vdbo.core.data.movie

import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieLocalDao: MovieLocalDao
) : MovieDataSource {

    override fun get(): Single<List<Movie>> {
        TODO("not implemented")
    }

    override fun edit(movie: Movie): Completable {
        TODO("not implemented")
    }


}