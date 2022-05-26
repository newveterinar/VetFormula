package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentSettingsBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.SHARED_PREFERENCES_KEY
import com.pet.animal.formula.dose.health.veterinary.cure.utils.SHARED_PREFERENCES_THEME_KEY

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    /** Задание переменных */ //region
    // Навигация
    private val navigationButtons = arrayOfNulls<View>(size = 3)
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
    }

    // Инициализация кнопок
    private fun initNavigationButtons() {
        binding.apply {
            navigationButtons.also {
                it[0] = this.lightThemeButton
                it[1] = this.darkThemeButton
            }
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