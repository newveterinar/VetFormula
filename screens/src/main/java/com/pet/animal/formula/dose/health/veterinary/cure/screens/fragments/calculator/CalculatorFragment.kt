package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.calculator

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentCalculatorBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import com.pet.animal.formula.dose.health.veterinary.cure.utils.NUMBER_NAVIGATION_BUTTONS_ON_INPUT_DATA_SCREENS
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class CalculatorFragment:
    BaseFragment<FragmentCalculatorBinding>(FragmentCalculatorBinding::inflate) {
    /** Задание переменных */ //region
    // Навигация
    private val navigationButtons =
        arrayOfNulls<View>(size = NUMBER_NAVIGATION_BUTTONS_ON_INPUT_DATA_SCREENS)
    // ViewModel
    private lateinit var viewModel: CalculatorFragmentViewModel
    // ShowCalculatorFragmentScope
    private lateinit var showCalculatorFragmentScope: Scope
    // newInstance для данного класса
    companion object {
        fun newInstance(): CalculatorFragment = CalculatorFragment()
    }
    //endregion

    /** Работа со Scope */ //region
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Задание Scope для данного фрагмента
        showCalculatorFragmentScope = KoinJavaComponent.getKoin().getOrCreateScope(
            FragmentScope.SHOW_CALCULATOR_FRAGMENT_SCOPE,
            named(FragmentScope.SHOW_CALCULATOR_FRAGMENT_SCOPE)
        )
    }
    override fun onDetach() {
        // Удаление скоупа для данного фрагмента
        showCalculatorFragmentScope.close()
        super.onDetach()
    }
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
            }
        }
        navigationButtons.forEachIndexed { index, button ->
            button?.setOnClickListener {
                when (index) {
                    0 -> viewModel.router.exit()
                    1 -> viewModel.router.navigateTo(viewModel.screens.calculatorKeyboardScreen())
                    else -> {
                        Toast.makeText(requireContext(),
                            requireActivity().resources.getString(
                                R.string.error_button_is_not_assigned), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    // Инициализация ViewModel
    private fun initViewModel() {
        val _viewModel: CalculatorFragmentViewModel by showCalculatorFragmentScope.inject()
        viewModel = _viewModel
    }
}