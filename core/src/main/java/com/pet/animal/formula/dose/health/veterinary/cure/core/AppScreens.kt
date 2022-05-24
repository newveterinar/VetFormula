package com.pet.animal.formula.dose.health.veterinary.cure.core

import com.github.terrakok.cicerone.androidx.FragmentScreen

interface AppScreens {
    fun mainScreen(): FragmentScreen
    fun pharmacyScreen(): FragmentScreen
    fun pharmacySurfaceScreen(): FragmentScreen
    fun pharmacySurfaceResultScreen(): FragmentScreen
    fun aboutScreen(): FragmentScreen
    fun fluidsScreen(): FragmentScreen
    fun hematologyScreen(): FragmentScreen
    fun conversionsScreen(): FragmentScreen
    fun settingsScreen(): FragmentScreen
    fun calculatorScreen(): FragmentScreen
    fun doseScreen(): FragmentScreen
    fun criScreen(): FragmentScreen
    fun pharmacyDosesResultScreen(): FragmentScreen
    fun timerScreen(): FragmentScreen
    fun webViewScreen(): FragmentScreen
    fun editTextScreen(): FragmentScreen

}