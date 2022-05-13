package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacyBinding

class PharmacyFragment : BaseFragment<FragmentPharmacyBinding>(FragmentPharmacyBinding::inflate) {
    /** Задание переменных */ //region
    // Навигация
//    lateinit var buttonToPharmacyScreen: ConstraintLayout
//    lateinit var buttonToPharmacySurfaceScreen: ConstraintLayout
//    lateinit var buttonPharmacyDoseScreen: ConstraintLayout
//    lateinit var buttonPharmacyCRIScreen: ConstraintLayout
//    lateinit var buttonToAboutScreen: ImageView
    private val buttons = mutableListOf<View>()

    // ViewModel
    lateinit var model: PharmacyFragmentViewModel
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
        binding.apply {
            buttons.add(this.pharmacyPreviousButtonContainer)
            buttons.add(this.pharmacySurfaceButtonContainer)
            buttons.add(this.pharmacyDoseButtonContainer)
            buttons.add(this.pharmacyCriButtonContainer)
            buttons.add(this.pharmacyAboutButton)
        }
        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                when (index){
                    0 -> requireActivity().onBackPressed()
                    1 -> model.router.navigateTo(model.screens.pharmacySurfaceScreen())
                    2 -> model.router.navigateTo(model.screens.doseScreen())
                    3 -> model.router.navigateTo(model.screens.criScreen())
                    4 -> model.router.navigateTo(model.screens.aboutScreen())
                    else -> {}
                }
            }
        }
//        buttonToPharmacyScreen = binding.pharmacyPreviousButtonContainer
//        buttonToPharmacyScreen.setOnClickListener {
//            requireActivity().onBackPressed()
//        }
//        buttonToPharmacySurfaceScreen = binding.pharmacySurfaceButtonContainer
//        buttonToPharmacySurfaceScreen.setOnClickListener {
//            model.router.navigateTo(model.screens.pharmacySurfaceScreen())
//        }
//        buttonPharmacyDoseScreen = binding.pharmacyDoseButtonContainer
//        buttonPharmacyDoseScreen.setOnClickListener {
//            model.router.navigateTo(model.screens.doseScreen())
//        }
//        buttonPharmacyCRIScreen = binding.pharmacyCriButtonContainer
//        buttonPharmacyCRIScreen.setOnClickListener {
//            model.router.navigateTo(model.screens.criScreen())
//        }
//        buttonToAboutScreen = binding.pharmacyAboutButton
//        buttonToAboutScreen.setOnClickListener {
//            model.router.navigateTo(model.screens.aboutScreen())
//        }
    }

    // Инициализация ViewModel
    fun initViewModel() {
        model = ViewModelProvider(this).get(PharmacyFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): PharmacyFragment = PharmacyFragment()
    }
}