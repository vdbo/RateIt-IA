package com.vdbo.core.dagger

import com.vdbo.core.data.movie.MovieDao
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class, MovieModule::class])
interface CoreComponent {

    val movieDao: MovieDao

}