package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.mainscreen

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
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
    private val navigationButtons = mutableListOf<View>()

    // ViewModel
    lateinit var model: MainScreenFragmentViewModel
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
        binding.apply {
            navigationButtons.addAll(listOf(
                this.pharmacySurfaceButtonContainer,
                this.fluidsSurfaceButtonContainer,
                this.hematologySurfaceButtonContainer,
                this.conversionsSurfaceButtonContainer,
                this.settingsSurfaceButtonContainer,
                this.calculatorSurfaceButtonContainer,
                this.pharmacyAboutButton,
            ))
        }

        navigationButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                when (index) {
                    0 -> model.router.navigateTo(model.screens.pharmacyScreen())
                    1 -> model.router.navigateTo(model.screens.fluidsScreen())
                    2 -> model.router.navigateTo(model.screens.hematologyScreen())
                    3 -> model.router.navigateTo(model.screens.conversionsScreen())
                    4 -> model.router.navigateTo(model.screens.settingsScreen())
                    5 -> model.router.navigateTo(model.screens.calculatorScreen())
                    6 -> model.router.navigateTo(model.screens.aboutScreen())
                    else ->{
                        Toast.makeText(requireContext(), "Кнопка не назначена", Toast.LENGTH_SHORT).show()
                    }
                }
            }
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