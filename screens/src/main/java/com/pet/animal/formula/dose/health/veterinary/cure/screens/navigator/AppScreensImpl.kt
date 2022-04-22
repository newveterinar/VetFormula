package com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.about.AboutFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.PharmacyFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.PharmacySurfaceCalculateFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.PharmacySurfaceCalculateResultFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.PharmacySurfaceFragment

class AppScreensImpl: AppScreens {
    override fun pharmacyScreen() = FragmentScreen {
        PharmacyFragment.newInstance()
    }

    override fun pharmacySurfaceScreen() = FragmentScreen {
        PharmacySurfaceFragment.newInstance()
    }

    override fun pharmacySurfaceCalculateScreen() = FragmentScreen {
        PharmacySurfaceCalculateFragment.newInstance()
    }

    override fun pharmacySurfaceCalculateResultScreen() = FragmentScreen {
        PharmacySurfaceCalculateResultFragment.newInstance()
    }

    override fun aboutScreen() = FragmentScreen {
        AboutFragment.newInstance()
    }
}