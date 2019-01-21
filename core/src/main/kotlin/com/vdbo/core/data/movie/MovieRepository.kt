package com.vdbo.core.data.movie

import com.vdbo.core.common.SortType
import com.vdbo.core.dagger.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieLocalDao: MovieLocalDao,
    private val schedulers: SchedulerProvider
) : MovieDataSource {

    override fun get(sortType: SortType): Single<List<Movie>> = when (sortType) {
        SortType.DESC -> movieLocalDao.getDesc()
            .subscribeOn(schedulers.io())
        SortType.ASC -> movieLocalDao.getAsc()
            .subscribeOn(schedulers.io())
    }

    override fun get(id: Int) = movieLocalDao.get(id)
        .subscribeOn(schedulers.io())

    override fun edit(movie: Movie) = movieLocalDao.edit(movie)
        .subscribeOn(schedulers.io())

}