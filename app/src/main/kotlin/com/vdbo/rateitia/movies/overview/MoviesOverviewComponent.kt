package com.vdbo.rateitia.movies.overview

import androidx.lifecycle.ViewModel
import com.vdbo.rateitia.dagger.ViewModelKey
import dagger.Binds
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(modules = [MoviesOverviewComponent.Module::class])
interface MoviesOverviewComponent {

    @Subcomponent.Builder interface Builder {

        fun build(): MoviesOverviewComponent

    }

    @dagger.Module interface Module {

        @Binds
        @IntoMap
        @ViewModelKey(MoviesOverviewViewModel::class)
        fun bindMoviesOverviewViewModel(viewModel: MoviesOverviewViewModel): ViewModel

    }

}