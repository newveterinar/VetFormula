package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.calculator

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentCalculatorBinding

class CalculatorFragment :
    BaseFragment<FragmentCalculatorBinding>(FragmentCalculatorBinding::inflate) {
    /** Задание переменных */ //region
    // Навигация
    private val navigationButtons = arrayOfNulls<View>(size = 3)
    // ViewModel
    private lateinit var model: CalculatorFragmentViewModel
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация кнопок
        initNavigationButton()
        // Инициализация ViewModel
        initViewModel()
    }

    // Инициализация кнопок
    private fun initNavigationButton() {
        binding.apply {
            navigationButtons.also {
                it[0] = this.calculatorPreviousButtonContainer
                it[1] = this.calculatorSurfaceButtonContainer
                it[2] = this.calculatorAboutButton
            }
        }
        navigationButtons.forEachIndexed { index, button ->
            button?.setOnClickListener {
                when (index) {
                    0 -> model.router.exit()
                    1 -> Toast.makeText(requireContext(), "Кнопка не назначена", Toast.LENGTH_SHORT).show()
                    2 -> model.router.navigateTo(model.screens.aboutScreen())
                    else ->{
                        Toast.makeText(requireContext(), "Кнопка не назначена", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    // Инициализация ViewModel
    private fun initViewModel() {
        model = ViewModelProvider(this).get(CalculatorFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): CalculatorFragment = CalculatorFragment()
    }
}