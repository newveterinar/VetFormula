package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.calculator.keyboard

import android.widget.Toast
import com.pet.animal.formula.dose.health.veterinary.cure.core.calculator.CalcLogic
import com.pet.animal.formula.dose.health.veterinary.cure.model.calculator.CalcConstants
import com.pet.animal.formula.dose.health.veterinary.cure.model.calculator.CalcKeyboardContract
import com.pet.animal.formula.dose.health.veterinary.cure.utils.resources.ResourcesProviderImpl
import org.koin.java.KoinJavaComponent
import java.util.*

class CalculatorKeyboardFragmentInteractorImpl (
    private val viewModel: CalculatorKeyboardFragmentViewModel
): CalcKeyboardContract {
    /** Задание исходных данных */ //region
    // Сам калькулятор (его логика)
    private val calcLogic = CalcLogic()
    // Получение доступа к ресурсам
    val resourcesProviderImpl: ResourcesProviderImpl = KoinJavaComponent.getKoin().get()
    //endregion

    //region Методы калькулятора
    override fun setMaxNumberSymbolsInOutputTextField(maxNumberSymbolsInOutputTextField: Int) {
        calcLogic.setMaxNumberSymbolsInOutputTextField(maxNumberSymbolsInOutputTextField)
    }
    override fun addNumeral(newNumeral: Int): Double {
        val result = calcLogic.addNumeral(newNumeral)
        viewModel.setInputedHistoryText(
            java.lang.String.format(Locale.getDefault(), "%s", createOutput())
        )
        return result
    }
    override fun calculate() {
        calcLogic.calculate()
    }
    override fun setCurZapitay() {
        calcLogic.setCurZapitay()
        viewModel.setInputedHistoryText(String.format(Locale.getDefault(),
            "%s", createOutput()))
    }
    override fun clearAll() {
        calcLogic.clearAll()
        calculate()
        getError()
        viewModel.setOutputResultText(calcLogic.getFinalResult())
        viewModel.setInputedHistoryText(String.format(Locale.getDefault(),
            "%s", createOutput()))
    }
    override fun clearOne() {
        if (calcLogic.clearOne()) {
            // TODO: Обновление поля с результатом, доделать, если нужно
        }
        viewModel.setInputedHistoryText(String.format(Locale.getDefault(),
            "%s", createOutput()))
    }
    override fun clearTwo() {
        if (calcLogic.clearTwo()) {
            // TODO: Обновление поля с результатом, доделать, если нужно
        }
        viewModel.setInputedHistoryText(String.format(Locale.getDefault(),
            "%s", createOutput()))
    }
    override fun setNewAction(action: CalcConstants.ACTIONS) {
        calcLogic.setNewAction(action)
        viewModel.setInputedHistoryText(String.format(Locale.getDefault(),
            "%s", createOutput()))
    }
    override fun changeSign() {
        calcLogic.changeSign()
        viewModel.setInputedHistoryText(String.format(Locale.getDefault(),
            "%s", createOutput()))
    }
    override fun setNewFunction(typeFuncInBracket: CalcConstants.FUNCTIONS): String {
        val result = calcLogic.setNewFunction(typeFuncInBracket)
        viewModel.setInputedHistoryText(
            java.lang.String.format(Locale.getDefault(),"%s", createOutput())
        )
        return result
    }
    override fun getPressedZapitay(): Boolean {
        return calcLogic.getPressedZapitay()
    }
    override fun createOutput(): String {
        return calcLogic.createOutput()
    }
    override fun getFinalResult(): String {
        return calcLogic.getFinalResult()
    }
    override fun setEqual() {
        calcLogic.calculate()
        getError()
        viewModel.setOutputResultText(calcLogic.getFinalResult())
    }
    override fun getError() {
        viewModel.setErrorText(calcLogic.getErrorCode())
        calcLogic.clearErrorCode()
    }
    override fun setBracketOpen() {
        viewModel.setInputedHistoryText(String.format(Locale.getDefault(),
            "%s", setNewFunction(CalcConstants.FUNCTIONS.FUNC_NO)))
    }
    override fun setBracketClose() {
        viewModel.setInputedHistoryText(String.format(Locale.getDefault(),
            "%s", calcLogic.closeBracket()))
    }
    override fun getInit() {
        viewModel.setOutputResultText(calcLogic.getFinalResult())
        viewModel.setInputedHistoryText(String.format(Locale.getDefault(),
            "%s", createOutput()))
    }
    //endregion
}