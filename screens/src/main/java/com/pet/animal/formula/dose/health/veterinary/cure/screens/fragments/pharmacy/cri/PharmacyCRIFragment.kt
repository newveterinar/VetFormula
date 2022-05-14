package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.cri

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacyCriBinding

class PharmacyCRIFragment :
    BaseFragment<FragmentPharmacyCriBinding>(FragmentPharmacyCriBinding::inflate) {

    /** Задание переменных */ //region
    // Навигация
    private val navigationButtons = arrayOfNulls<View>(size = 2)

    // ViewModel
    private lateinit var model: PharmacyCRIFragmentViewModel
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
                it[0] = this.pharmacyPreviousButtonContainer
                it[1] = this.pharmacyAboutButton
            }
        }
        navigationButtons.forEachIndexed { index, button ->
            button?.setOnClickListener {
                when (index) {
                    0 -> model.router.exit()
                    1 -> model.router.navigateTo(model.screens.aboutScreen())
                    else ->{
                        Toast.makeText(requireContext(), "Кнопка не назначена", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    // Инициализация ViewModel
    private fun initViewModel() {
        model = ViewModelProvider(this).get(PharmacyCRIFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): PharmacyCRIFragment = PharmacyCRIFragment()
    }
}