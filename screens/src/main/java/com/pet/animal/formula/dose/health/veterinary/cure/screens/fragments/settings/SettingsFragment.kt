package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.settings

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentSettingsBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.AppScreensImpl
import org.koin.java.KoinJavaComponent

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    /** Задание переменных */ //region
    // Навигация
    private val screens: AppScreensImpl = KoinJavaComponent.getKoin().get()
    private val router: Router = KoinJavaComponent.getKoin().get()
    lateinit var buttonToSettingsScreen: ConstraintLayout
    lateinit var buttonToSettingsSurfaceScreen: ConstraintLayout
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
        buttonToSettingsScreen = binding.settingsPreviousButtonContainer
        buttonToSettingsScreen.setOnClickListener {
            requireActivity().onBackPressed()
        }
        buttonToSettingsSurfaceScreen = binding.settingsSurfaceButtonContainer
        buttonToSettingsSurfaceScreen.setOnClickListener {
//            router.navigateTo(screens.fluidsSurfaceScreen())
      }
        buttonToAboutScreen = binding.settingsAboutButton
        buttonToAboutScreen.setOnClickListener {
            router.navigateTo(screens.aboutScreen())
        }
    }

    // Инициализация ViewModel
    fun initViewModel() {
        model = ViewModelProvider(this).get(SettingsFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): SettingsFragment = SettingsFragment()
    }
}