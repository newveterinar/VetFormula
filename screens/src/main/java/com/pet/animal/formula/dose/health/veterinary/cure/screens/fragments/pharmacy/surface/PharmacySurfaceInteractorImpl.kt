package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface

import android.widget.Toast
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.Interactor
import com.pet.animal.formula.dose.health.veterinary.cure.fakerepo.FakeRepositoryImpl
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.repo.Repository
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.resources.ResourcesProviderImpl
import com.pet.animal.formula.dose.health.veterinary.cure.utils.settings.SettingsImpl
import org.koin.java.KoinJavaComponent

class PharmacySurfaceInteractorImpl: Interactor<AppState> {
    /** Задание переменных */ //region
    // SettingsImpl
    private val settings: SettingsImpl = KoinJavaComponent.getKoin().get()
    // Фейковый (временный) репозиторий
    private val fakeRepositoryImpl: FakeRepositoryImpl = KoinJavaComponent.getKoin().get()
    private val repositoryImpl: Repository = KoinJavaComponent.getKoin().get()
    private val resourcesProviderImpl: ResourcesProviderImpl = KoinJavaComponent.getKoin().get()
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
    private suspend fun loadAndSaveFormula(
        screenType: ScreenType,
        listsAddFirstSecond: List<Int>,
        values: List<Double>) {
        //TODO необходимо создать отдельный класс/метод который будет создавать все формулы в базе данных при первом запуске приложения
//        val formulaForDB = fakeRepositoryImpl.getFormula(screenType, listsAddFirstSecond)
//        repositoryImpl.insertFormula(formulaForDB,screenType.ordinal,0,0,0 )
//        val formulaFromDV = repositoryImpl.getFormula(screenType, listsAddFirstSecond)
//        settings.setFormula(formulaFromDV)

        //region Удаление всех формул:
//        Toast.makeText(resourcesProviderImpl.context,
//            "Количество формул в базе данных: ${repositoryImpl.getAllVetFormulas().size}", Toast.LENGTH_SHORT).show()
//        val numberFormulas: Int = repositoryImpl.getAllVetFormulas().size
        repeat(300) {
            repositoryImpl.deleteFormulaByID(it.toLong())
        }
//        Toast.makeText(resourcesProviderImpl.context,
//            "Количество формул в базе данных: ${repositoryImpl.getAllVetFormulas().size}", Toast.LENGTH_SHORT).show()
        //endregion

        val formulaForDB = fakeRepositoryImpl.getFormula(screenType, listsAddFirstSecond)
        if (listsAddFirstSecond.isNotEmpty()) {
            repositoryImpl.insertFormula(
                formulaForDB,
                screenType.ordinal,
                values.count(),
                listsAddFirstSecond[0],
                if (listsAddFirstSecond.size > 1) listsAddFirstSecond[1] else 0
            )
            // Корректно работает. Всё время берёт формулу для собки (с индексом 0)
            val formulaFromDV = repositoryImpl.getFormula(screenType, listsAddFirstSecond)
            settings.setFormula(formulaFromDV)
        }
//          Корректно работает
//            settings.setFormula(fakeRepositoryImpl.getFormula(screenType, listsAddFirstSecond))
    }
    //endregion
}