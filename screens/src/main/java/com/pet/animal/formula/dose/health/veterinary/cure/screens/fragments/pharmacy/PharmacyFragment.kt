package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacyBinding

class PharmacyFragment : BaseFragment<FragmentPharmacyBinding>(FragmentPharmacyBinding::inflate) {
    /** Задание переменных */ //region
    // Навигация
    private val navigationButtons = arrayOfNulls<View>(5)

    // ViewModel
    private lateinit var viewModel: PharmacyFragmentViewModel
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
// Инициализация ViewModel
        initViewModel()
        // Инициализация кнопок
        initNavigationButtons()

    }

    // Инициализация кнопок
    private fun initNavigationButtons() {
        binding.apply {
            navigationButtons.also {
                it[0] = this.pharmacyPreviousButtonContainer
                it[1] = this.pharmacySurfaceButtonContainer
                it[2] = this.pharmacyDoseButtonContainer
                it[3] = this.pharmacyCriButtonContainer
            }


        }

        navigationButtons.forEachIndexed { index, button ->
            button?.setOnClickListener {
                when (index) {
                    0 -> viewModel.router.exit()
                    1 -> viewModel.router.navigateTo(viewModel.screens.pharmacySurfaceScreen())
                    2 -> viewModel.router.navigateTo(viewModel.screens.doseScreen())
                    3 -> viewModel.router.navigateTo(viewModel.screens.criScreen())
                    else -> {
                         Toast.makeText(requireContext(), requireActivity().resources.getString(
                            R.string.error_button_is_not_assigned), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    // Инициализация ViewModel
    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(PharmacyFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): PharmacyFragment = PharmacyFragment()
    }
}