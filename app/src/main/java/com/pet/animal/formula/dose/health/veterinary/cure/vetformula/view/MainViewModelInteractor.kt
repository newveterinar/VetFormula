package com.pet.animal.formula.dose.health.veterinary.cure.vetformula.view

import com.pet.animal.formula.dose.health.veterinary.cure.fakerepo.FakeRepositoryImpl
import com.pet.animal.formula.dose.health.veterinary.cure.repo.Repository
import com.pet.animal.formula.dose.health.veterinary.cure.utils.*
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
        // Принудительная очистка базы данных перед добавлением новых формул
//        repeat(500) {
//            repositoryImpl.deleteFormulaByID(it.toLong())
//        }

        // Проверка отсутствия в базе данных типизированной формулы для окна PHARMACY_SURFACE
        if (repositoryImpl.getFormula(ScreenType.PHARMACY_SURFACE,
                listOf(PHARMACY_SURFACE_DOG_INDEX, 0)).getTypedFormulas().size == 0) {
            // Сохранение формул для окна PHARMACY_SURFACE
            repeat(resourcesProviderImpl.getStringArray(R.array.
                addFirstSecond_animal_type_list).size) {
                repositoryImpl.insertFormula(
                    fakeRepositoryImpl.getFormula(ScreenType.PHARMACY_SURFACE, listOf(it, 0)),
                    ScreenType.PHARMACY_SURFACE.ordinal,
                    PHARMACY_SURFACE_FORMULA_ELEMENT_COUNT,
                    it,
                    0
                )
            }
            // Сохранение формул для окна PHARMACY_DOSES
            repeat(PHARMACY_DOSES_ADDFIRST_FORMULA_NUMBER) {
                repositoryImpl.insertFormula(
                    fakeRepositoryImpl.getFormula(ScreenType.PHARMACY_DOSES, listOf(it, 0)),
                    ScreenType.PHARMACY_DOSES.ordinal,
                    PHARMACY_DOSES_FORMULA_ELEMENT_COUNT,
                    it,
                    0
                )
            }
            // Сохранение формул для окна PHARMACY_CRI
            repeat(PHARMACY_CRI_ADDFIRST_FORMULA_NUMBER) {
                repositoryImpl.insertFormula(
                    fakeRepositoryImpl.getFormula(ScreenType.PHARMACY_CRI, listOf(it, 0)),
                    ScreenType.PHARMACY_CRI.ordinal,
                    PHARMACY_CRI_FORMULA_ELEMENT_COUNT,
                    it,
                    0
                )
            }
            // Сохранение формул для окна GASES_INHALATION_ANESTHESIA
            repeat(GASES_INHALATION_ANESTHESIA_ADDFIRST_FORMULA_NUMBER) {
                repositoryImpl.insertFormula(
                    fakeRepositoryImpl.getFormula(
                        ScreenType.GASES_INHALATION_ANESTHESIA, listOf(it, 0)),
                    ScreenType.GASES_INHALATION_ANESTHESIA.ordinal,
                    GASES_INHALATION_ANESTHESIA_ELEMENT_COUNT,
                    it,
                    0
                )
            }
        }
    }
}