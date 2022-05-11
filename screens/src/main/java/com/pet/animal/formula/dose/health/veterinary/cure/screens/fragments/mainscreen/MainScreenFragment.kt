package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.mainscreen

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentMainscreenBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.AppScreensImpl
import org.koin.java.KoinJavaComponent

class MainScreenFragment :
    BaseFragment<FragmentMainscreenBinding>(FragmentMainscreenBinding::inflate) {
    /** Задание переменных */ //region


    // Навигация
    private val screens: AppScreensImpl = KoinJavaComponent.getKoin().get()
    private val router: Router = KoinJavaComponent.getKoin().get()
    lateinit var buttonToPharmacySurfaceScreen: ConstraintLayout
    lateinit var buttonToFluidsSurfaceScreen: ConstraintLayout
    lateinit var buttonToHematologySurfaceScreen: ConstraintLayout
    lateinit var buttonToConversionsSurfaceScreen: ConstraintLayout
    lateinit var buttonToSettingsSurfaceScreen: ConstraintLayout
    lateinit var buttonToCalculatorSurfaceScreen: ConstraintLayout
    lateinit var buttonToAboutScreen: ImageView

    // ViewModel
    lateinit var model: ViewModel
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация кнопок
        initButtons()
        // Инициализация ViewModel
        initViewModel()
    }

    // Инициализация кнопок
    fun initButtons() {
        buttonToPharmacySurfaceScreen = binding.pharmacySurfaceButtonContainer
        buttonToPharmacySurfaceScreen.setOnClickListener {
            router.navigateTo(screens.pharmacyScreen())
        }
        buttonToFluidsSurfaceScreen = binding.fluidsSurfaceButtonContainer
        buttonToFluidsSurfaceScreen.setOnClickListener {
            router.navigateTo(screens.fluidsScreen())
        }
        buttonToHematologySurfaceScreen = binding.hematologySurfaceButtonContainer
        buttonToHematologySurfaceScreen.setOnClickListener {
            router.navigateTo(screens.hematologyScreen())
        }
        buttonToConversionsSurfaceScreen = binding.conversionsSurfaceButtonContainer
        buttonToConversionsSurfaceScreen.setOnClickListener {
            router.navigateTo(screens.conversionsScreen())
        }
        buttonToSettingsSurfaceScreen = binding.settingsSurfaceButtonContainer
        buttonToSettingsSurfaceScreen.setOnClickListener {
            router.navigateTo(screens.settingsScreen())
        }
        buttonToCalculatorSurfaceScreen = binding.calculatorSurfaceButtonContainer
        buttonToCalculatorSurfaceScreen.setOnClickListener {
            router.navigateTo(screens.calculatorScreen())
        }
        buttonToAboutScreen = binding.pharmacyAboutButton
        buttonToAboutScreen.setOnClickListener {
            router.navigateTo(screens.aboutScreen())
        }
    }

    // Инициализация ViewModel
    fun initViewModel() {
        model = ViewModelProvider(this).get(MainScreenFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): MainScreenFragment = MainScreenFragment()
    }
}