package com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata

import com.pet.animal.formula.dose.health.veterinary.cure.model.calculator.CalculatorKeyboardData

sealed class AppStateCalcKeyboard {
    data class Success(val calculatorKeyboardData: CalculatorKeyboardData): AppStateCalcKeyboard()
    data class Loading(val progress: Int?): AppStateCalcKeyboard()
    data class Error(val error: Throwable): AppStateCalcKeyboard()
}