package com.pet.animal.formula.dose.health.veterinary.cure.vetformula.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.pet.animal.formula.dose.health.veterinary.cure.core.AppScreens
import com.pet.animal.formula.dose.health.veterinary.cure.fakerepo.FakeRepositoryImpl
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.doses.PharmacyDosesFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface.PharmacySurfaceFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface.result.PharmacySurfaceResultFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.AppScreensImpl
import com.pet.animal.formula.dose.health.veterinary.cure.utils.CICERONE_NAME
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import com.pet.animal.formula.dose.health.veterinary.cure.utils.MAIN_ACTIVITY_NAME
import com.pet.animal.formula.dose.health.veterinary.cure.utils.resources.ResourcesProviderImpl
import com.pet.animal.formula.dose.health.veterinary.cure.utils.screens.UpAndBottomFramesSizesChanger
import com.pet.animal.formula.dose.health.veterinary.cure.utils.settings.SettingsImpl
import com.pet.animal.formula.dose.health.veterinary.cure.vetformula.view.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val database = module {
    // Фейковый репозиторий
    single<FakeRepositoryImpl> { FakeRepositoryImpl() }
}

val utils = module {
    // Получение доступа к ресурсам
    single<ResourcesProviderImpl> { ResourcesProviderImpl(androidContext()) }
    // Класс для сохранения настроек приложения
    single<SettingsImpl> { SettingsImpl() }
}

val screens = module {
    // Scope для MainActivity
    scope(named(MAIN_ACTIVITY_NAME)) {
        viewModel {
            MainViewModel()
        }
    }
    /** Классы для навигации */ //region
    single<Cicerone<Router>>(named(CICERONE_NAME)) { Cicerone.create() }
    single<NavigatorHolder> {
        get<Cicerone<Router>>(named(CICERONE_NAME)).getNavigatorHolder() }
    single<Router> { get<Cicerone<Router>>(named(CICERONE_NAME)).router }
    single<AppScreens> { AppScreensImpl() }
    //endregion
    /** Классы для Scope фрагментов */ //region
    scope(named(FragmentScope.SHOW_PHARMACY_SURFACE_FRAGMENT_SCOPE)) {
        viewModel {
            PharmacySurfaceFragmentViewModel()
        }
    }
    scope(named(FragmentScope.SHOW_PHARMACY_SURFACE_RESULT_FRAGMENT_SCOPE)) {
        viewModel {
            PharmacySurfaceResultFragmentViewModel()
        }
    }
    scope(named(FragmentScope.SHOW_PHARMACY_DOSE_FRAGMENT_SCOPE)) {
        viewModel {
            PharmacyDosesFragmentViewModel()
        }
    }
    //endregion
    // Класс для хранения размеров верхнего и нижнего окон
    single<UpAndBottomFramesSizesChanger> { UpAndBottomFramesSizesChanger() }
}