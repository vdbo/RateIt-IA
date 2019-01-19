package com.vdbo.rateitia.main

import androidx.lifecycle.ViewModel
import com.vdbo.rateitia.dagger.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Builder interface Builder {

        fun build(): MainComponent

    }

}

@Module interface MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

}