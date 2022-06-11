package com.pet.animal.formula.dose.health.veterinary.cure.core.base

interface InteractorCalculatorKeyboard<T> {
    suspend fun getData(): T
    suspend fun saveData()
}