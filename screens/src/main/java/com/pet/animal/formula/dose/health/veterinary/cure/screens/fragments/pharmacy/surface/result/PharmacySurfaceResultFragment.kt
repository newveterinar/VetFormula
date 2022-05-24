package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface.result

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.CalcInteractorImpl
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacySurfaceResultBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import com.pet.animal.formula.dose.health.veterinary.cure.utils.settings.SettingsImpl
import kotlinx.coroutines.delay
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class PharmacySurfaceResultFragment: BaseFragment<FragmentPharmacySurfaceResultBinding>(
    FragmentPharmacySurfaceResultBinding::inflate) {
    /** Задание переменных */ //region
    // Навигация
    private val navigationButtons = arrayOfNulls<View>(size = 2)
    // ViewModel
    private lateinit var viewModel: PharmacySurfaceResultFragmentViewModel
    // ShowPharmacySurfaceFragmentScope
    private lateinit var showPharmacySurfaceResultFragmentScope: Scope

    // SettingsImpl
    private val settings: SettingsImpl = KoinJavaComponent.getKoin().get()
    // Интерактор калькулятора
    private val calcInteractorImpl: CalcInteractorImpl = CalcInteractorImpl()

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

        Toast.makeText(requireContext(),
            "${settings.getInputedScreenData().listsAddFirstSecond.size}\n" +
                "${settings.getInputedScreenData().valueFields.size}", Toast.LENGTH_SHORT).show()

        if ((settings.getInputedScreenData().listsAddFirstSecond.size > 0) &&
            (settings.getInputedScreenData().valueFields.size > 0)) {
            Toast.makeText(requireContext(),
                "${settings.getInputedScreenData().listsAddFirstSecond[0]}\n" +
                        "${settings.getInputedScreenData().valueFields[0].stringValue}\n" +
                        "${settings.getInputedScreenData().valueFields[0].value}\n" +
                        "${settings.getInputedScreenData().valueFields[0].dimension}\n" +
                        "Количество типизированных формул: ${settings.getFormula().getTypedFormulas().size}",
                Toast.LENGTH_SHORT).show()
            tempCalculate()
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
                it[0] = this.pharmacyPreviousButtonContainer
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

    // Инициализация ViewModel
    fun initViewModel() {
        val _viewModel: PharmacySurfaceResultFragmentViewModel by
            showPharmacySurfaceResultFragmentScope.inject()
        viewModel = _viewModel
    }

    companion object {
        fun newInstance(): PharmacySurfaceResultFragment =
            PharmacySurfaceResultFragment()
    }

    // Временная демонстрация расчёта
    fun tempCalculate() {
        settings.getFormula().getTypedFormulas().forEach { typedFormula ->
            calcInteractorImpl.clearCalc()
            typedFormula.elements.forEach { element ->
                if (element.positionValueOnWindow == 0) {
                    calcInteractorImpl.setCommand(element.numberCommand)
                } else {
                    // Важно вычесть единицу из позиции element.positionValueOnWindow,
                    // так как нужно перейти от порядкового номера формы с числом
                    // к индексу элемента в списке
                    calcInteractorImpl.setCommand(
                        settings.getInputedScreenData().
                        valueFields[element.positionValueOnWindow - 1].value)
                }
            }
            Toast.makeText(requireContext(), "Результат вычислений по формуле: ${
                calcInteractorImpl.getCommandResultValue()}", Toast.LENGTH_SHORT).show()
        }
    }
}