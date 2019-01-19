package com.vdbo.core.dagger

import com.vdbo.core.data.movie.MovieLocalDao
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class, MovieModule::class])
interface CoreComponent {

    val movieDao: MovieLocalDao

}