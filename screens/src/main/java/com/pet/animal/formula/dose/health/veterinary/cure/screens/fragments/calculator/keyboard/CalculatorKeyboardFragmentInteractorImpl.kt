package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.calculator.keyboard

import com.pet.animal.formula.dose.health.veterinary.cure.core.base.InteractorCalculatorKeyboard
import com.pet.animal.formula.dose.health.veterinary.cure.model.calculator.CalculatorKeyboardData
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppStateCalcKeyboard

class CalculatorKeyboardFragmentInteractorImpl (
    private val viewModel: CalculatorKeyboardFragmentViewModel
): InteractorCalculatorKeyboard<AppStateCalcKeyboard> {


    override suspend fun getData(): AppStateCalcKeyboard {
//        TODO("Not yet implemented")
        return AppStateCalcKeyboard.Success(CalculatorKeyboardData())
    }

    override suspend fun saveData() {
//        TODO("Not yet implemented")
    }

}