package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.calculator

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentCalculatorBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.AppScreensImpl
import org.koin.java.KoinJavaComponent

class CalculatorFragment :
    BaseFragment<FragmentCalculatorBinding>(FragmentCalculatorBinding::inflate) {

    /** Задание переменных */ //region
    // Навигация
    lateinit var buttonToCalculatorScreen: ConstraintLayout
    lateinit var buttonToCalculatorSurfaceScreen: ConstraintLayout
    lateinit var buttonToAboutScreen: ImageView

    // ViewModel
    lateinit var model: CalculatorFragmentViewModel
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
        buttonToCalculatorScreen = binding.calculatorPreviousButtonContainer
        buttonToCalculatorScreen.setOnClickListener {
            model.router.exit()
        }
        buttonToCalculatorSurfaceScreen = binding.calculatorSurfaceButtonContainer
        buttonToCalculatorSurfaceScreen.setOnClickListener {
//            model.router.navigateTo(model.screens.fluidsSurfaceScreen())
       }
        buttonToAboutScreen = binding.calculatorAboutButton
        buttonToAboutScreen.setOnClickListener {
            model.router.navigateTo(model.screens.aboutScreen())
        }
    }

    // Инициализация ViewModel
    fun initViewModel() {
        model = ViewModelProvider(this).get(CalculatorFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): CalculatorFragment = CalculatorFragment()
    }
}