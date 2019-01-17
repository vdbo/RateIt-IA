package com.vdbo.rateitia

import androidx.lifecycle.ViewModelProvider
import com.vdbo.core.dagger.CoreComponent
import com.vdbo.core.dagger.ViewModelFactoryModule
import com.vdbo.rateitia.ui.main.ViewModelModule
import dagger.Component
import javax.inject.Scope

@MainScope
@Component(
    modules = [ViewModelModule::class],
    dependencies = [CoreComponent::class]
)
interface MainComponent {

    val viewModelFactory: ViewModelProvider.Factory

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MainScope