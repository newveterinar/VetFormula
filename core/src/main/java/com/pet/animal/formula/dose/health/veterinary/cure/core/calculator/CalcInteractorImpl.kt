package com.pet.animal.formula.dose.health.veterinary.cure.core.calculator

import com.pet.animal.formula.dose.health.veterinary.cure.model.calculator.CalcConstants

class CalcInteractorImpl : CalcInteractor {
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
        when (button) {
            1 -> calcLogic.addNumeral(1)
            2 -> calcLogic.addNumeral(2)
            3 -> calcLogic.addNumeral(3)
            4 -> calcLogic.addNumeral(4)
            5 -> calcLogic.addNumeral(5)
            6 -> calcLogic.addNumeral(6)
            7 -> calcLogic.addNumeral(7)
            8 -> calcLogic.addNumeral(8)
            9 -> calcLogic.addNumeral(9)
            10 -> calcLogic.addNumeral(0)
            11 -> calcLogic.setCurZapitay()
            12 -> calcLogic.setNewAction(CalcConstants.ACTIONS.ACT_MINUS)
            13 -> calcLogic.setNewAction(CalcConstants.ACTIONS.ACT_PLUS)
            14 -> calcLogic.setNewAction(CalcConstants.ACTIONS.ACT_DIV)
            15 -> calcLogic.setNewAction(CalcConstants.ACTIONS.ACT_MULTY)
            16 -> calcLogic.setNewFunction(CalcConstants.FUNCTIONS.FUNC_NO)
            17 -> calcLogic.closeBracket()
            18 -> calcLogic.changeSign()
            // Задаётся универсальное значение ACT_PERS_MULTY и оно уточняется в методе setNewAction
            19 -> calcLogic.setNewAction(CalcConstants.ACTIONS.ACT_PERS_MULTY)
            20 -> calcLogic.setNewAction(CalcConstants.ACTIONS.ACT_STEP)
            21 -> calcLogic.setNewFunction(CalcConstants.FUNCTIONS.FUNC_SQRT)
            22 -> calcLogic.calculate()
            23 -> calcLogic.clearOne()
            24 -> calcLogic.clearTwo()
            25 -> calcLogic.clearAll()
        }
    }

    // Отправить в калькулятор число в виде строки из текстового поля
    override fun setCommand(number: String) {
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