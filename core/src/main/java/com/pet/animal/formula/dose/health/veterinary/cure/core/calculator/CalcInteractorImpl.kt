package com.pet.animal.formula.dose.health.veterinary.cure.core.calculator

import com.pet.animal.formula.dose.health.veterinary.cure.model.calculator.CalcConstants

class CalcInteractorImpl: CalcInteractor {
    /** Задание переменных */ //region
    private val calcLogic: CalcLogic = CalcLogic()
    //endregion

    // Отправить в калькулятор команду
    override fun setCommand(button: Int) {
        /* Расшифровка команд
           Кнопка  -  Номер команды
           1       -  1
           2       -  2
           3       -  3
           4       -  4
           5       -  5
           6       -  6
           7       -  7
           8       -  8
           9       -  9
           0       -  10
           ,       -  11
           -       -  12
           +       -  13
           /       -  14
           *       -  15
           (       -  16
           )       -  17
           +/-     -  18
           %       -  19
           X^n     -  20
           Kx      -  21
           =       -  22
           <-      -  23
           <--     -  24
           C       -  25
         */
        when(button) {
            Command.ONE.index() -> calcLogic.addNumeral(1)
            Command.TWO.index() -> calcLogic.addNumeral(2)
            Command.THREE.index() -> calcLogic.addNumeral(3)
            Command.FOUR.index() -> calcLogic.addNumeral(4)
            Command.FIVE.index() -> calcLogic.addNumeral(5)
            Command.SIX.index() -> calcLogic.addNumeral(6)
            Command.SEVEN.index() -> calcLogic.addNumeral(7)
            Command.EIGHT.index() -> calcLogic.addNumeral(8)
            Command.NINE.index() -> calcLogic.addNumeral(9)
            Command.ZERO.index() -> calcLogic.addNumeral(0)
            Command.ZPT_ON_OFF.index() -> calcLogic.setCurZapitay()
            Command.MINUS.index() -> calcLogic.setNewAction(CalcConstants.ACTIONS.ACT_MINUS)
            Command.PLUS.index() -> calcLogic.setNewAction(CalcConstants.ACTIONS.ACT_PLUS)
            Command.DIVIDE.index() -> calcLogic.setNewAction(CalcConstants.ACTIONS.ACT_DIV)
            Command.MULTIPLY.index() -> calcLogic.setNewAction(CalcConstants.ACTIONS.ACT_MULTY)
            Command.BRACKET_OPEN.index() -> calcLogic.setNewFunction(CalcConstants.FUNCTIONS.FUNC_NO)
            Command.BRACKET_CLOSE.index() -> calcLogic.closeBracket()
            Command.CHANGE_SIGN.index() -> calcLogic.changeSign()
            // Задаётся универсальное значение ACT_PERS_MULTY и оно уточняется в методе setNewAction
            Command.PERCENT.index() -> calcLogic.setNewAction(CalcConstants.ACTIONS.ACT_PERS_MULTY)
            Command.STEPEN.index() -> calcLogic.setNewAction(CalcConstants.ACTIONS.ACT_STEP)
            Command.SQUARE.index() -> calcLogic.setNewFunction(CalcConstants.FUNCTIONS.FUNC_SQRT)
            Command.EQUAL.index() -> calcLogic.calculate()
            Command.DEL_ONE.index() -> calcLogic.clearOne()
            Command.DEL_TWO.index() -> calcLogic.clearTwo()
            Command.DEL_ALL.index() -> calcLogic.clearAll()
        }
    }

    // Отправить в калькулятор число в виде строки из текстового поля
    override fun setCommand(number: String) {
        calcLogic.setCurrentNumber(number)
    }

    // Отправить в калькулятор сразу число типа Double
    override fun setCommand(number: Double) {
        calcLogic.setCurrentNumber(number)
    }

    // Получить в виде строки все введённые команды
    override fun getInputtedCommands(): String {
//        TODO("Доработать и вывести заданную строчку для 3 релиза приложения")
        return ""
    }

    // Получить результат вычислений в виде числа, если результат возможен
    override fun getCommandResultValue(): Double? {
        calcLogic.calculate()
        return if (calcLogic.errorCode == CalcConstants.ERRORS.NO) {
            calcLogic.finalResultValue
        } else {
            calcLogic.clearErrorCode()
            null
        }
    }

    // Получить результат вычислений в виде строки, если результат возможен
    override fun getCommandResultString(): String? {
        calcLogic.calculate()
        return if (calcLogic.errorCode == CalcConstants.ERRORS.NO) {
            calcLogic.finalResult
        } else {
            calcLogic.clearErrorCode()
            null
        }
    }

    // Очистить калькулятор, удалив все ранее введённые команды
    override fun clearCalc() {
        calcLogic.clearAll()
    }
}