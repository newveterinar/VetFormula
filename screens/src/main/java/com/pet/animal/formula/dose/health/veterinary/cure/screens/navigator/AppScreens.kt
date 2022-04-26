package com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator

import com.github.terrakok.cicerone.androidx.FragmentScreen

interface AppScreens {
    fun mainScreen(): FragmentScreen
    fun pharmacyScreen(): FragmentScreen
    fun pharmacySurfaceScreen(): FragmentScreen
    fun pharmacySurfaceResultScreen(): FragmentScreen
    fun aboutScreen(): FragmentScreen
    fun upgradeScreen(): FragmentScreen
    fun hematologyScreen(): FragmentScreen
    fun energyScreen(): FragmentScreen
    fun fluidsScreen(): FragmentScreen
    fun fluidsSurfaceScreen(): FragmentScreen
    fun conversionsScreen(): FragmentScreen
}