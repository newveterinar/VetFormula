package com.pet.animal.formula.dose.health.veterinary.cure.vetformula.view

import com.pet.animal.formula.dose.health.veterinary.cure.fakerepo.FakeRepositoryImpl
import com.pet.animal.formula.dose.health.veterinary.cure.repo.Repository
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.resources.ResourcesProviderImpl
import org.koin.java.KoinJavaComponent

class MainViewModelInteractor {
    /** Задание переменных */ //region
    // Фейковый (временный) репозиторий
    private val fakeRepositoryImpl: FakeRepositoryImpl = KoinJavaComponent.getKoin().get()
    // Репозиторий с базой данной
    private val repositoryImpl: Repository = KoinJavaComponent.getKoin().get()
    // Получение доступа к ресурсам
    private val resourcesProviderImpl: ResourcesProviderImpl = KoinJavaComponent.getKoin().get()
    //endregion

    // Запись в базу данных формул при первом запуске приложения
    suspend fun writeDataToBDAtFirstRun() {
        // Временная очистка базы данных (потом удалить!)
//        repeat(300) {
//            repositoryImpl.deleteFormulaByID(it.toLong())
//        }

        // Проверка отсутствия в базе данных типизированной формулы для окна PHARMACY_SURFACE
        if (repositoryImpl.getFormula(ScreenType.PHARMACY_SURFACE, listOf(0, 0))
                .getTypedFormulas().size == 0) {
            // Сохранение формул для окна PHARMACY_SURFACE
            repeat(resourcesProviderImpl.getStringArray(
                com.pet.animal.formula.dose.health.veterinary.cure.screens.R.array.
                addFirstSecond_animal_type_list).size) {
                repositoryImpl.insertFormula(
                    fakeRepositoryImpl.getFormula(ScreenType.PHARMACY_SURFACE, listOf(it, 0)),
                    ScreenType.PHARMACY_SURFACE.ordinal,
                    1,
                    it,
                    0
                )
                // Сохранение формул для окна PHARMACY_SURFACE
                repositoryImpl.insertFormula(
                    fakeRepositoryImpl.getFormula(ScreenType.PHARMACY_DOSES, listOf(0, 0)),
                    ScreenType.PHARMACY_DOSES.ordinal,
                    3,
                    0,
                    0
                )
            }
        }
//        Toast.makeText(resourcesProviderImpl.context,
//            "${repositoryImpl.getFormula(ScreenType.PHARMACY_SURFACE, listOf(0, 0))
//                .getTypedFormulas().size}", Toast.LENGTH_SHORT).show()
//        Toast.makeText(resourcesProviderImpl.context,
//            "${repositoryImpl.getFormula(ScreenType.PHARMACY_DOSES, listOf(0, 0))
//                .getTypedFormulas().size}", Toast.LENGTH_SHORT).show()
    }
}