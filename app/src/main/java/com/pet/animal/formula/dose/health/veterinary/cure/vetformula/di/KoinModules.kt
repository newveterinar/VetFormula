package com.pet.animal.formula.dose.health.veterinary.cure.vetformula.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.AppScreensImpl
import com.pet.animal.formula.dose.health.veterinary.cure.utils.Constants
import com.pet.animal.formula.dose.health.veterinary.cure.vetformula.view.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val screens = module {
    // Scope для MainActivity
    scope(named(Constants.MAIN_ACTIVITY_NAME)) {
        viewModel {
            MainViewModel()
        }
    }
    single<Cicerone<Router>>(named(Constants.CICERONE_NAME)) { Cicerone.create() }
    single<NavigatorHolder> {
        get<Cicerone<Router>>(named(Constants.CICERONE_NAME)).getNavigatorHolder() }
    single<Router> { get<Cicerone<Router>>(named(Constants.CICERONE_NAME)).router }
    single<AppScreensImpl> { AppScreensImpl() }
}