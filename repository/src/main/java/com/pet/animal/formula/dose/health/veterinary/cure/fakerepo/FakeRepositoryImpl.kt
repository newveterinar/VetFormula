package com.pet.animal.formula.dose.health.veterinary.cure.fakerepo

import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Element
import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Formula
import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.TypedFormula
import com.pet.animal.formula.dose.health.veterinary.cure.utils.*
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.convertAddFirstSecondToTypedFormulaName
import com.pet.animal.formula.dose.health.veterinary.cure.utils.resources.ResourcesProviderImpl
import org.koin.java.KoinJavaComponent

class FakeRepositoryImpl: FakeRepository {
    /** Задание переменных */ //region
    private val resourcesProviderImpl: ResourcesProviderImpl = KoinJavaComponent.getKoin().get()
    //endregion

    override suspend fun getFormula(screenType: ScreenType, listsAddFirstSecond: List<Int>): Formula {
        when (screenType) {
            ScreenType.PHARMACY_SURFACE ->
                return pharmacySurfaceFormula(screenType,listsAddFirstSecond)
            else -> return Formula()
        }
    }
    private fun pharmacySurfaceFormula(
        screenType: ScreenType, listsAddFirstSecond: List<Int>): Formula {
        /** Задание переменных */ //region
        // Результирующая формула
        val pharmacySurfaceFormula: Formula = Formula()
        // Типизированное имя запрошенной формулы
        val askedTypedName: String =
            listsAddFirstSecond.convertAddFirstSecondToTypedFormulaName(screenType)
        //endregion

        when {
            //region Формулы для окна PHARMACY_SURFACE
            askedTypedName.contains(PHARMACY_SURFACE_DOG_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(TypedFormula(
                    PHARMACY_SURFACE_DOG_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(10,0),
                        Element(11,0),
                        Element(1,0),
                        Element(10,0),
                        Element(1,0),
                        Element(11,0),
                        Element(15,0),
                        Element(0,1),
                        Element(20,0),
                        Element(16,0),
                        Element(2,0),
                        Element(14,0),
                        Element(3,0),
                        Element(22,0)
                    )
                ))
            askedTypedName.contains(PHARMACY_SURFACE_CAT_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(TypedFormula(
                    PHARMACY_SURFACE_CAT_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(10,0),
                        Element(11,0),
                        Element(1,0),
                        Element(11,0),
                        Element(15,0),
                        Element(0,1),
                        Element(20,0),
                        Element(16,0),
                        Element(2,0),
                        Element(14,0),
                        Element(3,0),
                        Element(22,0)
                    )
                ))
            askedTypedName.contains(PHARMACY_SURFACE_RABBIT_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(TypedFormula(
                    PHARMACY_SURFACE_RABBIT_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(10,0),
                        Element(11,0),
                        Element(10,0),
                        Element(9,0),
                        Element(7,0),
                        Element(5,0),
                        Element(11,0),
                        Element(15,0),
                        Element(0,1),
                        Element(20,0),
                        Element(16,0),
                        Element(2,0),
                        Element(14,0),
                        Element(3,0),
                        Element(22,0)
                    )
                ))
            askedTypedName.contains(PHARMACY_SURFACE_POLECAT_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(TypedFormula(
                    PHARMACY_SURFACE_POLECAT_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(10,0),
                        Element(11,0),
                        Element(10,0),
                        Element(9,0),
                        Element(6,0),
                        Element(9,0),
                        Element(11,0),
                        Element(15,0),
                        Element(0,1),
                        Element(20,0),
                        Element(16,0),
                        Element(2,0),
                        Element(14,0),
                        Element(3,0),
                        Element(22,0)
                    )
                ))
            askedTypedName.contains(PHARMACY_SURFACE_GUINEAPIG_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(TypedFormula(
                    PHARMACY_SURFACE_GUINEAPIG_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(10,0),
                        Element(11,0),
                        Element(10,0),
                        Element(9,0),
                        Element(11,0),
                        Element(15,0),
                        Element(0,1),
                        Element(20,0),
                        Element(16,0),
                        Element(2,0),
                        Element(14,0),
                        Element(3,0),
                        Element(22,0)
                    )
                ))
            askedTypedName.contains(PHARMACY_SURFACE_HAMSTER_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(TypedFormula(
                    PHARMACY_SURFACE_HAMSTER_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(10,0),
                        Element(11,0),
                        Element(1,0),
                        Element(1,0),
                        Element(9,0),
                        Element(11,0),
                        Element(15,0),
                        Element(0,1),
                        Element(20,0),
                        Element(16,0),
                        Element(2,0),
                        Element(14,0),
                        Element(3,0),
                        Element(22,0)
                    )
                ))
            askedTypedName.contains(PHARMACY_SURFACE_HORSEEXCEPTLOMUSTIN_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(TypedFormula(
                    PHARMACY_SURFACE_HORSEEXCEPTLOMUSTIN_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(10,0),
                        Element(11,0),
                        Element(1,0),
                        Element(10,0),
                        Element(5,0),
                        Element(11,0),
                        Element(15,0),
                        Element(0,1),
                        Element(20,0),
                        Element(16,0),
                        Element(2,0),
                        Element(14,0),
                        Element(3,0),
                        Element(22,0)
                    )
                ))
            askedTypedName.contains(PHARMACY_SURFACE_HORSEONLYLOMUSTIN_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(TypedFormula(
                    PHARMACY_SURFACE_HORSEONLYLOMUSTIN_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(10,0),
                        Element(11,0),
                        Element(10,0),
                        Element(8,0),
                        Element(5,0),
                        Element(11,0),
                        Element(15,0),
                        Element(0,1),
                        Element(20,0),
                        Element(16,0),
                        Element(2,0),
                        Element(14,0),
                        Element(3,0),
                        Element(22,0)
                    )
                ))
            askedTypedName.contains(PHARMACY_SURFACE_RAT_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(TypedFormula(
                    PHARMACY_SURFACE_RAT_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(10,0),
                        Element(11,0),
                        Element(10,0),
                        Element(9,0),
                        Element(1,0),
                        Element(11,0),
                        Element(15,0),
                        Element(0,1),
                        Element(20,0),
                        Element(16,0),
                        Element(2,0),
                        Element(14,0),
                        Element(3,0),
                        Element(22,0)
                    )
                ))
            askedTypedName.contains(PHARMACY_SURFACE_MOUSE_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(TypedFormula(
                    PHARMACY_SURFACE_MOUSE_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(10,0),
                        Element(11,0),
                        Element(10,0),
                        Element(9,0),
                        Element(11,0),
                        Element(15,0),
                        Element(0,1),
                        Element(20,0),
                        Element(16,0),
                        Element(2,0),
                        Element(14,0),
                        Element(3,0),
                        Element(22,0)
                    )
                ))
            //endregion
        }
        return pharmacySurfaceFormula
    }
}