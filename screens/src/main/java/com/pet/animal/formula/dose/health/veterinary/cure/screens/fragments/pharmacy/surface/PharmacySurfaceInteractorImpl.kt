package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface

import android.widget.Toast
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.Interactor
import com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.CalcInteractorImpl
import com.pet.animal.formula.dose.health.veterinary.cure.fakerepo.FakeRepositoryImpl
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.resources.ResourcesProviderImpl
import com.pet.animal.formula.dose.health.veterinary.cure.utils.settings.SettingsImpl
import org.koin.java.KoinJavaComponent

class PharmacySurfaceInteractorImpl: Interactor<AppState> {
    /** Задание переменных */ //region
    private val resourcesProviderImpl: ResourcesProviderImpl = KoinJavaComponent.getKoin().get()
    // SettingsImpl
    private val settings: SettingsImpl = KoinJavaComponent.getKoin().get()
    // Фейковый (временный) репозиторий
    private val fakeRepositoryImpl: FakeRepositoryImpl = KoinJavaComponent.getKoin().get()
    // Интерактор калькулятора
    private val calcInteractorImpl: CalcInteractorImpl = CalcInteractorImpl()
    //endregion

    // Получение данных полей окна, если они были сохранены ранее
    override suspend fun getData(): AppState {
        return AppState.Success(settings.getPharmacySurfaceScreenData())
    }

    /** Методы для сохранения данных с полей и списков */ //region
    override suspend fun saveData(screenType: ScreenType,
                                  listsAddFirstSecond: List<Int>,
                                  values: List<Double>,
                                  dimensions: List<Int>) {
        // Проверка на заполненность всех числовыз полей
        var isExistZeroData: Boolean = false
        values.forEach {
            if (it == 0.0)  {
                isExistZeroData = true
            }
        }
        if (!isExistZeroData) loadAndSaveFormula(screenType, listsAddFirstSecond, values)
        // Сохранение значений всех списков и числовых полей
        settings.setScreenData(screenType, listsAddFirstSecond, values, dimensions)
    }
    private suspend fun loadAndSaveFormula(screenType: ScreenType, listsAddFirstSecond: List<Int>,
                                           values: List<Double>) {
        Toast.makeText(resourcesProviderImpl.context,
            "Размер: ${listsAddFirstSecond.count()}; Первый член: ${listsAddFirstSecond[0]}", Toast.LENGTH_SHORT).show()
        settings.setFormula(fakeRepositoryImpl.getFormula(screenType, listsAddFirstSecond))
        Toast.makeText(resourcesProviderImpl.context,
            "Сама формула: ${settings.getFormula().getTypedFormulas()[0].name}; \nколичество элементов в формуле: ${settings.getFormula().getTypedFormulas()[0].elements.count()}", Toast.LENGTH_SHORT).show()
        settings.getFormula().getTypedFormulas().forEach { typedFormula ->
            calcInteractorImpl.clearCalc()
            typedFormula.elements.forEach { element ->
                if (element.positionValueOnWindow <= 0) {
                    calcInteractorImpl.setCommand(element.numberCommand)
                } else {
                    // Важно вычесть единицу из позиции element.positionValueOnWindow,
                    // так как нужно перейти от порядкового номера формы с числом
                    // к индексу элемента в списке
                    calcInteractorImpl.setCommand(values[element.positionValueOnWindow - 1].toString())
                }
            }
            Toast.makeText(resourcesProviderImpl.context, "Результат вычислений по формуле: ${calcInteractorImpl.getCommandResultValue()}", Toast.LENGTH_SHORT).show()
        }
    }
    //endregion
}