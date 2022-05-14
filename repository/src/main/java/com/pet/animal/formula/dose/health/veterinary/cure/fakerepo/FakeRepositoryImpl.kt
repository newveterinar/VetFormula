package com.pet.animal.formula.dose.health.veterinary.cure.fakerepo

import com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command
import com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.index
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
                        Element(Command.ZERO.index(),0),
                        Element(Command.ZPT_ON_OFF.index(),0),
                        Element(Command.ONE.index(),0),
                        Element(Command.ZERO.index(),0),
                        Element(Command.ONE.index(),0),
                        Element(Command.ZPT_ON_OFF.index(),0),
                        Element(Command.MULTIPLY.index(),0),
                        Element(Command.NO_COMMAND.index(),1),
                        Element(Command.STEPEN.index(),0),
                        Element(Command.BRACKET_OPEN.index(),0),
                        Element(Command.TWO.index(),0),
                        Element(Command.DIVIDE.index(),0),
                        Element(Command.THREE.index(),0),
                        Element(Command.EQUAL.index(),0)
                    )
                ))
            askedTypedName.contains(PHARMACY_SURFACE_CAT_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(TypedFormula(
                    PHARMACY_SURFACE_CAT_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(Command.ZERO.index(),0),
                        Element(Command.ZPT_ON_OFF.index(),0),
                        Element(Command.ONE.index(),0),
                        Element(Command.ZPT_ON_OFF.index(),0),
                        Element(Command.MULTIPLY.index(),0),
                        Element(Command.NO_COMMAND.index(),1),
                        Element(Command.STEPEN.index(),0),
                        Element(Command.BRACKET_OPEN.index(),0),
                        Element(Command.TWO.index(),0),
                        Element(Command.DIVIDE.index(),0),
                        Element(Command.THREE.index(),0),
                        Element(Command.EQUAL.index(),0)
                    )
                ))
            askedTypedName.contains(PHARMACY_SURFACE_RABBIT_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(TypedFormula(
                    PHARMACY_SURFACE_RABBIT_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(Command.ZERO.index(),0),
                        Element(Command.ZPT_ON_OFF.index(),0),
                        Element(Command.ZERO.index(),0),
                        Element(Command.NINE.index(),0),
                        Element(Command.SEVEN.index(),0),
                        Element(Command.FIVE.index(),0),
                        Element(Command.ZPT_ON_OFF.index(),0),
                        Element(Command.MULTIPLY.index(),0),
                        Element(Command.NO_COMMAND.index(),1),
                        Element(Command.STEPEN.index(),0),
                        Element(Command.BRACKET_OPEN.index(),0),
                        Element(Command.TWO.index(),0),
                        Element(Command.DIVIDE.index(),0),
                        Element(Command.THREE.index(),0),
                        Element(Command.EQUAL.index(),0)
                    )
                ))
            askedTypedName.contains(PHARMACY_SURFACE_POLECAT_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(TypedFormula(
                    PHARMACY_SURFACE_POLECAT_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(Command.ZERO.index(),0),
                        Element(Command.ZPT_ON_OFF.index(),0),
                        Element(Command.ZERO.index(),0),
                        Element(Command.NINE.index(),0),
                        Element(Command.SIX.index(),0),
                        Element(Command.NINE.index(),0),
                        Element(Command.ZPT_ON_OFF.index(),0),
                        Element(Command.MULTIPLY.index(),0),
                        Element(Command.NO_COMMAND.index(),1),
                        Element(Command.STEPEN.index(),0),
                        Element(Command.BRACKET_OPEN.index(),0),
                        Element(Command.TWO.index(),0),
                        Element(Command.DIVIDE.index(),0),
                        Element(Command.THREE.index(),0),
                        Element(Command.EQUAL.index(),0)
                    )
                ))
            askedTypedName.contains(PHARMACY_SURFACE_GUINEAPIG_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(TypedFormula(
                    PHARMACY_SURFACE_GUINEAPIG_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(Command.ZERO.index(),0),
                        Element(Command.ZPT_ON_OFF.index(),0),
                        Element(Command.ZERO.index(),0),
                        Element(Command.NINE.index(),0),
                        Element(Command.ZPT_ON_OFF.index(),0),
                        Element(Command.MULTIPLY.index(),0),
                        Element(Command.NO_COMMAND.index(),1),
                        Element(Command.STEPEN.index(),0),
                        Element(Command.BRACKET_OPEN.index(),0),
                        Element(Command.TWO.index(),0),
                        Element(Command.DIVIDE.index(),0),
                        Element(Command.THREE.index(),0),
                        Element(Command.EQUAL.index(),0)
                    )
                ))
            askedTypedName.contains(PHARMACY_SURFACE_HAMSTER_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(TypedFormula(
                    PHARMACY_SURFACE_HAMSTER_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(Command.ZERO.index(),0),
                        Element(Command.ZPT_ON_OFF.index(),0),
                        Element(Command.ONE.index(),0),
                        Element(Command.ONE.index(),0),
                        Element(Command.NINE.index(),0),
                        Element(Command.ZPT_ON_OFF.index(),0),
                        Element(Command.MULTIPLY.index(),0),
                        Element(Command.NO_COMMAND.index(),1),
                        Element(Command.STEPEN.index(),0),
                        Element(Command.BRACKET_OPEN.index(),0),
                        Element(Command.TWO.index(),0),
                        Element(Command.DIVIDE.index(),0),
                        Element(Command.THREE.index(),0),
                        Element(Command.EQUAL.index(),0)
                    )
                ))
            askedTypedName.contains(PHARMACY_SURFACE_HORSEEXCEPTLOMUSTIN_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(TypedFormula(
                    PHARMACY_SURFACE_HORSEEXCEPTLOMUSTIN_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(Command.ZERO.index(),0),
                        Element(Command.ZPT_ON_OFF.index(),0),
                        Element(Command.ONE.index(),0),
                        Element(Command.ZERO.index(),0),
                        Element(Command.FIVE.index(),0),
                        Element(Command.ZPT_ON_OFF.index(),0),
                        Element(Command.MULTIPLY.index(),0),
                        Element(Command.NO_COMMAND.index(),1),
                        Element(Command.STEPEN.index(),0),
                        Element(Command.BRACKET_OPEN.index(),0),
                        Element(Command.TWO.index(),0),
                        Element(Command.DIVIDE.index(),0),
                        Element(Command.THREE.index(),0),
                        Element(Command.EQUAL.index(),0)
                    )
                ))
            askedTypedName.contains(PHARMACY_SURFACE_HORSEONLYLOMUSTIN_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(TypedFormula(
                    PHARMACY_SURFACE_HORSEONLYLOMUSTIN_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(Command.ZERO.index(),0),
                        Element(Command.ZPT_ON_OFF.index(),0),
                        Element(Command.ZERO.index(),0),
                        Element(Command.EIGHT.index(),0),
                        Element(Command.FIVE.index(),0),
                        Element(Command.ZPT_ON_OFF.index(),0),
                        Element(Command.MULTIPLY.index(),0),
                        Element(Command.NO_COMMAND.index(),1),
                        Element(Command.STEPEN.index(),0),
                        Element(Command.BRACKET_OPEN.index(),0),
                        Element(Command.TWO.index(),0),
                        Element(Command.DIVIDE.index(),0),
                        Element(Command.THREE.index(),0),
                        Element(Command.EQUAL.index(),0)
                    )
                ))
            askedTypedName.contains(PHARMACY_SURFACE_RAT_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(TypedFormula(
                    PHARMACY_SURFACE_RAT_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(Command.ZERO.index(),0),
                        Element(Command.ZPT_ON_OFF.index(),0),
                        Element(Command.ZERO.index(),0),
                        Element(Command.NINE.index(),0),
                        Element(Command.ONE.index(),0),
                        Element(Command.ZPT_ON_OFF.index(),0),
                        Element(Command.MULTIPLY.index(),0),
                        Element(Command.NO_COMMAND.index(),1),
                        Element(Command.STEPEN.index(),0),
                        Element(Command.BRACKET_OPEN.index(),0),
                        Element(Command.TWO.index(),0),
                        Element(Command.DIVIDE.index(),0),
                        Element(Command.THREE.index(),0),
                        Element(Command.EQUAL.index(),0)
                    )
                ))
            askedTypedName.contains(PHARMACY_SURFACE_MOUSE_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(TypedFormula(
                    PHARMACY_SURFACE_MOUSE_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(Command.ZERO.index(),0),
                        Element(Command.ZPT_ON_OFF.index(),0),
                        Element(Command.ZERO.index(),0),
                        Element(Command.NINE.index(),0),
                        Element(Command.ZPT_ON_OFF.index(),0),
                        Element(Command.MULTIPLY.index(),0),
                        Element(Command.NO_COMMAND.index(),1),
                        Element(Command.STEPEN.index(),0),
                        Element(Command.BRACKET_OPEN.index(),0),
                        Element(Command.TWO.index(),0),
                        Element(Command.DIVIDE.index(),0),
                        Element(Command.THREE.index(),0),
                        Element(Command.EQUAL.index(),0)
                    )
                ))
            //endregion
        }
        return pharmacySurfaceFormula
    }
}