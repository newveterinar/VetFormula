package com.pet.animal.formula.dose.health.veterinary.cure.model.formula

// Класс для хранения формул
class Formula {
    /** Задание переменных */ //region
    // Добавление первого элемента при создании формулы сразу в конструкторе
    private val formula: MutableList<TypedFormula> = mutableListOf()
    //endregion

    // Добавление новой типизированной формулы
    fun addTypedFormula(newTypedFormula: TypedFormula) {
        formula.add(newTypedFormula)
    }

    // Получение всей формулы
    fun getFormula(): MutableList<TypedFormula> {
        return formula
    }
}