package com.pet.animal.formula.dose.health.veterinary.cure.core

import com.github.terrakok.cicerone.androidx.FragmentScreen

interface AppScreens {
    fun mainScreen(): FragmentScreen
    fun pharmacyScreen(): FragmentScreen
    fun pharmacySurfaceScreen(): FragmentScreen
    fun pharmacySurfaceResultScreen(): FragmentScreen
    fun pharmacyDosesScreen(): FragmentScreen
    fun pharmacyDosesResultScreen(): FragmentScreen
    fun pharmacyCRIScreen(): FragmentScreen
    fun pharmacyCRIResultScreen(): FragmentScreen
    fun aboutScreen(): FragmentScreen
    fun infoScreen(): FragmentScreen
    fun fluidsScreen(): FragmentScreen
    fun hematologyScreen(): FragmentScreen
    fun gasScreen(): FragmentScreen
    fun settingsScreen(): FragmentScreen
    fun calculatorScreen(): FragmentScreen
    fun calculatorKeyboardScreen(): FragmentScreen
    fun timerScreen(): FragmentScreen
    fun webViewScreen(): FragmentScreen
    fun editTextScreen(): FragmentScreen
    fun aboutCalc(): FragmentScreen
    fun aboutTimer(): FragmentScreen
}