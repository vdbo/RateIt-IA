package com.vdbo.core.data.movie

import com.vdbo.core.dagger.TestSchedulers
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Before
import org.junit.Test


class MovieRepositoryTest {

    private val schedulers = TestSchedulers()
    private val movieLocalDao = mockk<MovieLocalDao>()
    private val movieRepository = MovieRepository(movieLocalDao, schedulers)
    private val mockedMoviesDesc = listOf(
        Movie(0, "title 0", "description 0", 3.0f),
        Movie(1, "title 1", "description 1", 2.0f),
        Movie(2, "title 2", "description 2", 1.0f)
    )
    private val mockedMoviesAsc = listOf(
        Movie(0, "title 2", "description 2", 1.0f),
        Movie(1, "title 1", "description 1", 2.0f),
        Movie(2, "title 0", "description 0", 3.0f)
    )

    @Before
    fun initialSetup() {
        every { movieLocalDao.getDesc() } returns Single.just(mockedMoviesDesc)
        every { movieLocalDao.getAsc() } returns Single.just(mockedMoviesAsc)
    }

    @Test
    fun getMoviesDesc_Successfully() {
        movieRepository.get(SortType.DESC)
            .test()
            .assertComplete()
            .assertResult(mockedMoviesDesc)
    }

    @Test
    fun getMoviesAsc_Successfully() {
        movieRepository.get(SortType.ASC)
            .test()
            .assertComplete()
            .assertResult(mockedMoviesAsc)
    }

}