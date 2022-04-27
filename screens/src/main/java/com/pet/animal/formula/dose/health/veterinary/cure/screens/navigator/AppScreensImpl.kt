package com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.about.AboutFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.fluids.FluidsFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.mainscreen.MainScreenFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.PharmacyFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface.PharmacySurfaceFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface.PharmacySurfaceResultFragment

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
}