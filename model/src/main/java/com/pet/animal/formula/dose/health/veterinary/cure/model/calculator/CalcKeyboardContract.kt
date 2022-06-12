package com.pet.animal.formula.dose.health.veterinary.cure.model.calculator

interface CalcKeyboardContract {
    fun setMaxNumberSymbolsInOutputTextField(maxNumberSymbolsInOutputTextField: Int)
    fun addNumeral(newNumeral: Int): Double
    fun calculate()
    fun setCurZapitay()
    fun clearAll()
    fun clearOne()
    fun clearTwo()
    fun setNewAction(action: CalcConstants.ACTIONS)
    fun changeSign()
    fun setNewFunction(typeFuncInBracket: CalcConstants.FUNCTIONS): String
    fun getPressedZapitay(): Boolean
    fun createOutput(): String
    fun getFinalResult(): String
    fun setEqual()
    fun getError()
    fun setBracketOpen()
    fun setBracketClose()
    fun getInit()
}