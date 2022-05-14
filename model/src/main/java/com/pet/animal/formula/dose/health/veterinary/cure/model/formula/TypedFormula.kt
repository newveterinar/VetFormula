package com.pet.animal.formula.dose.health.veterinary.cure.model.formula

class TypedFormula(
    private val name: String,
    private val typedFormula: MutableList<Element>
) {
    // Добавление нового элемента в формулу
    fun addElement(newElement: Element) {
        typedFormula.add(newElement)
    }

    // Получение типизированной формулы
    fun getTypedFormula(): MutableList<Element> {
        return typedFormula
    }
}