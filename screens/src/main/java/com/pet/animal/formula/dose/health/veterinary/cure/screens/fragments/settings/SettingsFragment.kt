package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.settings

import android.os.Bundle
import android.view.View
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.androidx.AppScreen
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentSettingsBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.selected
import com.pet.animal.formula.dose.health.veterinary.cure.utils.language_utils.LocaleHelper
import com.pet.animal.formula.dose.health.veterinary.cure.utils.language_utils.Locales

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    /** Задание переменных */ //region
    // Навигация
    private val navigationButtons = arrayOfNulls<View>(size = 1)
    private lateinit var languageSpinner: Spinner

    // ViewModel
    lateinit var viewModel: SettingsFragmentViewModel
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация кнопок
        initNavigationButtons()
        // Инициализация ViewModel
        initViewModel()
        initLanguageSettingsFields()
    }

    // Инициализация кнопок
    private fun initNavigationButtons() {
        binding.apply {
            navigationButtons.also {
                it[0] = this.settingsPreviousButtonContainer
            }
        }
        navigationButtons.forEachIndexed { index, button ->
            button?.setOnClickListener {
                when (index) {
                    0 -> viewModel.router.exit()
                    else -> {
                        Toast.makeText(requireContext(), requireActivity().resources.getString(
                            R.string.error_button_is_not_assigned), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun initLanguageSettingsFields() {
        languageSpinner = binding.settingsLanguageList
        selectCurrentLocale()
        languageSpinner.selected { position ->
            when (position) {
                2 -> LocaleHelper.setLocale(requireActivity(), Locales.english)
                4 -> LocaleHelper.setLocale(requireActivity(), Locales.german)
                8 -> LocaleHelper.setLocale(requireActivity(), Locales.russian)
            }
        }
    }


    private fun selectCurrentLocale() {
        return when (LocaleHelper.getLocale(requireContext()).language) {
            Locales.english.language -> {
                languageSpinner.setSelection(2, false)
            }
            Locales.german.language -> {
                languageSpinner.setSelection(4, false)
            }
            Locales.russian.language -> {
                languageSpinner.setSelection(8, false)
            }
            else -> {}
        }
    }

    // Инициализация ViewModel
    fun initViewModel() {
        viewModel = ViewModelProvider(this).get(SettingsFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): SettingsFragment = SettingsFragment()
    }
}