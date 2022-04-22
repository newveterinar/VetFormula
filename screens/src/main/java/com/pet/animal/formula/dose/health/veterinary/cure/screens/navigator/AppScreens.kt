package com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator

import com.github.terrakok.cicerone.androidx.FragmentScreen

interface AppScreens {
    fun pharmacyScreen(): FragmentScreen
    fun pharmacySurfaceScreen(): FragmentScreen
    fun pharmacySurfaceCalculateScreen(): FragmentScreen
    fun pharmacySurfaceCalculateResultScreen(): FragmentScreen
}