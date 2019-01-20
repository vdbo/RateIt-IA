package com.vdbo.rateitia.movies.detail

import androidx.lifecycle.ViewModel
import com.vdbo.rateitia.dagger.ViewModelKey
import dagger.Binds
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(modules = [MovieDetailsComponent.Module::class])
interface MovieDetailsComponent {

    @Subcomponent.Builder interface Builder {

        fun build(): MovieDetailsComponent

    }

    @dagger.Module interface Module {

        @Binds
        @IntoMap
        @ViewModelKey(MovieDetailsViewModel::class)
        fun bindMovieDetailsViewModel(movieDetailsViewModel: MovieDetailsViewModel): ViewModel

    }

}