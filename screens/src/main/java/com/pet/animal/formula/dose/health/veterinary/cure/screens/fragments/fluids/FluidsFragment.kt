package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.fluids

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentFluidsBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.AppScreensImpl
import org.koin.java.KoinJavaComponent

class FluidsFragment : BaseFragment<FragmentFluidsBinding>(FragmentFluidsBinding::inflate) {
    /** Задание переменных */ //region
    // Навигация
    private val screens: AppScreensImpl = KoinJavaComponent.getKoin().get()
    private val router: Router = KoinJavaComponent.getKoin().get()
    lateinit var buttonToFluidsScreen: ConstraintLayout
    lateinit var buttonToFluidsSurfaceScreen: ConstraintLayout
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
        buttonToFluidsScreen = binding.fluidsPreviousButtonContainer
        buttonToFluidsScreen.setOnClickListener {
            requireActivity().onBackPressed()
        }
        buttonToFluidsSurfaceScreen = binding.fluidsSurfaceButtonContainer
//        buttonToFluidsSurfaceScreen.setOnClickListener {
//            router.navigateTo(screens.fluidsSurfaceScreen())
//        }
        buttonToAboutScreen = binding.fluidsAboutButton
        buttonToAboutScreen.setOnClickListener {
            router.navigateTo(screens.aboutScreen())
        }
    }

    // Инициализация ViewModel
    fun initViewModel() {
        model = ViewModelProvider(this).get(FluidsFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): FluidsFragment = FluidsFragment()
    }
}