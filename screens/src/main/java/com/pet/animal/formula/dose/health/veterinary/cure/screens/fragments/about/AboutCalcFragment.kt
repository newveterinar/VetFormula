package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.about

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentAboutBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentAboutCalcBinding

class AboutCalcFragment: BaseFragment<FragmentAboutCalcBinding>(FragmentAboutCalcBinding::inflate) {
    /** Задание переменных */ //region
    // Навигация
    private lateinit var buttonToBackScreen: ImageView

    // ViewModel
    private lateinit var model: AboutCaclFragmentViewModel
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализация кнопок
        initButtons()
        // Инициализация ViewModel
        initViewModel()
    }

    // Инициализация кнопок
    private fun initButtons() {
        buttonToBackScreen = binding.aboutAboutButton
        buttonToBackScreen.setOnClickListener {
            model.router.exit()
        }
    }

    // Инициализация ViewModel
    private fun initViewModel() {
        model = ViewModelProvider(this).get(AboutCaclFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): AboutCalcFragment = AboutCalcFragment()
    }
}