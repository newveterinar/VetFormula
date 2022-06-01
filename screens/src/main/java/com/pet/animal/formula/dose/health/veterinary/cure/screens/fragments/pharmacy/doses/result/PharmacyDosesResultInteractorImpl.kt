package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.doses.result

import com.pet.animal.formula.dose.health.veterinary.cure.core.base.Interactor
import com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.CalcInteractorImpl
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.ResultValueField
import com.pet.animal.formula.dose.health.veterinary.cure.utils.OutputDataDimensionType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.dimension.outputDataDimensionConverter
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.convertMutableListValueFieldToListIntDimension
import com.pet.animal.formula.dose.health.veterinary.cure.utils.settings.SettingsImpl
import org.koin.java.KoinJavaComponent

class PharmacyDosesResultInteractorImpl(
    private val viewModel: PharmacyDosesResultFragmentViewModel
): Interactor<AppState> {
    /** Задание переменных */ //region
    // SettingsImpl
    private val settings: SettingsImpl = KoinJavaComponent.getKoin().get()
    // Интерактор калькулятора
    private val calcInteractorImpl: CalcInteractorImpl = CalcInteractorImpl()
    //endregion

    // Получение данных полей окна, если они были сохранены ранее
    override suspend fun getData(): AppState {
        return AppState.Success(settings.getInputedScreenData())
    }

    // Проведение вычислений по формуле и передача результата во viewModel
    override suspend fun saveData(
        screenType: ScreenType,
        listsAddFirstSecond: List<Int>,
        stringValues: List<String>,
        values: List<Double>,
        dimensions: List<Int>, // здесь dimensions пуст, нужно брать его из settings
        isGoToResultScreen: Boolean
    ) {
        val resultValueField: MutableList<ResultValueField> = mutableListOf()
        settings.getFormula().getTypedFormulas().forEachIndexed { index, typedFormula ->
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
            // Назначение различным результирующим данным своих конвертирующих коэффициентов
            val outputDataDimensionType: OutputDataDimensionType = when(index) {
                0 -> OutputDataDimensionType.VOLUME
                1 -> OutputDataDimensionType.MASS
                else -> OutputDataDimensionType.ERROR_TYPE
            }
            resultValueField.add(
                ResultValueField(
                    outputDataDimensionConverter(
                        outputDataDimensionType,
                        calcInteractorImpl.getCommandResultValue() ?: 0.0,
                        settings.getInputedScreenData().valueFields.
                            convertMutableListValueFieldToListIntDimension()
                    )
                )
            )
        }
        viewModel.setResultScreenToLiveData(resultValueField)
    }
}