package com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata

class ScreenData() {
    /** Исходные данные */ //region
    // Исходные данные для проведения расчётов
    var listsAddFirstSecond: MutableList<Int> = mutableListOf()
    var valueFields: MutableList<ValueField> = mutableListOf()
    // Признак перехода на экран с результатами расчётов
    var isGoToResultScreen: Boolean = false
    // Результаты расчётов
    var resultValueField: MutableList<ResultValueField> = mutableListOf()
    //endregion

    // Установка признака перехода на окно с получением результатов расчёта
    constructor(isGoToResultScreen: Boolean): this() {
        this.isGoToResultScreen = isGoToResultScreen
    }

    // Сохранение результата расчёта
    constructor(resultValueField: MutableList<ResultValueField>): this() {
        this.resultValueField.clear()
        this.resultValueField = resultValueField
    }
}