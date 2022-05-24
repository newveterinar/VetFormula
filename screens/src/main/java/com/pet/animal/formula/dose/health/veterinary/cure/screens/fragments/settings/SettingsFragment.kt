package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.settings

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentSettingsBinding

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    /** Задание переменных */ //region
    // Навигация
    private val navigationButtons = arrayOfNulls<View>(size = 1)

    // ViewModel
    lateinit var viewModel: SettingsFragmentViewModel
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация кнопок
        initNavigationButtons()
        // Инициализация ViewModel
        initViewModel()
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

    // Инициализация ViewModel
    fun initViewModel() {
        viewModel = ViewModelProvider(this).get(SettingsFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): SettingsFragment = SettingsFragment()
    }
}