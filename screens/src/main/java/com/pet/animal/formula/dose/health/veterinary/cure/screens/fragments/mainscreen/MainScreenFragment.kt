package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.mainscreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentMainscreenBinding

class MainScreenFragment :
    BaseFragment<FragmentMainscreenBinding>(FragmentMainscreenBinding::inflate) {
    /** Задание переменных */ //region

    // Навигация
    private val navigationButtons = arrayOfNulls<View>(size = 8)

    // ViewModel
    private lateinit var model: MainScreenFragmentViewModel
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация кнопок
        initNavigationButtons()
        // Инициализация ViewModel
        initViewModel()
    }

    // Инициализация кнопок
    private fun initNavigationButtons() {
        binding.apply {
            navigationButtons.also{
                it[0] = this.pharmacySurfaceButtonContainer
                it[1] = this.fluidsSurfaceButtonContainer
                it[2] = this.hematologySurfaceButtonContainer
                it[3] = this.conversionsSurfaceButtonContainer
                it[4] = this.settingsSurfaceButtonContainer
                it[5] = this.calculatorSurfaceButtonContainer
                it[6] = this.pharmacyAboutButton
                it[7] = this.timerButton
            }

        }

        navigationButtons.forEachIndexed { index, button ->
            button?.setOnClickListener {
                when (index) {
                    0 -> model.router.navigateTo(model.screens.pharmacyScreen())
                    1 -> model.router.navigateTo(model.screens.fluidsScreen())
                    2 -> model.router.navigateTo(model.screens.hematologyScreen())
                    3 -> model.router.navigateTo(model.screens.conversionsScreen())
                    4 -> model.router.navigateTo(model.screens.settingsScreen())
                    5 -> model.router.navigateTo(model.screens.calculatorScreen())
                    6 -> model.router.navigateTo(model.screens.aboutScreen())
                    7 -> model.router.navigateTo(model.screens.timerScreen())
                    else -> {
                        Toast.makeText(requireContext(), "Кнопка не назначена", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    // Инициализация ViewModel
    private fun initViewModel() {
        model = ViewModelProvider(this).get(MainScreenFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): MainScreenFragment = MainScreenFragment()
    }
}