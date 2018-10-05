package org.almiso.exchangerates.businesslayer.di

import dagger.Component
import org.almiso.exchangerates.businesslayer.fragments.ConverterFragment
import javax.inject.Singleton


@Singleton
@Component(modules = [DependenciesFactory::class])
interface DependenciesInjector {
    fun inject(coordinator: ConverterFragment.Coordinator)
}
