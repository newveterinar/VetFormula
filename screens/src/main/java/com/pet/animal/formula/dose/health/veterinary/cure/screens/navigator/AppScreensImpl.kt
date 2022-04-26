package com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.about.AboutFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.mainscreen.MainScreenFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.PharmacyFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.PharmacySurfaceFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.PharmacySurfaceResultFragment

class AppScreensImpl: AppScreens {
    override fun mainScreen() = FragmentScreen {
        MainScreenFragment.newInstance()
    }

    override fun pharmacyScreen() = FragmentScreen {
        PharmacyFragment.newInstance()
    }

    override fun pharmacySurfaceScreen() = FragmentScreen {
        PharmacySurfaceFragment.newInstance()
    }

    override fun pharmacySurfaceResultScreen() = FragmentScreen {
        PharmacySurfaceResultFragment.newInstance()
    }

    override fun aboutScreen() = FragmentScreen {
        AboutFragment.newInstance()
    }
}