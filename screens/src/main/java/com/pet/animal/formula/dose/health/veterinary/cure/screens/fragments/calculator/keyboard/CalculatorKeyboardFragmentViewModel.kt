package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.calculator.keyboard

import androidx.lifecycle.LiveData
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseViewModelCalcKeyboard
import com.pet.animal.formula.dose.health.veterinary.cure.model.calculator.CalcConstants
import com.pet.animal.formula.dose.health.veterinary.cure.model.calculator.CalcKeyboardContract
import com.pet.animal.formula.dose.health.veterinary.cure.model.calculator.CalculatorKeyboardData
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppStateCalcKeyboard

class CalculatorKeyboardFragmentViewModel:
    BaseViewModelCalcKeyboard<AppStateCalcKeyboard>(), CalcKeyboardContract {
    /** Задание переменных */ //region
    // LiveData
    private val liveDataForViewToObserve: LiveData<AppStateCalcKeyboard> = _mutableLiveData
    // Интерактор
    private val interactor: CalculatorKeyboardFragmentInteractorImpl =
        CalculatorKeyboardFragmentInteractorImpl(this)
    //endregion

    //region Базовые методы вьюмодели
    override fun setInputedHistoryText(newText: String) {
        _mutableLiveData.postValue(AppStateCalcKeyboard.Success(
            CalculatorKeyboardData(newText, "", CalcConstants.ERRORS.NO)))
    }
    override fun setOutputResultText(newText: String) {
        _mutableLiveData.postValue(AppStateCalcKeyboard.Success(
            CalculatorKeyboardData("", newText, CalcConstants.ERRORS.NO)))
    }
    override fun setErrorText(error: CalcConstants.ERRORS) {
        _mutableLiveData.postValue(AppStateCalcKeyboard.Success(
            CalculatorKeyboardData("", "", error)))
    }
    override fun handleError(error: Throwable) {
//        TODO("Not yet implemented")
    }
    //endregion

    //region Методы калькулятора
    override fun setMaxNumberSymbolsInOutputTextField(maxNumberSymbolsInOutputTextField: Int) {
        interactor.setMaxNumberSymbolsInOutputTextField(maxNumberSymbolsInOutputTextField)
    }
    override fun addNumeral(newNumeral: Int): Double {
        TODO("Not yet implemented")
    }
    override fun calculate() {
        TODO("Not yet implemented")
    }
    override fun setCurZapitay() {
        TODO("Not yet implemented")
    }
    override fun clearAll() {
        TODO("Not yet implemented")
    }
    override fun clearOne() {
        TODO("Not yet implemented")
    }
    override fun clearTwo() {
        TODO("Not yet implemented")
    }
    override fun setNewAction(action: CalcConstants.ACTIONS) {
        TODO("Not yet implemented")
    }
    override fun changeSign() {
        TODO("Not yet implemented")
    }
    override fun setNewFunction(typeFuncInBracket: CalcConstants.FUNCTIONS): String {
        TODO("Not yet implemented")
    }
    override fun getPressedZapitay(): Boolean {
        TODO("Not yet implemented")
    }
    override fun createOutput(): String {
        TODO("Not yet implemented")
    }
    override fun getFinalResult(): String {
        TODO("Not yet implemented")
    }
    override fun setEqual() {
        TODO("Not yet implemented")
    }
    override fun getError() {
        TODO("Not yet implemented")
    }
    override fun setBracketOpen() {
        TODO("Not yet implemented")
    }
    override fun setBracketClose() {
        TODO("Not yet implemented")
    }
    override fun getInit() {
        TODO("Not yet implemented")
    }
    //endregion

    // Получение LiveData
    fun subscribe(): LiveData<AppStateCalcKeyboard> {
        return liveDataForViewToObserve
    }
}