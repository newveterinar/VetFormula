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
import com.pet.animal.formula.dose.health.veterinary.cure.screens.getItemByValue
import com.pet.animal.formula.dose.health.veterinary.cure.screens.onItemSelected
import com.pet.animal.formula.dose.health.veterinary.cure.screens.selectItemByValue
import com.pet.animal.formula.dose.health.veterinary.cure.utils.language_utils.LocaleHelper
import com.pet.animal.formula.dose.health.veterinary.cure.utils.language_utils.Locales
import java.util.*

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    /** Задание переменных */ //region
    // Навигация
    private val navigationButtons = arrayOfNulls<View>(size = 1)
    private lateinit var languageSpinner: Spinner
    private var lightthemebutton: Chip? = null
    private var darkthemebutton: Chip? = null

    // ViewModel
    lateinit var viewModel: SettingsFragmentViewModel
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lightthemebutton = view.findViewById(R.id.light_theme_button)
        lightthemebutton?.let {
            it.setOnClickListener {
                val sharedPreferences: SharedPreferences =
                    requireActivity().getSharedPreferences(SHARED_PREFERENCES_KEY,
                        AppCompatActivity.MODE_PRIVATE
                    )
                var sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
                sharedPreferencesEditor.putBoolean(SHARED_PREFERENCES_THEME_KEY, false)
                sharedPreferencesEditor.apply()
                requireActivity().recreate()
            }
        }
        darkthemebutton = view.findViewById(R.id.dark_theme_button)
        darkthemebutton?.let {
            it.setOnClickListener {
                val sharedPreferences: SharedPreferences =
                    requireActivity().getSharedPreferences(SHARED_PREFERENCES_KEY,
                        AppCompatActivity.MODE_PRIVATE
                    )
                var sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()
                sharedPreferencesEditor.putBoolean(SHARED_PREFERENCES_THEME_KEY, true)
                sharedPreferencesEditor.apply()
                requireActivity().recreate()
            }
        }

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

    companion object {
        fun newInstance(): SettingsFragment = SettingsFragment()
    }
}