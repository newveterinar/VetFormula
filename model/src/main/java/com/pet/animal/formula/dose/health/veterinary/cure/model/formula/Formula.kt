package com.pet.animal.formula.dose.health.veterinary.cure.model.formula

// Класс для хранения формул
// Удалять формулу с помощью обнуления класса
// Если класс != null, значит, как минимум один элемент в формуле есть
class Formula(
    // Добавление первого элемента при создании формулы сразу в конструкторе
    var formula: MutableList<Element>
) {
    // Добавление нового элемента в формулу
    fun addElement(newElement: Element) {
        formula.add(newElement)
    }

    // Получение всей формулы
    @JvmName("getFormula1")
    fun getFormula(): MutableList<Element> {
        return formula
    }
}