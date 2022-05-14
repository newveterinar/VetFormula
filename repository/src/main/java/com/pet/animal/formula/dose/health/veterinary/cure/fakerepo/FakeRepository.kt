package com.pet.animal.formula.dose.health.veterinary.cure.fakerepo

import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Formula
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType

interface FakeRepository {
    suspend fun getFormula(screenType: ScreenType, listsAddFirstSecond: List<Int>): Formula
}