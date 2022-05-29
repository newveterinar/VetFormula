package com.pet.animal.formula.dose.health.veterinary.cure.fakerepo

import com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.index
import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Element
import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Formula
import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.TypedFormula
import com.pet.animal.formula.dose.health.veterinary.cure.unientity.*
import com.pet.animal.formula.dose.health.veterinary.cure.utils.*
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.convertAddFirstSecondToTypedFormulaName

//TODO только для тестов
object FakeBackend {


    fun getSection(): List<UniSectionEntity> {
        return listOf(
            UniSectionEntity(1, "Pharmacy"),
            UniSectionEntity(2, "Fluids")
        )
    }

    fun getSectionTranslate(sectionId: Int): List<UniTranslateSectionEntity> {
        return listOf(
            UniTranslateSectionEntity(1, 1, "ru", "Препараты")
        )
    }

    fun getFormulasBySection(sectionId: Int): List<UniFormulaEntity> {
        return when (sectionId) {
            1 -> {
                listOf(
                    UniFormulaEntity(1, 1, "Surface area",pharmacySurfaceFormula(ScreenType.PHARMACY_SURFACE,listOf(1,1))),
                    UniFormulaEntity(2, 1, "Pharmacy Doses",Formula())
                )
            }
            2 -> {
                listOf(
                    UniFormulaEntity(3, 2, "Basic",Formula())
                )
            }
            else -> {
                listOf()
            }
        }
    }

    fun getParamsByFormula(formulaId: Int): List<UniParamEntity> {
        when (formulaId) {
            1 -> {
                return listOf(
                    UniParamEntity(
                        1,
                        1,
                        1,
                        "Animal type",
                        ParamType.LIST,
                        DimensionType.NONE,
                        ListType.ANIMAL
                    ),
                    UniParamEntity(
                        2,
                        1,
                        2,
                        "Weigth",
                        ParamType.INT,
                        DimensionType.WEIGHT,
                        ListType.NONE
                    )
                )
            }
            2 -> {
                return listOf(
                    UniParamEntity(
                        3,
                        2,
                        1,
                        "Animal type",
                        ParamType.LIST,
                        DimensionType.NONE,
                        ListType.ANIMAL
                    ),
                    UniParamEntity(
                        4,
                        2,
                        2,
                        "Dose",
                        ParamType.INT,
                        DimensionType.DOSE,
                        ListType.NONE
                    ),
                    UniParamEntity(
                        5,
                        2,
                        3,
                        "Specify concentration",
                        ParamType.INT,
                        DimensionType.CONCENTRATION,
                        ListType.NONE
                    )
                )
            }
            3 -> {
                return listOf()
            }
            else -> {
                return listOf()
            }
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
                pharmacySurfaceFormula.addTypedFormula(
                    TypedFormula(
                    PHARMACY_SURFACE_DOG_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZERO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZPT_ON_OFF.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ONE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZERO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ONE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZPT_ON_OFF.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.MULTIPLY.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.NO_COMMAND.index(),1),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.STEPEN.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.BRACKET_OPEN.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.TWO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.DIVIDE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.THREE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.BRACKET_CLOSE.index(),0),
                    )
                )
                )
            askedTypedName.contains(PHARMACY_SURFACE_CAT_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(
                    TypedFormula(
                    PHARMACY_SURFACE_CAT_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZERO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZPT_ON_OFF.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ONE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZPT_ON_OFF.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.MULTIPLY.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.NO_COMMAND.index(),1),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.STEPEN.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.BRACKET_OPEN.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.TWO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.DIVIDE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.THREE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.BRACKET_CLOSE.index(),0),
                    )
                )
                )
            askedTypedName.contains(PHARMACY_SURFACE_RABBIT_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(
                    TypedFormula(
                    PHARMACY_SURFACE_RABBIT_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZERO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZPT_ON_OFF.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZERO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.NINE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.SEVEN.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.FIVE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZPT_ON_OFF.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.MULTIPLY.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.NO_COMMAND.index(),1),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.STEPEN.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.BRACKET_OPEN.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.TWO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.DIVIDE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.THREE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.BRACKET_CLOSE.index(),0),
                    )
                )
                )
            askedTypedName.contains(PHARMACY_SURFACE_POLECAT_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(
                    TypedFormula(
                    PHARMACY_SURFACE_POLECAT_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZERO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZPT_ON_OFF.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZERO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.NINE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.SIX.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.NINE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZPT_ON_OFF.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.MULTIPLY.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.NO_COMMAND.index(),1),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.STEPEN.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.BRACKET_OPEN.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.TWO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.DIVIDE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.THREE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.BRACKET_CLOSE.index(),0),
                    )
                )
                )
            askedTypedName.contains(PHARMACY_SURFACE_GUINEAPIG_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(
                    TypedFormula(
                    PHARMACY_SURFACE_GUINEAPIG_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZERO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZPT_ON_OFF.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZERO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.NINE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZPT_ON_OFF.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.MULTIPLY.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.NO_COMMAND.index(),1),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.STEPEN.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.BRACKET_OPEN.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.TWO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.DIVIDE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.THREE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.BRACKET_CLOSE.index(),0),
                    )
                )
                )
            askedTypedName.contains(PHARMACY_SURFACE_HAMSTER_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(
                    TypedFormula(
                    PHARMACY_SURFACE_HAMSTER_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZERO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZPT_ON_OFF.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ONE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ONE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.NINE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZPT_ON_OFF.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.MULTIPLY.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.NO_COMMAND.index(),1),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.STEPEN.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.BRACKET_OPEN.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.TWO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.DIVIDE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.THREE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.BRACKET_CLOSE.index(),0),
                    )
                )
                )
            askedTypedName.contains(PHARMACY_SURFACE_HORSEEXCEPTLOMUSTIN_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(
                    TypedFormula(
                    PHARMACY_SURFACE_HORSEEXCEPTLOMUSTIN_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZERO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZPT_ON_OFF.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ONE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZERO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.FIVE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZPT_ON_OFF.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.MULTIPLY.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.NO_COMMAND.index(),1),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.STEPEN.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.BRACKET_OPEN.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.TWO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.DIVIDE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.THREE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.BRACKET_CLOSE.index(),0),
                    )
                )
                )
            askedTypedName.contains(PHARMACY_SURFACE_HORSEONLYLOMUSTIN_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(
                    TypedFormula(
                    PHARMACY_SURFACE_HORSEONLYLOMUSTIN_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZERO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZPT_ON_OFF.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZERO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.EIGHT.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.FIVE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZPT_ON_OFF.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.MULTIPLY.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.NO_COMMAND.index(),1),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.STEPEN.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.BRACKET_OPEN.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.TWO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.DIVIDE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.THREE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.BRACKET_CLOSE.index(),0),
                    )
                )
                )
            askedTypedName.contains(PHARMACY_SURFACE_RAT_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(
                    TypedFormula(
                    PHARMACY_SURFACE_RAT_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZERO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZPT_ON_OFF.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZERO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.NINE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ONE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZPT_ON_OFF.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.MULTIPLY.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.NO_COMMAND.index(),1),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.STEPEN.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.BRACKET_OPEN.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.TWO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.DIVIDE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.THREE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.BRACKET_CLOSE.index(),0),
                    )
                )
                )
            askedTypedName.contains(PHARMACY_SURFACE_MOUSE_BODYSURFACEAREA_NAME) ->
                pharmacySurfaceFormula.addTypedFormula(
                    TypedFormula(
                    PHARMACY_SURFACE_MOUSE_BODYSURFACEAREA_NAME,
                    mutableListOf(
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZERO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZPT_ON_OFF.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZERO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.NINE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.ZPT_ON_OFF.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.MULTIPLY.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.NO_COMMAND.index(),1),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.STEPEN.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.BRACKET_OPEN.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.TWO.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.DIVIDE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.THREE.index(),0),
                        Element(com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.Command.BRACKET_CLOSE.index(),0),
                    )
                )
                )
            //endregion
        }
        return pharmacySurfaceFormula
    }
}