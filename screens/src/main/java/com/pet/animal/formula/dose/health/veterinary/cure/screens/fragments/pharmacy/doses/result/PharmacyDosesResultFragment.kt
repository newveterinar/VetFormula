package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.doses.result

import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.text.style.SuperscriptSpan
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.ScreenData
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacyDosesResultBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.*
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.convertStringToInputDataDimensionType
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
    private val navigationButtons = arrayOfNulls<View>(size = 1)
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
            listOf(),
            listOf(),
            isGoToResultScreen
        )
    }

    // Отображение изменения LiveData у viewModel
    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                appState.screenData.let {
                    if (!it.isGoToResultScreen) {
                        resultsValueFields.forEachIndexed { index, resultValueField ->
                            resultValueField.text = createStringResult(it, index, resultValueField)
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

    // Подготовка строк с результами
    private fun createStringResult(
        screenData: ScreenData, indexData: Int, resultValueField: TextView): SpannableString {
        var initialString: String = "${screenData.resultValueField[indexData].value}"
        lateinit var result: SpannableString
        // Изменение формата размерности текста
        if (initialString.isNotEmpty()) {
            when (resultValueField.tag) {
                OutputDataDimensionType.LENGTH.toString() -> {
                    result = SpannableString(initialString)
                }
                OutputDataDimensionType.SQUARE_LENGTH.toString() -> {
                    initialString += " ${requireActivity().resources.getString(
                        R.string.output_data_dimension_square_length)}"
                    result = SpannableString(initialString)
                    result.setSpan(
                        SuperscriptSpan(),
                        initialString.length - 1,
                        initialString.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    result.setSpan(
                        RelativeSizeSpan(SQUARE_TEXT_RELATIVE_SIZE),
                        initialString.length - 1,
                        initialString.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
                OutputDataDimensionType.VOLUME.toString() -> {
                    val addString: String = requireActivity().resources.getStringArray(
                        R.array.input_data_dimension_concentration_list)[
                            settings.getInputedScreenData().valueFields[2].dimension]
                    if (settings.getInputedScreenData().valueFields[2].dimension !=
                        requireActivity().resources.getStringArray(
                            R.array.input_data_dimension_concentration_list).size - 1)
                    initialString +=
                        " ${addString.substring(addString.lastIndexOf('/') + 1)}"
                    else initialString += " ${requireActivity().resources.getStringArray(
                        R.array.input_data_dimension_volume_list)[0]}"
                    result = SpannableString(initialString)
                }
                OutputDataDimensionType.MASS.toString() -> {
                    val addString: String = requireActivity().resources.getStringArray(
                        R.array.input_data_dimension_mass_dose_per_kg_list)[
                            settings.getInputedScreenData().valueFields[1].dimension]
                    initialString += " $addString"
                    result = SpannableString(initialString)
                }
                OutputDataDimensionType.TIME.toString() -> {
                    result = SpannableString(initialString)
                }
                OutputDataDimensionType.RATE.toString() -> {
                    result = SpannableString(initialString)
                }
                OutputDataDimensionType.ERROR_TYPE.toString() -> {
                    result = SpannableString(initialString)
                }
                else -> {
                    initialString = ""
                    result = SpannableString(initialString)
                }
            }
        }
        return result
    }
}