package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.doses

import com.pet.animal.formula.dose.health.veterinary.cure.core.base.Interactor
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.repo.Repository
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.settings.SettingsImpl
import org.koin.java.KoinJavaComponent

class PharmacyDosesInteractorImpl(
    private val viewModel: PharmacyDosesFragmentViewModel
): Interactor<AppState> {
    /** Задание переменных */ //region
    // SettingsImpl
    private val settings: SettingsImpl = KoinJavaComponent.getKoin().get()
    // Репозиторий с базой данной
    private val repositoryImpl: Repository = KoinJavaComponent.getKoin().get()
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
                                  dimensions: List<Int>,
                                  isGoToResultScreen: Boolean) {
        // Проверка на заполненность всех числовых полей
        var isExistZeroData: Boolean = false
        values.forEach {
            if (it == 0.0)  {
                isExistZeroData = true
            }
        }
        if (!isExistZeroData) loadAndSaveFormula(screenType, listsAddFirstSecond)
        // Сохранение значений всех списков и числовых полей
        settings.setScreenData(screenType, listsAddFirstSecond, stringValues, values, dimensions)
        // Установка в LiveData вьюмодели признака IsGoToResultScreen
        // для перехода в окно с результатом
        if (isGoToResultScreen) viewModel.setIsGoToResultScreenToLiveData()
    }
    private suspend fun loadAndSaveFormula(
        screenType: ScreenType,
        listsAddFirstSecond: List<Int>) {
        settings.setFormula(repositoryImpl.getFormula(screenType, listsAddFirstSecond))
    }
    //endregion
}