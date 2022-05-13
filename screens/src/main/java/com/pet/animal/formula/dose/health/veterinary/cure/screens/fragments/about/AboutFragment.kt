package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.about

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentAboutBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.AppScreensImpl
import org.koin.java.KoinJavaComponent

class AboutFragment : BaseFragment<FragmentAboutBinding>(FragmentAboutBinding::inflate) {
    /** Задание переменных */ //region
    // Навигация
    lateinit var buttonToBackScreen: ImageView

    // ViewModel
    lateinit var model: AboutFragmentViewModel
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
        buttonToBackScreen = binding.aboutAboutButton
        buttonToBackScreen.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    // Инициализация ViewModel
    fun initViewModel() {
        model = ViewModelProvider(this).get(AboutFragmentViewModel::class.java)
    }
    
    companion object {
        fun newInstance(): AboutFragment = AboutFragment()
    }
}