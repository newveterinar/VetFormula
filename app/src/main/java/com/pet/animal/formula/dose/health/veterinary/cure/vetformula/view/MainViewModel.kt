package com.pet.animal.formula.dose.health.veterinary.cure.vetformula.view

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.pet.animal.formula.dose.health.veterinary.cure.core.AppScreens
import org.koin.java.KoinJavaComponent

class MainViewModel: ViewModel() {
    val screens: AppScreens = KoinJavaComponent.getKoin().get()
    val router: Router = KoinJavaComponent.getKoin().get()
}