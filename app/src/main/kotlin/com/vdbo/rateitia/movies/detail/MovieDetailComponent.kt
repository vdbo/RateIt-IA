package com.vdbo.rateitia.movies.detail

import androidx.lifecycle.ViewModel
import com.vdbo.rateitia.dagger.ViewModelKey
import dagger.Binds
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(modules = [MovieDetailComponent.Module::class])
interface MovieDetailComponent {

    @Subcomponent.Builder interface Builder {

        fun build()

    }

    @dagger.Module interface Module {

        @Binds
        @IntoMap
        @ViewModelKey(MovieDetailViewModel::class)
        fun bindMovieDetailViewModel(movieDetailViewModel: MovieDetailViewModel): ViewModel

    }

}