package com.pet.animal.formula.dose.health.veterinary.cure.fakerepo

import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Element
import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Formula
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType

class FakeRepositoryImpl: FakeRepository {
    override suspend fun getAllVetFormulas(formulaType: ScreenType): List<Formula> {
        when (formulaType) {
            ScreenType.PHARMACY_SURFACE -> return pharmacySurfaceFormula()
            else -> return listOf(Formula(mutableListOf()))
        }
    }
    private fun pharmacySurfaceFormula(): List<Formula> {
        var pharmacySurfaceFormula: Formula = Formula(mutableListOf(
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
        ))
        return listOf(pharmacySurfaceFormula)
    }
}