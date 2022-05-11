package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.hematology

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentHematologyBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.AppScreensImpl
import org.koin.java.KoinJavaComponent

class HematologyFragment :
    BaseFragment<FragmentHematologyBinding>(FragmentHematologyBinding::inflate) {

    /** Задание переменных */ //region
    // Навигация
    private val screens: AppScreensImpl = KoinJavaComponent.getKoin().get()
    private val router: Router = KoinJavaComponent.getKoin().get()
    lateinit var buttonToHematologyScreen: ConstraintLayout
    lateinit var buttonToHematologySurfaceScreen: ConstraintLayout
    lateinit var buttonToAboutScreen: ImageView

    // ViewModel
    lateinit var model: ViewModel
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
        buttonToHematologyScreen = binding.hematologyPreviousButtonContainer
        buttonToHematologyScreen.setOnClickListener {
            requireActivity().onBackPressed()
        }
        buttonToHematologySurfaceScreen = binding.hematologySurfaceButtonContainer
        buttonToHematologySurfaceScreen.setOnClickListener {
//            router.navigateTo(screens.fluidsSurfaceScreen())
        }
        buttonToAboutScreen = binding.hematologyAboutButton
        buttonToAboutScreen.setOnClickListener {
            router.navigateTo(screens.aboutScreen())
        }
    }

    // Инициализация ViewModel
    fun initViewModel() {
        model = ViewModelProvider(this).get(HematologyFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): HematologyFragment = HematologyFragment()
    }
}