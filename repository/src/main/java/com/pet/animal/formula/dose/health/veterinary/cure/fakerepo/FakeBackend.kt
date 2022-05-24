package com.pet.animal.formula.dose.health.veterinary.cure.fakerepo

import com.pet.animal.formula.dose.health.veterinary.cure.unientity.*

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
                    UniFormulaEntity(1, 1, "Surface area"),
                    UniFormulaEntity(2, 1, "Pharmacy Doses")
                )
            }
            2 -> {
                listOf(
                    UniFormulaEntity(3, 2, "Basic")
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
}