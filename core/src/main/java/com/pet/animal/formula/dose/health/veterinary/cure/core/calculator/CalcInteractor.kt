package com.pet.animal.formula.dose.health.veterinary.cure.core.calculator

interface CalcInteractor {
    // Отправить в калькулятор команду
    fun setCommand(button: Int)

    // Отправить в калькулятор число в виде строки из текстового поля
    fun setCommand(number: String)

    // Получить в виде строки все введённые команды
    fun getInputtedCommands(): String

    // Получить результат вычислений в виде числа, если результат возможен
    fun getCommandResultValue(): Double?

    // Получить результат вычислений в виде строки, если результат возможен
    fun getCommandResultString(): String?

    // Очистить калькулятор, удалив все ранее введённые команды
    fun clearCalc()
}