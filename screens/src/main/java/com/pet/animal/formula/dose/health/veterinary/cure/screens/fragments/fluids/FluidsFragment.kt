package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.fluids

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentFluidsBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.NUMBER_NAVIGATION_BUTTONS_ON_INPUT_DATA_SCREENS

class FluidsFragment : BaseFragment<FragmentFluidsBinding>(FragmentFluidsBinding::inflate) {
    /** Задание переменных */ //region
    // Навигация
    private val navigationButtons =
        arrayOfNulls<View>(size = NUMBER_NAVIGATION_BUTTONS_ON_INPUT_DATA_SCREENS)

    // ViewModel
    private lateinit var viewModel: FluidsFragmentViewModel
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
            navigationButtons.also {
                it[0] = this.fluidsPreviousButtonContainer
                it[1] = this.fluidsSurfaceButtonContainer
            }
        }

        navigationButtons.forEachIndexed { index, button ->
            button?.setOnClickListener {
                when (index) {
                    0 -> viewModel.router.exit()
                    1 -> Toast.makeText(
                        requireContext(), requireActivity().resources.getString(
                            R.string.error_button_is_not_assigned
                        ), Toast.LENGTH_SHORT
                    ).show()
                    else -> {
                        Toast.makeText(
                            requireContext(), requireActivity().resources.getString(
                                R.string.error_button_is_not_assigned
                            ), Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    // Инициализация ViewModel
    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(FluidsFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): FluidsFragment = FluidsFragment()
    }
}