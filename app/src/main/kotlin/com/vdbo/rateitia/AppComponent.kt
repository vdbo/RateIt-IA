package com.vdbo.rateitia

import androidx.lifecycle.ViewModelProvider
import com.vdbo.core.dagger.CoreComponent
import com.vdbo.rateitia.dagger.ViewModelFactoryModule
import com.vdbo.rateitia.movies.detail.MovieDetailsComponent
import com.vdbo.rateitia.movies.overview.MoviesOverviewComponent
import dagger.Component
import javax.inject.Scope

@MainScope
@Component(
    modules = [
        ViewModelFactoryModule::class,
        MoviesOverviewComponent.Module::class,
        MovieDetailsComponent.Module::class
    ],
    dependencies = [CoreComponent::class]
)
interface AppComponent {

    val viewModelFactory: ViewModelProvider.Factory

    fun moviesOverviewComponentBuilder(): MoviesOverviewComponent.Builder

    fun movieDetailComponentBuilder(): MovieDetailsComponent.Builder

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MainScope