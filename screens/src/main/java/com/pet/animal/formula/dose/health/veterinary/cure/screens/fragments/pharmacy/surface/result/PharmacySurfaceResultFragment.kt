package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface.result

import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.text.style.SuperscriptSpan
import android.view.View
import android.widget.Toast
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.ScreenData
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacySurfaceResultBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import com.pet.animal.formula.dose.health.veterinary.cure.utils.SQUARE_TEXT_RELATIVE_SIZE
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
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
    // ViewModel
    private lateinit var viewModel: PharmacySurfaceResultFragmentViewModel
    // ShowPharmacySurfaceResultFragmentScope
    private lateinit var showPharmacySurfaceResultFragmentScope: Scope
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
                        binding.pharmacyResultText.text = createStringResult(it, 0)
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

    // Подготовка строк с результатами
    private fun createStringResult(screenData: ScreenData, indexData: Int): SpannableString {
        val initialString: String = "${screenData.resultValueField[indexData].value} " +
                requireActivity().resources.getString(R.string.output_data_dimension_square_length)
        val result = SpannableString(initialString)
        // Изменение формата размерности текста
        if (result.isNotEmpty()) {
            //region Установка последнего символа в верхний регистр
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
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            //endregion
        }
        return result
    }
}