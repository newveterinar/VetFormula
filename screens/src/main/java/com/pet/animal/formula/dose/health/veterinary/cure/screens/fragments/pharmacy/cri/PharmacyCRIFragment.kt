package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.cri

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacyCriBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.AppScreensImpl
import org.koin.java.KoinJavaComponent

class PharmacyCRIFragment :
    BaseFragment<FragmentPharmacyCriBinding>(FragmentPharmacyCriBinding::inflate) {

    /** Задание переменных */ //region
    // Навигация
    lateinit var buttonToPharmacyScreen: ConstraintLayout
    lateinit var buttonToAboutScreen: ImageView

    // ViewModel
    lateinit var model: PharmacyCRIFragmentViewModel
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
        buttonToAboutScreen = binding.pharmacyAboutButton
        buttonToAboutScreen.setOnClickListener {
            model.router.navigateTo(model.screens.aboutScreen())
        }
    }

    // Инициализация ViewModel
    fun initViewModel() {
        model = ViewModelProvider(this).get(PharmacyCRIFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): PharmacyCRIFragment = PharmacyCRIFragment()
    }
}