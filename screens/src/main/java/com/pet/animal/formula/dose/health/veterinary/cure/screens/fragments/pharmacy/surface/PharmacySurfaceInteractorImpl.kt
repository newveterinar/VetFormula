package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface

import com.pet.animal.formula.dose.health.veterinary.cure.core.base.Interactor
import com.pet.animal.formula.dose.health.veterinary.cure.fakerepo.FakeRepositoryImpl
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.settings.SettingsImpl
import org.koin.java.KoinJavaComponent

class PharmacySurfaceInteractorImpl: Interactor<AppState> {
    /** Задание переменных */ //region
    // SettingsImpl
    private val settings: SettingsImpl = KoinJavaComponent.getKoin().get()
    // Фейковый (временный) репозиторий
    private val fakeRepositoryImpl: FakeRepositoryImpl = KoinJavaComponent.getKoin().get()
    //endregion

    // Получение данных полей окна, если они были сохранены ранее
    override suspend fun getData(): AppState {
        return AppState.Success(settings.getPharmacySurfaceScreenData())
    }

    /** Методы для сохранения данных с полей и списков */ //region
    override suspend fun saveData(screenType: ScreenType,
                                  listsAddFirstSecond: List<Int>,
                                  stringValues: List<String>,
                                  values: List<Double>,
                                  dimensions: List<Int>) {
        // Проверка на заполненность всех числовых полей
        var isExistZeroData: Boolean = false
        values.forEach {
            if (it == 0.0)  {
                isExistZeroData = true
            }
        }
        if (!isExistZeroData) loadAndSaveFormula(screenType, listsAddFirstSecond, values)
        // Сохранение значений всех списков и числовых полей
        settings.setScreenData(screenType, listsAddFirstSecond, stringValues, values, dimensions)
    }
    private suspend fun loadAndSaveFormula(screenType: ScreenType, listsAddFirstSecond: List<Int>,
                                           values: List<Double>) {
        settings.setFormula(fakeRepositoryImpl.getFormula(screenType, listsAddFirstSecond))
    }
    //endregion
}