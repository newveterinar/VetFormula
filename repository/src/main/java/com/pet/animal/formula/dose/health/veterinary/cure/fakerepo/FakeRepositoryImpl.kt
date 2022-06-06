package com.pet.animal.formula.dose.health.veterinary.cure.fakerepo

import com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command
import com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.index
import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Element
import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Formula
import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.TypedFormula
import com.pet.animal.formula.dose.health.veterinary.cure.utils.*
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.convertAddFirstSecondToTypedFormulaName

class FakeRepositoryImpl: FakeRepository {
    override suspend fun getFormula(screenType: ScreenType, listsAddFirstSecond: List<Int>
    ): Formula {
        return when (screenType) {
            ScreenType.PHARMACY_SURFACE ->
                pharmacySurfaceFormula(screenType, listsAddFirstSecond)
            ScreenType.PHARMACY_DOSES ->
                pharmacyDosesFormula(screenType, listsAddFirstSecond)
            ScreenType.PHARMACY_CRI ->
                pharmacyCRIFormula(screenType, listsAddFirstSecond)
            else -> Formula()
        }
    }

    // Задание формулы для окна PHARMACY_CRI
    private fun pharmacyCRIFormula(
        screenType: ScreenType, listsAddFirstSecond: List<Int>): Formula {
        /** Задание переменных */ //region
        // Результирующая формула
        val pharmacyCRIFormula: Formula = Formula()
        // Типизированное имя запрошенной формулы
        val askedTypedName: String =
            listsAddFirstSecond.convertAddFirstSecondToTypedFormulaName(screenType)
        //endregion

        when {
            //region Типизированные формулы для окна PHARMACY_CRI
            askedTypedName.contains(PHARMACY_CRI_NO_GIVING_SET_NAME) -> {
                pharmacyCRIFormula.addTypedFormula(
                    TypedFormula(
                        PHARMACY_CRI_NO_GIVING_SET_NAME,
                        mutableListOf(
                            Element(Command.NO_COMMAND.index(), 2),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 4),
                            Element(Command.DIVIDE.index(), 0),
                            Element(Command.BRACKET_OPEN.index(), 0),
                            // Заглушка для устранения бага работы скобок
                            Element(Command.ONE.index(), 0),
                            // Заглушка для устранения бага работы скобок
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 3),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 5),
                            Element(Command.BRACKET_CLOSE.index(), 0)
                        )
                    )
                )
                pharmacyCRIFormula.addTypedFormula(
                    TypedFormula(
                        PHARMACY_CRI_NO_GIVING_SET_NAME,
                        mutableListOf(
                            Element(Command.NO_COMMAND.index(), 1),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 5)
                        )
                    )
                )
                pharmacyCRIFormula.addTypedFormula(
                    TypedFormula(
                        PHARMACY_CRI_NO_GIVING_SET_NAME,
                        mutableListOf(
                            Element(Command.NO_COMMAND.index(), 2)
                        )
                    )
                )
                pharmacyCRIFormula.addTypedFormula(
                    TypedFormula(
                        PHARMACY_CRI_NO_GIVING_SET_NAME,
                        mutableListOf(
                            Element(Command.SIX.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 4),
                            Element(Command.DIVIDE.index(), 0),
                            Element(Command.BRACKET_OPEN.index(), 0),
                            // Заглушка для устранения бага работы скобок
                            Element(Command.ONE.index(), 0),
                            // Заглушка для устранения бага работы скобок
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 5),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 1),
                            Element(Command.BRACKET_CLOSE.index(), 0)
                        )
                    )
                )
            }
            askedTypedName.contains(PHARMACY_CRI_20_DROPS_PER_ML_NAME) -> {
                pharmacyCRIFormula.addTypedFormula(
                    TypedFormula(
                        PHARMACY_CRI_20_DROPS_PER_ML_NAME,
                        mutableListOf(
                            Element(Command.NO_COMMAND.index(), 2),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 4),
                            Element(Command.DIVIDE.index(), 0),
                            Element(Command.BRACKET_OPEN.index(), 0),
                            // Заглушка для устранения бага работы скобок
                            Element(Command.ONE.index(), 0),
                            // Заглушка для устранения бага работы скобок
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 3),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 5),
                            Element(Command.BRACKET_CLOSE.index(), 0)
                        )
                    )
                )
                pharmacyCRIFormula.addTypedFormula(
                    TypedFormula(
                        PHARMACY_CRI_20_DROPS_PER_ML_NAME,
                        mutableListOf(
                            Element(Command.NO_COMMAND.index(), 1),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 5)
                        )
                    )
                )
                pharmacyCRIFormula.addTypedFormula(
                    TypedFormula(
                        PHARMACY_CRI_20_DROPS_PER_ML_NAME,
                        mutableListOf(
                            Element(Command.NO_COMMAND.index(), 2)
                        )
                    )
                )
                pharmacyCRIFormula.addTypedFormula(
                    TypedFormula(
                        PHARMACY_CRI_20_DROPS_PER_ML_NAME,
                        mutableListOf(
                            Element(Command.SIX.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 4),
                            Element(Command.DIVIDE.index(), 0),
                            Element(Command.BRACKET_OPEN.index(), 0),
                            // Заглушка для устранения бага работы скобок
                            Element(Command.ONE.index(), 0),
                            // Заглушка для устранения бага работы скобок
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 5),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 1),
                            Element(Command.BRACKET_CLOSE.index(), 0)
                        )
                    )
                )
                pharmacyCRIFormula.addTypedFormula(
                    TypedFormula(
                        PHARMACY_CRI_20_DROPS_PER_ML_NAME,
                        mutableListOf(
                            Element(Command.ONE.index(), 0),
//                            Element(Command.ZERO.index(), 0),
//                            Element(Command.ZPT_ON_OFF.index(), 0),
//                            Element(Command.ZERO.index(), 0),
//                            Element(Command.ZERO.index(), 0),
//                            Element(Command.ZERO.index(), 0),
//                            Element(Command.ZERO.index(), 0),
//                            Element(Command.ZERO.index(), 0),
//                            Element(Command.ONE.index(), 0),
//                            Element(Command.ZPT_ON_OFF.index(), 0),
                            Element(Command.DIVIDE.index(), 0),
                            Element(Command.BRACKET_OPEN.index(), 0),
                            // Заглушка для устранения бага работы скобок
                            Element(Command.ONE.index(), 0),
                            // Заглушка для устранения бага работы скобок
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 1),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 5),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.TWO.index(), 0),
                            Element(Command.ZERO.index(), 0),
//                            Element(Command.MULTIPLY.index(), 0),
//                            Element(Command.ZERO.index(), 0),
//                            Element(Command.ZPT_ON_OFF.index(), 0),
//                            Element(Command.ZERO.index(), 0),
//                            Element(Command.ZERO.index(), 0),
//                            Element(Command.ZERO.index(), 0),
//                            Element(Command.ZERO.index(), 0),
//                            Element(Command.ZERO.index(), 0),
//                            Element(Command.ONE.index(), 0),
//                            Element(Command.ZPT_ON_OFF.index(), 0),
                            Element(Command.BRACKET_CLOSE.index(), 0)
                        )
                    )
                )
                pharmacyCRIFormula.addTypedFormula(
                    TypedFormula(
                        PHARMACY_CRI_20_DROPS_PER_ML_NAME,
                        mutableListOf(
                            Element(Command.ONE.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.NO_COMMAND.index(), 1),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 5),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.TWO.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZPT_ON_OFF.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ONE.index(), 0),
                            Element(Command.ZPT_ON_OFF.index(), 0)
                        )
                    )
                )
                pharmacyCRIFormula.addTypedFormula(
                    TypedFormula(
                        PHARMACY_CRI_20_DROPS_PER_ML_NAME,
                        mutableListOf(
                            Element(Command.NO_COMMAND.index(), 1),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 5),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.TWO.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZPT_ON_OFF.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ONE.index(), 0),
                            Element(Command.ZPT_ON_OFF.index(), 0)
                        )
                    )
                )
            }
            askedTypedName.contains(PHARMACY_CRI_60_DROPS_PER_ML_NAME) -> {
                pharmacyCRIFormula.addTypedFormula(
                    TypedFormula(
                        PHARMACY_CRI_60_DROPS_PER_ML_NAME,
                        mutableListOf(
                            Element(Command.NO_COMMAND.index(), 2),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 4),
                            Element(Command.DIVIDE.index(), 0),
                            Element(Command.BRACKET_OPEN.index(), 0),
                            // Заглушка для устранения бага работы скобок
                            Element(Command.ONE.index(), 0),
                            // Заглушка для устранения бага работы скобок
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 3),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 5),
                            Element(Command.BRACKET_CLOSE.index(), 0)
                        )
                    )
                )
                pharmacyCRIFormula.addTypedFormula(
                    TypedFormula(
                        PHARMACY_CRI_60_DROPS_PER_ML_NAME,
                        mutableListOf(
                            Element(Command.NO_COMMAND.index(), 1),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 5)
                        )
                    )
                )
                pharmacyCRIFormula.addTypedFormula(
                    TypedFormula(
                        PHARMACY_CRI_60_DROPS_PER_ML_NAME,
                        mutableListOf(
                            Element(Command.NO_COMMAND.index(), 2)
                        )
                    )
                )
                pharmacyCRIFormula.addTypedFormula(
                    TypedFormula(
                        PHARMACY_CRI_60_DROPS_PER_ML_NAME,
                        mutableListOf(
                            Element(Command.SIX.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 4),
                            Element(Command.DIVIDE.index(), 0),
                            Element(Command.BRACKET_OPEN.index(), 0),
                            // Заглушка для устранения бага работы скобок
                            Element(Command.ONE.index(), 0),
                            // Заглушка для устранения бага работы скобок
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 5),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 1),
                            Element(Command.BRACKET_CLOSE.index(), 0)
                        )
                    )
                )
                pharmacyCRIFormula.addTypedFormula(
                    TypedFormula(
                        PHARMACY_CRI_60_DROPS_PER_ML_NAME,
                        mutableListOf(
                            Element(Command.ONE.index(), 0),
                            Element(Command.DIVIDE.index(), 0),
                            Element(Command.BRACKET_OPEN.index(), 0),
                            // Заглушка для устранения бага работы скобок
                            Element(Command.ONE.index(), 0),
                            // Заглушка для устранения бага работы скобок
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 1),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 5),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.SIX.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZPT_ON_OFF.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ONE.index(), 0),
                            Element(Command.ZPT_ON_OFF.index(), 0),
                            Element(Command.BRACKET_CLOSE.index(), 0)
                        )
                    )
                )
                pharmacyCRIFormula.addTypedFormula(
                    TypedFormula(
                        PHARMACY_CRI_60_DROPS_PER_ML_NAME,
                        mutableListOf(
                            Element(Command.ONE.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.NO_COMMAND.index(), 1),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 5),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.SIX.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZPT_ON_OFF.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ONE.index(), 0),
                            Element(Command.ZPT_ON_OFF.index(), 0)
                        )
                    )
                )
                pharmacyCRIFormula.addTypedFormula(
                    TypedFormula(
                        PHARMACY_CRI_60_DROPS_PER_ML_NAME,
                        mutableListOf(
                            Element(Command.NO_COMMAND.index(), 1),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 5),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.SIX.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZPT_ON_OFF.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ZERO.index(), 0),
                            Element(Command.ONE.index(), 0),
                            Element(Command.ZPT_ON_OFF.index(), 0)
                        )
                    )
                )
            }
        }
        //endregion
        return pharmacyCRIFormula
    }

    // Задание формулы для окна PHARMACY_SURFACE
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
            //region Типизированные формулы для окна PHARMACY_SURFACE
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
                        // Заглушка для устранения бага работы скобок
                        Element(Command.ONE.index(), 0),
                        // Заглушка для устранения бага работы скобок
                        Element(Command.MULTIPLY.index(), 0),
                        Element(Command.TWO.index(),0),
                        Element(Command.DIVIDE.index(),0),
                        Element(Command.THREE.index(),0),
                        Element(Command.BRACKET_CLOSE.index(),0)
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
                        // Заглушка для устранения бага работы скобок
                        Element(Command.ONE.index(), 0),
                        // Заглушка для устранения бага работы скобок
                        Element(Command.MULTIPLY.index(), 0),
                        Element(Command.TWO.index(),0),
                        Element(Command.DIVIDE.index(),0),
                        Element(Command.THREE.index(),0),
                        Element(Command.BRACKET_CLOSE.index(),0)
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
                        // Заглушка для устранения бага работы скобок
                        Element(Command.ONE.index(), 0),
                        // Заглушка для устранения бага работы скобок
                        Element(Command.MULTIPLY.index(), 0),
                        Element(Command.TWO.index(),0),
                        Element(Command.DIVIDE.index(),0),
                        Element(Command.THREE.index(),0),
                        Element(Command.BRACKET_CLOSE.index(),0)
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
                        // Заглушка для устранения бага работы скобок
                        Element(Command.ONE.index(), 0),
                        // Заглушка для устранения бага работы скобок
                        Element(Command.MULTIPLY.index(), 0),
                        Element(Command.TWO.index(),0),
                        Element(Command.DIVIDE.index(),0),
                        Element(Command.THREE.index(),0),
                        Element(Command.BRACKET_CLOSE.index(),0)
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
                        // Заглушка для устранения бага работы скобок
                        Element(Command.ONE.index(), 0),
                        // Заглушка для устранения бага работы скобок
                        Element(Command.MULTIPLY.index(), 0),
                        Element(Command.TWO.index(),0),
                        Element(Command.DIVIDE.index(),0),
                        Element(Command.THREE.index(),0),
                        Element(Command.BRACKET_CLOSE.index(),0)
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
                        // Заглушка для устранения бага работы скобок
                        Element(Command.ONE.index(), 0),
                        // Заглушка для устранения бага работы скобок
                        Element(Command.MULTIPLY.index(), 0),
                        Element(Command.TWO.index(),0),
                        Element(Command.DIVIDE.index(),0),
                        Element(Command.THREE.index(),0),
                        Element(Command.BRACKET_CLOSE.index(),0)
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
                        // Заглушка для устранения бага работы скобок
                        Element(Command.ONE.index(), 0),
                        // Заглушка для устранения бага работы скобок
                        Element(Command.MULTIPLY.index(), 0),
                        Element(Command.TWO.index(),0),
                        Element(Command.DIVIDE.index(),0),
                        Element(Command.THREE.index(),0),
                        Element(Command.BRACKET_CLOSE.index(),0)
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
                        // Заглушка для устранения бага работы скобок
                        Element(Command.ONE.index(), 0),
                        // Заглушка для устранения бага работы скобок
                        Element(Command.MULTIPLY.index(), 0),
                        Element(Command.TWO.index(),0),
                        Element(Command.DIVIDE.index(),0),
                        Element(Command.THREE.index(),0),
                        Element(Command.BRACKET_CLOSE.index(),0)
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
                        // Заглушка для устранения бага работы скобок
                        Element(Command.ONE.index(), 0),
                        // Заглушка для устранения бага работы скобок
                        Element(Command.MULTIPLY.index(), 0),
                        Element(Command.TWO.index(),0),
                        Element(Command.DIVIDE.index(),0),
                        Element(Command.THREE.index(),0),
                        Element(Command.BRACKET_CLOSE.index(),0)
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
                        // Заглушка для устранения бага работы скобок
                        Element(Command.ONE.index(), 0),
                        // Заглушка для устранения бага работы скобок
                        Element(Command.MULTIPLY.index(), 0),
                        Element(Command.TWO.index(),0),
                        Element(Command.DIVIDE.index(),0),
                        Element(Command.THREE.index(),0),
                        Element(Command.BRACKET_CLOSE.index(),0)
                    )
                ))
            //endregion
        }
        return pharmacySurfaceFormula
    }

    // Задание формулы для окна PHARMACY_DOSES
    private fun pharmacyDosesFormula(
        screenType: ScreenType, listsAddFirstSecond: List<Int>): Formula {
        /** Задание переменных */ //region
        // Результирующая формула
        val pharmacyDosesFormula: Formula = Formula()
        // Типизированное имя запрошенной формулы
        val askedTypedName: String =
            listsAddFirstSecond.convertAddFirstSecondToTypedFormulaName(screenType)
        //endregion

        when {
            //region Типизированные формулы для окна PHARMACY_DOSES
            askedTypedName.contains(PHARMACY_DOSES_NAME) -> {
                pharmacyDosesFormula.addTypedFormula(
                    TypedFormula(
                        PHARMACY_DOSES_NAME,
                        mutableListOf(
                            Element(Command.NO_COMMAND.index(), 1),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 2),
                            Element(Command.DIVIDE.index(), 0),
                            Element(Command.NO_COMMAND.index(), 3)
                        )
                    )
                )
                pharmacyDosesFormula.addTypedFormula(
                    TypedFormula(
                        PHARMACY_DOSES_NAME,
                        mutableListOf(
                            Element(Command.NO_COMMAND.index(), 1),
                            Element(Command.MULTIPLY.index(), 0),
                            Element(Command.NO_COMMAND.index(), 2)
                        )
                    )
                )
            }
        }
        //endregion
        return pharmacyDosesFormula
    }
}