package com.vdbo.rateitia

import androidx.lifecycle.ViewModelProvider
import com.vdbo.core.dagger.CoreComponent
import com.vdbo.rateitia.dagger.ViewModelFactoryModule
import com.vdbo.rateitia.main.MainComponent
import com.vdbo.rateitia.main.MainModule
import dagger.Component
import javax.inject.Scope

@MainScope
@Component(
    modules = [ViewModelFactoryModule::class, MainModule::class],
    dependencies = [CoreComponent::class]
)
interface AppComponent {

    val viewModelFactory: ViewModelProvider.Factory

    fun mainComponentBuilder(): MainComponent.Builder

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MainScope