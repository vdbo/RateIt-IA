package com.vdbo.core.dagger

import com.vdbo.core.data.RDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module class MovieModule {

    @Singleton @Provides
    fun bindMovieLocalDao(database: RDatabase) = database.movieDao()

}