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
            CalculatorKeyboardData(true, newText,
                false,"", CalcConstants.ERRORS.NO)))
    }
    override fun setOutputResultText(newText: String) {
        _mutableLiveData.postValue(AppStateCalcKeyboard.Success(
            CalculatorKeyboardData(false, "",
                true, newText, CalcConstants.ERRORS.NO)))
    }
    override fun setErrorText(error: CalcConstants.ERRORS) {
        _mutableLiveData.postValue(AppStateCalcKeyboard.Success(
            CalculatorKeyboardData(false, "",
                false, "", error)))
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
        return interactor.addNumeral(newNumeral)
    }
    override fun calculate() {
        interactor.calculate()
    }
    override fun setCurZapitay() {
        interactor.setCurZapitay()
    }
    override fun clearAll() {
        interactor.clearAll()
    }
    override fun clearOne() {
        interactor.clearOne()
    }
    override fun clearTwo() {
        interactor.clearTwo()
    }
    override fun setNewAction(action: CalcConstants.ACTIONS) {
        interactor.setNewAction(action)
    }
    override fun changeSign() {
        interactor.changeSign()
    }
    override fun setNewFunction(typeFuncInBracket: CalcConstants.FUNCTIONS): String {
        return interactor.setNewFunction(typeFuncInBracket)
    }
    override fun getPressedZapitay(): Boolean {
        return interactor.getPressedZapitay()
    }
    override fun createOutput(): String {
        return interactor.createOutput()
    }
    override fun getFinalResult(): String {
        return interactor.getFinalResult()
    }
    override fun setEqual() {
        interactor.setEqual()
    }
    override fun getError() {
        interactor.getError()
    }
    override fun setBracketOpen() {
        interactor.setBracketOpen()
    }
    override fun setBracketClose() {
        interactor.setBracketClose()
    }
    override fun getInit() {
        interactor.getInit()
    }
    //endregion

    // Получение LiveData
    fun subscribe(): LiveData<AppStateCalcKeyboard> {
        return liveDataForViewToObserve
    }
}