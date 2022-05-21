package com.pet.animal.formula.dose.health.veterinary.cure.model.formula

class TypedFormula(
    val name: String,
    val elements: MutableList<Element>
) {
    // Добавление нового элемента в формулу
    fun addElement(newElement: Element) {
        elements.add(newElement)
    }
}