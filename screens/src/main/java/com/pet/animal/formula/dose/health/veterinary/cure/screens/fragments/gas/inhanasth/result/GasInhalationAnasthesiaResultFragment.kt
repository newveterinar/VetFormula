package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.gas.inhanasth.result

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentGasesAnihalationAnasthesiaResultBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import com.pet.animal.formula.dose.health.veterinary.cure.utils.NUMBER_NAVIGATION_BUTTONS_ON_OUTPUT_DATA_SCREENS
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.convertListValuefFieldToListIntDimensions
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.createStringResult
import com.pet.animal.formula.dose.health.veterinary.cure.utils.settings.SettingsImpl
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class GasInhalationAnasthesiaResultFragment: BaseFragment<FragmentGasesAnihalationAnasthesiaResultBinding>(
    FragmentGasesAnihalationAnasthesiaResultBinding::inflate) {
    /** Задание переменных */ //region
    // Установка типа формулы для текущего окна
    private val screenType: ScreenType = ScreenType.GASES_INHALATION_ANESTHESIA
    // Навигация
    private val navigationButtons = arrayOfNulls<View>(
        size = NUMBER_NAVIGATION_BUTTONS_ON_OUTPUT_DATA_SCREENS
    )
    // Элементы для вывода результирующей информации
    private val resultsValueFields: MutableList<TextView> = mutableListOf()
    // ViewModel
    private lateinit var viewModel: GasInhalationAnasthesiaResultFragmentViewModel
    // ShowPharmacySurfaceResultFragmentScope
    private lateinit var showGasInhalationAnasthesiaResultFragmentScope: Scope
    // SettingsImpl
    private val settings: SettingsImpl = KoinJavaComponent.getKoin().get()
    // newInstance для данного класса
    companion object {
        fun newInstance(): GasInhalationAnasthesiaResultFragment =
            GasInhalationAnasthesiaResultFragment()
    }
    //endregion

    /** Работа со Scope */ //region
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Задание Scope для данного фрагмента
        showGasInhalationAnasthesiaResultFragmentScope = KoinJavaComponent.getKoin().getOrCreateScope(
            FragmentScope.SHOW_GAS_INHALATION_ANASTHESIA_RESULT_FRAGMENT_SCOPE,
            named(FragmentScope.SHOW_GAS_INHALATION_ANASTHESIA_RESULT_FRAGMENT_SCOPE)
        )
    }
    override fun onDetach() {
        // Удаление скоупа для данного фрагмента
        showGasInhalationAnasthesiaResultFragmentScope.close()
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
                it[0] = this.previousButtonContainer
            }
        }

        navigationButtons.forEachIndexed { index, button ->
            button?.let {
                it.setOnClickListener {
                    when (index) {
                        0 -> viewModel.router.exit()
                        else -> {
                            Toast.makeText(
                                requireContext(),
                                requireActivity().resources.getString(
                                    R.string.error_button_is_not_assigned
                                ), Toast.LENGTH_SHORT
                            ).show()
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
        resultsValueFields.add(binding.gasesInhalationAnasthesiaResultText)
    }

    // Инициализация ViewModel
    fun initViewModel() {
        val _viewModel: GasInhalationAnasthesiaResultFragmentViewModel by
        showGasInhalationAnasthesiaResultFragmentScope.inject()
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
            listOf(),
            settings.getInputedScreenData().valueFields.convertListValuefFieldToListIntDimensions(),
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