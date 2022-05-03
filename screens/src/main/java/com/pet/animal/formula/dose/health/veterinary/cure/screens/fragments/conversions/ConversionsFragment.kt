package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.conversions

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentConversionsBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.AppScreensImpl
import org.koin.java.KoinJavaComponent

class ConversionsFragment :
    BaseFragment<FragmentConversionsBinding>(FragmentConversionsBinding::inflate) {

    /** Задание переменных */ //region
    // Навигация
    private val screens: AppScreensImpl = KoinJavaComponent.getKoin().get()
    private val router: Router = KoinJavaComponent.getKoin().get()
    lateinit var buttonToConversionsScreen: ConstraintLayout
    lateinit var buttonToConversionsSurfaceScreen: ConstraintLayout
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
        buttonToConversionsScreen = binding.conversionsPreviousButtonContainer
        buttonToConversionsScreen.setOnClickListener {
            requireActivity().onBackPressed()
        }
        buttonToConversionsSurfaceScreen = binding.conversionsSurfaceButtonContainer
        buttonToConversionsSurfaceScreen.setOnClickListener {
//            router.navigateTo(screens.fluidsSurfaceScreen())
        }
        buttonToAboutScreen = binding.conversionsAboutButton
        buttonToAboutScreen.setOnClickListener {
            router.navigateTo(screens.aboutScreen())
        }
    }

    // Инициализация ViewModel
    fun initViewModel() {
        model = ViewModelProvider(this).get(ConversionsFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): ConversionsFragment = ConversionsFragment()
    }
}