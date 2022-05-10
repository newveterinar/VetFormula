package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface

import android.widget.EditText
import android.widget.Spinner
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.Interactor
import com.pet.animal.formula.dose.health.veterinary.cure.fakerepo.FakeRepositoryImpl
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.resources.ResourcesProviderImpl
import com.pet.animal.formula.dose.health.veterinary.cure.utils.settings.SettingsImpl
import org.koin.java.KoinJavaComponent

class PharmacySurfaceInteractorImpl: Interactor<AppState> {
    /** Задание переменных */ //region
    // Доступ к ресурсам
    private val resourcesProviderImpl: ResourcesProviderImpl = KoinJavaComponent.getKoin().get()
    // Фейковый (временный) репозиторий
    private val fakeRepositoryImpl: FakeRepositoryImpl = KoinJavaComponent.getKoin().get()
    // SettingsImpl
    private val settings: SettingsImpl = KoinJavaComponent.getKoin().get()
    //endregion

    // Получение данных полей окна, если они были сохранены ранее
    override suspend fun getData(): AppState {
        return AppState.SuccessPharmacySurface(settings.getPharmacySurfaceScreenData())
    }

    /** Методы для сохранения данных с полей и списков */ //region
    override suspend fun saveData(formulaType: ScreenType,
                                  listsAddFirstSecond: List<Int>,
                                  values: List<Double>,
                                  dimensions: List<Int>) {
        settings.setScreenData(formulaType, listsAddFirstSecond, values, dimensions)
    }
    //endregion
}