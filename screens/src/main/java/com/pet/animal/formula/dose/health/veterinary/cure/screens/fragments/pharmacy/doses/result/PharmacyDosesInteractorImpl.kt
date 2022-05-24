package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.doses.result

import com.pet.animal.formula.dose.health.veterinary.cure.core.base.Interactor
import com.pet.animal.formula.dose.health.veterinary.cure.fakerepo.FakeRepositoryImpl
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.settings.SettingsImpl
import org.koin.java.KoinJavaComponent

class PharmacyDosesInteractorImpl: Interactor<AppState> {
    /** Задание переменных */ //region
    // Фейковый (временный) репозиторий
    private val fakeRepositoryImpl: FakeRepositoryImpl = KoinJavaComponent.getKoin().get()
    // SettingsImpl
    private val settings: SettingsImpl = KoinJavaComponent.getKoin().get()
    //endregion

    // Получение данных полей окна, если они были сохранены ранее
    override suspend fun getData(): AppState {
        return AppState.Success(settings.getInputedScreenData())
    }

    /** Методы для сохранения данных с полей и списков */ //region
    override suspend fun saveData(screenType: ScreenType,
                                  listsAddFirstSecond: List<Int>,
                                  stringValues: List<String>,
                                  values: List<Double>,
                                  dimensions: List<Int>) {
        settings.setScreenData(screenType, listsAddFirstSecond, stringValues, values, dimensions)
    }
    //endregion
}