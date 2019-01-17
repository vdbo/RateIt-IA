package com.vdbo.core.dagger

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module interface ViewModelFactoryModule {

    @Binds fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}