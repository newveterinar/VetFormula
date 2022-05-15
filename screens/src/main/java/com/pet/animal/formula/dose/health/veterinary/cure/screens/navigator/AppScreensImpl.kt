package com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.pet.animal.formula.dose.health.veterinary.cure.core.AppScreens
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.about.AboutFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.calculator.CalculatorFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.conversions.ConversionsFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.fluids.FluidsFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.hematology.HematologyFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.mainscreen.MainScreenFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.PharmacyFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.cri.PharmacyCRIFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.doses.PharmacyDosesFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.doses.result.PharmacyDosesResultFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.result.PharmacySurfaceResultFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface.PharmacySurfaceFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.settings.SettingsFragment

class AppScreensImpl : AppScreens {
    //region Системные окна
    override fun mainScreen() = FragmentScreen {
        MainScreenFragment.newInstance()
    }

    override fun aboutScreen() = FragmentScreen {
        AboutFragment.newInstance()
    }
    //endregion

    //region Pharmacy
    override fun pharmacyScreen() = FragmentScreen {
        PharmacyFragment.newInstance()
    }

    override fun pharmacySurfaceScreen() = FragmentScreen {
        PharmacySurfaceFragment.newInstance()
    }

    override fun pharmacySurfaceResultScreen() = FragmentScreen {
        PharmacySurfaceResultFragment.newInstance()
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

    //region Dose
    override fun doseScreen() = FragmentScreen {
        PharmacyDosesFragment.newInstance()
    }
    //endregion

    //region CRI
    override fun criScreen() = FragmentScreen {
        PharmacyCRIFragment.newInstance()
    }

    override fun pharmacyDosesResultScreen() = FragmentScreen {
        PharmacyDosesResultFragment.newInstance()
    }
    //endregion
}