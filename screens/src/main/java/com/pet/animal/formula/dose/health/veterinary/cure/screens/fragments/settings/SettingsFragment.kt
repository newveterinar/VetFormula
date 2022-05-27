package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentSettingsBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.SHARED_PREFERENCES_KEY
import com.pet.animal.formula.dose.health.veterinary.cure.utils.SHARED_PREFERENCES_THEME_KEY
import com.pet.animal.formula.dose.health.veterinary.cure.screens.onItemSelected
import com.pet.animal.formula.dose.health.veterinary.cure.screens.selectItemByValue
import com.pet.animal.formula.dose.health.veterinary.cure.utils.language.LocaleHelper
import com.pet.animal.formula.dose.health.veterinary.cure.utils.language.Locales
import java.util.*

class SettingsFragment: BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {
    /** Задание переменных */ //region
    // Навигация
    private val navigationButtons = arrayOfNulls<View>(size = 1)
    // Список с выбором языка
    private lateinit var languageSpinner: Spinner
    // Кнопки для смены темы приложения
    private lateinit var lightThemeButton: Chip
    private lateinit var darkThemeButton: Chip
    // ViewModel
    lateinit var viewModel: SettingsFragmentViewModel
    // NewInstance
    companion object {
        fun newInstance(): SettingsFragment = SettingsFragment()
    }
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализация кнопок переключения темы приложения
        initThemeButtons()
        // Инициализация навигационных кнопок
        initNavigationButtons()
        // Инициализация ViewModel
        initViewModel()
        initLanguageSettingsFields()
    }

    // Инициализация кнопок переключения темы приложения
    private fun initThemeButtons() {
        lightThemeButton = binding.lightThemeButton.also { it.setOnClickListener {
                val sharedPreferences: SharedPreferences =
                    requireActivity().getSharedPreferences(
                        SHARED_PREFERENCES_KEY,
                        AppCompatActivity.MODE_PRIVATE
                    )
                val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
                sharedPreferencesEditor.putBoolean(SHARED_PREFERENCES_THEME_KEY, false)
                sharedPreferencesEditor.apply()
                requireActivity().recreate()
            }
        }
        darkThemeButton = binding.darkThemeButton.also { it.setOnClickListener {
            val sharedPreferences: SharedPreferences =
                requireActivity().getSharedPreferences(
                    SHARED_PREFERENCES_KEY,
                    AppCompatActivity.MODE_PRIVATE
                )
            val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
            sharedPreferencesEditor.putBoolean(SHARED_PREFERENCES_THEME_KEY, true)
            sharedPreferencesEditor.apply()
            requireActivity().recreate()
            }
        }
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
                        Toast.makeText(requireContext(),
                            requireActivity().resources.getString(
                            R.string.error_button_is_not_assigned), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun initLanguageSettingsFields() {
        languageSpinner = binding.settingsLanguageList
        selectCurrentLocale()
        languageSpinner.onItemSelected { position ->
            when (position) {
                0 -> LocaleHelper.setLocale(requireActivity(), Locales.english)
                1 -> LocaleHelper.setLocale(requireActivity(), Locales.russian)
            }
        }
    }


    private fun selectCurrentLocale() {
        return when (LocaleHelper.getLocale(requireContext())) {
            Locales.english -> {
                languageSpinner.selectItemByValue(Locale.getDefault().displayLanguage, false)
            }
            Locales.russian -> {
                languageSpinner.selectItemByValue(Locale.getDefault().displayLanguage, false)
            }
            else -> {}
        }
    }

    // Инициализация ViewModel
    fun initViewModel() {
        viewModel = ViewModelProvider(this).get(SettingsFragmentViewModel::class.java)
    }
}