package com.pet.animal.formula.dose.health.veterinary.cure.vetformula.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.pet.animal.formula.dose.health.veterinary.cure.core.AppScreens
import com.pet.animal.formula.dose.health.veterinary.cure.fakerepo.FakeRepositoryImpl
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.about.AboutCalcFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.about.AboutFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.about.AboutTimerFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.calculator.CalculatorFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.calculator.keyboard.CalculatorKeyboardFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.edittext.EditTextFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.info.InfoFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.gas.GasFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.gas.inhanasth.GasInhalationAnasthesiaFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.gas.inhanasth.result.GasInhalationAnasthesiaResultFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.mainscreen.MainScreenFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.PharmacyFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.cri.PharmacyCRIFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.cri.result.PharmacyCRIResultFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.doses.PharmacyDosesFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.doses.result.PharmacyDosesResultFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface.PharmacySurfaceFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface.result.PharmacySurfaceResultFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.settings.SettingsFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.webview.VetMedicalViewFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.webview.WsavaViewFragmentViewModel
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
    // ???????????????? ??????????????????????
    single<FakeRepositoryImpl> { FakeRepositoryImpl() }
}

val utils = module {
    // ?????????????????? ?????????????? ?? ????????????????
    single<ResourcesProviderImpl> { ResourcesProviderImpl(androidContext()) }
    // ?????????? ?????? ???????????????????? ???????????????? ????????????????????
    single<SettingsImpl> { SettingsImpl() }
}

val screens = module {
    // Scope ?????? MainActivity
    scope(named(MAIN_ACTIVITY_NAME)) {
        viewModel {
            MainViewModel()
        }
    }
    /** ???????????? ?????? ?????????????????? */ //region
    single<Cicerone<Router>>(named(CICERONE_NAME)) { Cicerone.create() }
    single<NavigatorHolder> {
        get<Cicerone<Router>>(named(CICERONE_NAME)).getNavigatorHolder()
    }
    single<Router> { get<Cicerone<Router>>(named(CICERONE_NAME)).router }
    single<AppScreens> { AppScreensImpl() }
    //endregion
    /** ???????????? ?????? Scope ???????????????????? */ //region
    scope(named(FragmentScope.SHOW_MAIN_SCREEN_FRAGMENT_SCOPE)) {
        viewModel {
            MainScreenFragmentViewModel()
        }
    }
    scope(named(FragmentScope.SHOW_PHARMACY_FRAGMENT_SCOPE)) {
        viewModel {
            PharmacyFragmentViewModel()
        }
    }
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
    scope(named(FragmentScope.SHOW_PHARMACY_DOSES_FRAGMENT_SCOPE)) {
        viewModel {
            PharmacyDosesFragmentViewModel()
        }
    }
    scope(named(FragmentScope.SHOW_PHARMACY_DOSES_RESULT_FRAGMENT_SCOPE)) {
        viewModel {
            PharmacyDosesResultFragmentViewModel()
        }
    }
    scope(named(FragmentScope.SHOW_PHARMACY_CRI_FRAGMENT_SCOPE)) {
        viewModel {
            PharmacyCRIFragmentViewModel()
        }
    }
    scope(named(FragmentScope.SHOW_PHARMACY_CRI_RESULT_FRAGMENT_SCOPE)) {
        viewModel {
            PharmacyCRIResultFragmentViewModel()
        }
    }
    scope(named(FragmentScope.SHOW_CALCULATOR_FRAGMENT_SCOPE)) {
        viewModel {
            CalculatorFragmentViewModel()
        }
    }
    scope(named(FragmentScope.SHOW_CALCULATOR_KEYBOARD_FRAGMENT_SCOPE)) {
        viewModel {
            CalculatorKeyboardFragmentViewModel()
        }
    }
    scope(named(FragmentScope.SHOW_GAS_FRAGMENT_SCOPE)) {
        viewModel {
            GasFragmentViewModel()
        }
    }
    scope(named(FragmentScope.SHOW_GAS_INHALATION_ANASTHESIA_FRAGMENT_SCOPE)) {
        viewModel {
            GasInhalationAnasthesiaFragmentViewModel()
        }
    }
    scope(named(FragmentScope.SHOW_GAS_INHALATION_ANASTHESIA_RESULT_FRAGMENT_SCOPE)) {
        viewModel {
            GasInhalationAnasthesiaResultFragmentViewModel()
        }
    }
    scope(named(FragmentScope.SHOW_ABOUT_FRAGMENT_SCOPE)) {
        viewModel {
            AboutFragmentViewModel()
        }
    }
    scope(named(FragmentScope.SHOW_INFO_FRAGMENT_SCOPE)) {
        viewModel {
            InfoFragmentViewModel()
        }
    }
    scope(named(FragmentScope.SHOW_SETTINGS_FRAGMENT_SCOPE)) {
        viewModel {
            SettingsFragmentViewModel()
        }
    }
    scope(named(FragmentScope.SHOW_VETMEDICAL_VIEW_FRAGMENT_SCOPE)) {
        viewModel {
            VetMedicalViewFragmentViewModel()
        }
    }
    scope(named(FragmentScope.SHOW_WSAVA_VIEW_FRAGMENT_SCOPE)) {
        viewModel {
            WsavaViewFragmentViewModel()
        }
    }
    scope(named(FragmentScope.SHOW_EDIT_TEXT_FRAGMENT_SCOPE)) {
        viewModel {
            EditTextFragmentViewModel()
        }
    }
    scope(named(FragmentScope.SHOW_ABOUT_CALC_SCOPE)) {
        viewModel {
            AboutCalcFragmentViewModel()
        }
    }
    scope(named(FragmentScope.SHOW_ABOUT_TIMER_SCOPE)) {
        viewModel {
            AboutTimerFragmentViewModel()
        }
    }
    //endregion
    // ?????????? ?????? ???????????????? ???????????????? ???????????????? ?? ?????????????? ????????
    single<UpAndBottomFramesSizesChanger> { UpAndBottomFramesSizesChanger() }
}