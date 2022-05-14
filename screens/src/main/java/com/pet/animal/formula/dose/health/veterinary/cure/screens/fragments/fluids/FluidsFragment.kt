package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.fluids

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentFluidsBinding

class FluidsFragment : BaseFragment<FragmentFluidsBinding>(FragmentFluidsBinding::inflate) {
    /** Задание переменных */ //region
    // Навигация
    private val navigationButtons = arrayOfNulls<View>(size = 3)

    // ViewModel
    private lateinit var model: FluidsFragmentViewModel
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
                it[2] = this.fluidsAboutButton
            }
        }

        navigationButtons.forEachIndexed { index, button ->
            button?.setOnClickListener {
                when (index) {
                    0 -> model.router.exit()
                    1 -> Toast.makeText(requireContext(), "Кнопка не назначена", Toast.LENGTH_SHORT)
                        .show()
                    2 -> model.router.navigateTo(model.screens.aboutScreen())
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
        model = ViewModelProvider(this).get(FluidsFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): FluidsFragment = FluidsFragment()
    }
}