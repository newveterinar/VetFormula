package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.calculator.keyboard

import android.content.Context
import android.view.View
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentCalculatorKeyboardBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.calculator.CalculatorFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.calculator.CalculatorFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import com.pet.animal.formula.dose.health.veterinary.cure.utils.NUMBER_NAVIGATION_BUTTONS_ON_INPUT_DATA_SCREENS
import com.pet.animal.formula.dose.health.veterinary.cure.utils.NUMBER_NAVIGATION_BUTTONS_ON_OUTPUT_DATA_SCREENS
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class CalculatorKeyboardFragment:
    BaseFragment<FragmentCalculatorKeyboardBinding>(FragmentCalculatorKeyboardBinding::inflate) {
    /** Задание переменных */ //region
    // Навигация
    private val navigationButtons =
        arrayOfNulls<View>(size = NUMBER_NAVIGATION_BUTTONS_ON_OUTPUT_DATA_SCREENS)
    // ViewModel
    private lateinit var viewModel: CalculatorKeyboardFragmentViewModel
    // ShowCalculatorKeyboardFragmentScope
    private lateinit var showCalculatorKeyboardFragmentScope: Scope
    // newInstance для данного класса
    companion object {
        fun newInstance(): CalculatorKeyboardFragment = CalculatorKeyboardFragment()
    }
    //endregion

    /** Работа со Scope */ //region
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Задание Scope для данного фрагмента
        showCalculatorKeyboardFragmentScope = KoinJavaComponent.getKoin().getOrCreateScope(
            FragmentScope.SHOW_CALCULATOR_KEYBOARD_FRAGMENT_SCOPE,
            named(FragmentScope.SHOW_CALCULATOR_KEYBOARD_FRAGMENT_SCOPE)
        )
    }
    override fun onDetach() {
        // Удаление скоупа для данного фрагмента
        showCalculatorKeyboardFragmentScope.close()
        super.onDetach()
    }
    //endregion
}