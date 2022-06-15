package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.doses.result

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacyDosesResultBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import com.pet.animal.formula.dose.health.veterinary.cure.utils.NUMBER_NAVIGATION_BUTTONS_ON_OUTPUT_DATA_SCREENS
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.convertListValueFieldToListDoubleValue
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.convertListValueFieldToListIntDimensions
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.convertListValueFieldToListStringValue
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.createStringResult
import com.pet.animal.formula.dose.health.veterinary.cure.utils.settings.SettingsImpl
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class PharmacyDosesResultFragment:
    BaseFragment<FragmentPharmacyDosesResultBinding>(FragmentPharmacyDosesResultBinding::inflate) {
    /** Задание переменных */ //region
    // Установка типа формулы для текущего окна
    private val screenType: ScreenType = ScreenType.PHARMACY_DOSES
    // Навигация
    private val navigationButtons = arrayOfNulls<View>(
        size = NUMBER_NAVIGATION_BUTTONS_ON_OUTPUT_DATA_SCREENS)
    // Элементы для вывода результирующей информации
    private val resultsValueFields: MutableList<TextView> = mutableListOf()
    // ViewModel
    private lateinit var viewModel: PharmacyDosesResultFragmentViewModel
    // ShowPharmacyDosesResultFragmentScope
    private lateinit var showPharmacyDosesResultFragmentScope: Scope
    // SettingsImpl
    private val settings: SettingsImpl = KoinJavaComponent.getKoin().get()
    // newInstance для данного класса
    companion object {
        fun newInstance() = PharmacyDosesResultFragment()
    }
    //endregion

    /** Работа со Scope */ //region
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Задание Scope для данного фрагмента
        showPharmacyDosesResultFragmentScope = KoinJavaComponent.getKoin().getOrCreateScope(
            FragmentScope.SHOW_PHARMACY_DOSES_RESULT_FRAGMENT_SCOPE,
            named(FragmentScope.SHOW_PHARMACY_DOSES_RESULT_FRAGMENT_SCOPE)
        )
    }
    override fun onDetach() {
        // Удаление скоупа для данного фрагмента
        showPharmacyDosesResultFragmentScope.close()
        super.onDetach()
    }
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализация кнопок
        initNavigationButtons()
        // Инициализация элементов для вывода результирующей информации
        initResultValueVields()
        // Инициализация ViewModel
        initViewModel()
    }

    // Инициализация кнопок
    private fun initNavigationButtons() {
        binding.apply {
            navigationButtons.also {
                it[0] = this.pharmacyPreviousButtonContainer
            }
        }

        navigationButtons.forEachIndexed { index, button ->
            button?.let {
                it.setOnClickListener {
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
    }

    // Инициализация элементов для вывода результирующей информации
    private fun initResultValueVields() {
        // Очистка переменной
        resultsValueFields.clear()
        // Задание полей для вывода результирующей информации
        resultsValueFields.add(binding.pharmacyResultVolumeText)
        resultsValueFields.add(binding.pharmacyResultMassTitleText)
    }

    // Инициализация ViewModel
    private fun initViewModel() {
        val _viewModel: PharmacyDosesResultFragmentViewModel by
        showPharmacyDosesResultFragmentScope.inject()
        viewModel = _viewModel
        // Отображение текущих значений числовых поле и списков
        viewModel.subscribe().observe(viewLifecycleOwner) {
            renderData(it)
        }
        saveData(false)
    }

    // Сохранение текущего состояния всех числовых полей и списков
    private fun saveData(isGoToResultScreen: Boolean) {
        viewModel.saveData(
            screenType,
            listOf(),
            listOf(),
//            settings.getInputedScreenData().valueFields.convertListValueFieldToListStringValue(),
            listOf(),
//            settings.getInputedScreenData().valueFields.convertListValueFieldToListDoubleValue(),
            listOf(),
//            settings.getInputedScreenData().valueFields.convertListValueFieldToListIntDimensions(),
            isGoToResultScreen
        )
    }

    // Отображение изменения LiveData у viewModel
    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                appState.screenData.let {
                    if (!it.isGoToResultScreen) {
                        resultsValueFields.forEachIndexed { index, resultValueTextView ->
                            resultValueTextView.createStringResult(screenType.ordinal,
                                it.resultValueField, index,
                                settings.getInputedScreenData().valueFields)
                        }
                    }
                }
            }
            is AppState.Loading -> {
                if (appState.progress != null) {
                } else {
                }
            }
            is AppState.Error -> {
                Toast.makeText(
                    requireContext(), requireContext().getString(
                        R.string.error_appstate_not_loaded_for_fragment
                    ),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}