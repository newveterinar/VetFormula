package com.pet.animal.formula.dose.health.veterinary.cure.core.base

import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType

interface Interactor<T> {
    suspend fun getData(): T
    suspend fun saveData(screenType: ScreenType,
                         listsAddFirstSecond: List<Int>,
                         stringValues: List<String>,
                         values: List<Double>,
                         dimensions: List<Int>,
                         isGoToResultScreen: Boolean)
}