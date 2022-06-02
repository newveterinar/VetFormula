package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface.result

import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.text.style.SuperscriptSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.ScreenData
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacySurfaceResultBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import com.pet.animal.formula.dose.health.veterinary.cure.utils.SQUARE_TEXT_RELATIVE_SIZE
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.createStringResult
import com.pet.animal.formula.dose.health.veterinary.cure.utils.settings.SettingsImpl
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent


class PharmacySurfaceResultFragment: BaseFragment<FragmentPharmacySurfaceResultBinding>(
    FragmentPharmacySurfaceResultBinding::inflate) {
    /** Задание переменных */ //region
    // Установка типа формулы для текущего окна
    private val screenType: ScreenType = ScreenType.PHARMACY_SURFACE
    // Навигация
    private val navigationButtons = arrayOfNulls<View>(size = 1)
    // Элементы для вывода результирующей информации
    private val resultsValueFields: MutableList<TextView> = mutableListOf()
    // ViewModel
    private lateinit var viewModel: PharmacySurfaceResultFragmentViewModel
    // ShowPharmacySurfaceResultFragmentScope
    private lateinit var showPharmacySurfaceResultFragmentScope: Scope
    // SettingsImpl
    private val settings: SettingsImpl = KoinJavaComponent.getKoin().get()
    // newInstance для данного класса
    companion object {
        fun newInstance(): PharmacySurfaceResultFragment =
            PharmacySurfaceResultFragment()
    }
    //endregion

    /** Работа со Scope */ //region
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Задание Scope для данного фрагмента
        showPharmacySurfaceResultFragmentScope = KoinJavaComponent.getKoin().getOrCreateScope(
            FragmentScope.SHOW_PHARMACY_SURFACE_RESULT_FRAGMENT_SCOPE,
            named(FragmentScope.SHOW_PHARMACY_SURFACE_RESULT_FRAGMENT_SCOPE)
        )
    }
    override fun onDetach() {
        // Удаление скоупа для данного фрагмента
        showPharmacySurfaceResultFragmentScope.close()
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
        resultsValueFields.add(binding.pharmacyResultText)
    }

    // Инициализация ViewModel
    fun initViewModel() {
        val _viewModel: PharmacySurfaceResultFragmentViewModel by
        showPharmacySurfaceResultFragmentScope.inject()
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
                        resultsValueFields.forEachIndexed { index, resultValueTextView ->
                            resultValueTextView.createStringResult(it.resultValueField,
                                index, settings.getInputedScreenData().valueFields)
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