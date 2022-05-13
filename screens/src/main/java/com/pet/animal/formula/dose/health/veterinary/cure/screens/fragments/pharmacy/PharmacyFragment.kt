package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacyBinding

class PharmacyFragment : BaseFragment<FragmentPharmacyBinding>(FragmentPharmacyBinding::inflate) {
    /** Задание переменных */ //region
    // Навигация
    private val navigationButtons = mutableListOf<View>()
    // ViewModel
    lateinit var model: PharmacyFragmentViewModel
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
// Инициализация ViewModel
        initViewModel()
        // Инициализация кнопок
        initButtons()

    }

    // Инициализация кнопок
    fun initButtons() {
        binding.apply {
            navigationButtons.addAll(listOf(
                this.pharmacyPreviousButtonContainer,
                this.pharmacySurfaceButtonContainer,
                this.pharmacyDoseButtonContainer,
                this.pharmacyCriButtonContainer,
                this.pharmacyAboutButton
            ))
        }

        navigationButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                when (index) {
                    0 -> model.router.exit()
                    1 -> model.router.navigateTo(model.screens.pharmacySurfaceScreen())
                    2 -> model.router.navigateTo(model.screens.doseScreen())
                    3 -> model.router.navigateTo(model.screens.criScreen())
                    4 -> model.router.navigateTo(model.screens.aboutScreen())
                    else ->{
                        Toast.makeText(requireContext(), "Кнопка не назначена", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    // Инициализация ViewModel
    fun initViewModel() {
        model = ViewModelProvider(this).get(PharmacyFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): PharmacyFragment = PharmacyFragment()
    }
}