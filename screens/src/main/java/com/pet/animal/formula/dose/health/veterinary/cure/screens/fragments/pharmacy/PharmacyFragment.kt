package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacyBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.AppScreensImpl
import org.koin.java.KoinJavaComponent

class PharmacyFragment : BaseFragment<FragmentPharmacyBinding>(FragmentPharmacyBinding::inflate) {
    /** Задание переменных */ //region
    // Навигация
    lateinit var buttonToPharmacyScreen: ConstraintLayout
    lateinit var buttonToPharmacySurfaceScreen: ConstraintLayout
    lateinit var buttonPharmacyDoseScreen: ConstraintLayout
    lateinit var buttonPharmacyCRIScreen: ConstraintLayout
    lateinit var buttonToAboutScreen: ImageView

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
        buttonToPharmacyScreen = binding.pharmacyPreviousButtonContainer
        buttonToPharmacyScreen.setOnClickListener {
            requireActivity().onBackPressed()
        }
        buttonToPharmacySurfaceScreen = binding.pharmacySurfaceButtonContainer
        buttonToPharmacySurfaceScreen.setOnClickListener {
            model.router.navigateTo(model.screens.pharmacySurfaceScreen())
        }
        buttonPharmacyDoseScreen = binding.pharmacyDoseButtonContainer
        buttonPharmacyDoseScreen.setOnClickListener {
            model.router.navigateTo(model.screens.doseScreen())
        }
        buttonPharmacyCRIScreen = binding.pharmacyCriButtonContainer
        buttonPharmacyCRIScreen.setOnClickListener {
            model.router.navigateTo(model.screens.criScreen())
        }
        buttonToAboutScreen = binding.pharmacyAboutButton
        buttonToAboutScreen.setOnClickListener {
            model.router.navigateTo(model.screens.aboutScreen())
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