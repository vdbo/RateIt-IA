package com.vdbo.rateitia.ui.main

import androidx.lifecycle.ViewModel
import com.vdbo.core.dagger.ViewModelFactoryModule
import com.vdbo.core.dagger.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

}