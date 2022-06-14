package com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator

import MainScreenFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.pet.animal.formula.dose.health.veterinary.cure.core.AppScreens
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.about.AboutFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.calculator.CalculatorFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.calculator.keyboard.CalculatorKeyboardFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.conversions.ConversionsFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.edittext.EditTextFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.fluids.FluidsFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.hematology.HematologyFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.info.InfoFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.PharmacyFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.cri.PharmacyCRIFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.cri.result.PharmacyCRIResultFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.doses.PharmacyDosesFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.doses.result.PharmacyDosesResultFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface.PharmacySurfaceFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface.result.PharmacySurfaceResultFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.settings.SettingsFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.timer.TimerFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.webview.VetMedicalViewFragment

class AppScreensImpl : AppScreens {
    //region Системные окна
    override fun mainScreen() = FragmentScreen {
        MainScreenFragment.newInstance()
    }

    //region About
    override fun aboutScreen() = FragmentScreen {
        AboutFragment.newInstance()
    }

    //region Information
    override fun infoScreen() = FragmentScreen {
        InfoFragment.newInstance()
    }
    //endregion

    //region Pharmacy
    override fun pharmacyScreen() = FragmentScreen {
        PharmacyFragment.newInstance()
    }

    // PharmacySurface
    override fun pharmacySurfaceScreen() = FragmentScreen {
        PharmacySurfaceFragment.newInstance()
    }

    override fun pharmacySurfaceResultScreen() = FragmentScreen {
        PharmacySurfaceResultFragment.newInstance()
    }

    // PharmacyDoses
    override fun pharmacyDosesScreen() = FragmentScreen {
        PharmacyDosesFragment.newInstance()
    }

    override fun pharmacyDosesResultScreen() = FragmentScreen {
        PharmacyDosesResultFragment.newInstance()
    }

    // PharmacyCRI
    override fun pharmacyCRIScreen() = FragmentScreen {
        PharmacyCRIFragment.newInstance()
    }

    override fun pharmacyCRIResultScreen() = FragmentScreen {
        PharmacyCRIResultFragment.newInstance()
    }
    //endregion

    //region Fluids
    override fun fluidsScreen() = FragmentScreen {
        FluidsFragment.newInstance()
    }
    //endregion

    //region Hematology
    override fun hematologyScreen() = FragmentScreen {
        HematologyFragment.newInstance()
    }
    //endregion

    //region Conversions
    override fun conversionsScreen() = FragmentScreen {
        ConversionsFragment.newInstance()
    }
    //endregion

    //region Settings
    override fun settingsScreen() = FragmentScreen {
        SettingsFragment.newInstance()
    }
    //endregion

    //region Calculator
    override fun calculatorScreen() = FragmentScreen {
        CalculatorFragment.newInstance()
    }

    override fun calculatorKeyboardScreen() = FragmentScreen {
        CalculatorKeyboardFragment.newInstance()
    }
    //endregion

    //region Timer
    override fun timerScreen() = FragmentScreen {
        TimerFragment.newInstance()
    }

    override fun webViewScreen() = FragmentScreen {
        VetMedicalViewFragment.newInstance()
    }

    override fun editTextScreen() = FragmentScreen {
        EditTextFragment.newInstance()
    }
    //endregion
}