package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.doses

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacyDosesBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.AppScreensImpl
import org.koin.java.KoinJavaComponent

class PharmacyDosesFragment :
    BaseFragment<FragmentPharmacyDosesBinding>(FragmentPharmacyDosesBinding::inflate) {

    /** Задание переменных */ //region
    // Навигация
    lateinit var buttonToPharmacyScreen: ConstraintLayout
    lateinit var buttonToAboutScreen: ImageView

    // ViewModel
    lateinit var model: PharmacyDosesFragmentViewModel
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
            model.router.exit()
        }
        buttonToAboutScreen = binding.pharmacyAboutButton
        buttonToAboutScreen.setOnClickListener {
            model.router.navigateTo(model.screens.aboutScreen())
        }
    }

    // Инициализация ViewModel
    fun initViewModel() {
        model = ViewModelProvider(this).get(PharmacyDosesFragmentViewModel::class.java)
    }


    companion object {
        fun newInstance(): PharmacyDosesFragment = PharmacyDosesFragment()
    }
}