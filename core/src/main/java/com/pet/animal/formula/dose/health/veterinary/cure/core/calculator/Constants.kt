package com.pet.animal.formula.dose.health.veterinary.cure.core.calculator

enum class Command {
    NO_COMMAND,     // нет команды, в этом месте задаётся число с формы окна
    ZERO,           // кнопка 0       -  команда 10
    ONE,            // кнопка 1       -  команда 1
    TWO,            // кнопка 2       -  команда 2
    THREE,          // кнопка 3       -  команда 3
    FOUR,           // кнопка 4       -  команда 4
    FIVE,           // кнопка 5       -  команда 5
    SIX,            // кнопка 6       -  команда 6
    SEVEN,          // кнопка 7       -  команда 7
    EIGHT,          // кнопка 8       -  команда 8
    NINE,           // кнопка 9       -  команда 9
    ZPT_ON_OFF,     // кнопка ,       -  команда 11
    MINUS,          // кнопка -       -  команда 12
    PLUS,           // кнопка +       -  команда 13
    DIVIDE,         // кнопка /       -  команда 14
    MULTIPLY,       // кнопка *       -  команда 15
    BRACKET_OPEN,   // кнопка (       -  команда 16
    BRACKET_CLOSE,  // кнопка )       -  команда 17
    CHANGE_SIGN,    // кнопка +/-     -  команда 18
    PERCENT,        // кнопка %       -  команда 19
    STEPEN,         // кнопка X^n     -  команда 20
    SQUARE,         // кнопка Kx      -  команда 21
    EQUAL,          // кнопка =       -  команда 22
    DEL_ONE,        // кнопка <-      -  команда 23
    DEL_TWO,        // кнопка <--     -  команда 24
    DEL_ALL         // кнопка C       -  команда 25
}

fun Command.index(): Int {
    return when (this) {
        Command.NO_COMMAND -> return 0
        Command.ZERO -> return 10
        Command.ONE -> return 1
        Command.TWO -> return 2
        Command.THREE -> return 3
        Command.FOUR -> return  4
        Command.FIVE -> return 5
        Command.SIX -> return 6
        Command.SEVEN -> return 7
        Command.EIGHT -> return 8
        Command.NINE -> return 9
        Command.ZPT_ON_OFF -> return 11
        Command.MINUS -> return 12
        Command.PLUS -> return 13
        Command.DIVIDE -> return 14
        Command.MULTIPLY -> return 15
        Command.BRACKET_OPEN -> return 16
        Command.BRACKET_CLOSE -> return 17
        Command.CHANGE_SIGN -> return 18
        Command.PERCENT -> return 19
        Command.STEPEN -> return 20
        Command.SQUARE -> return 21
        Command.EQUAL -> return 22
        Command.DEL_ONE -> return 23
        Command.DEL_TWO -> return 24
        Command.DEL_ALL -> return 25
        else -> 0
    }
}