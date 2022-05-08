package com.pet.animal.formula.dose.health.veterinary.cure.core.base

interface Interactor<T> {
    suspend fun getData(): T
}