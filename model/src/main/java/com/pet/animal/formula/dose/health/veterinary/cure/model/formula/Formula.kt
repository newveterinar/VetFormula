package com.pet.animal.formula.dose.health.veterinary.cure.model.formula

// Класс для хранения формул
class Formula {
    /** Задание переменных */ //region
    // Добавление первого элемента при создании формулы сразу в конструкторе
    private val formula: MutableList<TypedFormula> = mutableListOf()
    //endregion

    // Удаление всех типизированных формул
    fun clearAllTypedFormulas() {
        formula.clear()
    }

    // Добавление новой типизированной формулы
    fun addTypedFormula(newTypedFormula: TypedFormula) {
        formula.add(newTypedFormula)
    }

    // Получение всей формулы
    fun getTypedFormulas(): MutableList<TypedFormula> {
        return formula
    }
}