package com.pet.animal.formula.dose.health.veterinary.cure.utils.settings

import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Formula
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.ScreenData
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.ValueField
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ThemesNames

class SettingsImpl: Settings {
    /** Задание переменных */ //region
    // Переменная для хранения темы приложения (ThemesNames.DAY или ThemesNames.NIGHT)
    private var currentTheme: ThemesNames = ThemesNames.DAY
    // Переменная для хранения текущей формулы
    private var formula: Formula = Formula()
    // Переменные для хранения исходных данных пользователя в расчётном окне
    private var inputedScreenData: ScreenData = ScreenData()
    //endregion

    // Задание темы приложения
    override fun setTheme(theme: ThemesNames) {
        currentTheme = theme
    }
    // Получение текущей темы приложения
    override fun getTheme(): ThemesNames {
        return currentTheme
    }
    // Задание формулы
    override fun setFormula(formula: Formula) {
        this.formula = formula
    }
    // Получение формулы
    override fun getFormula(): Formula {
        return formula
    }

    /** Функции для работы с данными окон */ // region
    // Задание данных для окон
    override fun setScreenData(screenType: ScreenType,
                               listsAddFirstSecond: List<Int>,
                               stringValues: List<String>,
                               values: List<Double>,
                               dimensions: List<Int>) {
        when(screenType) {
            // Окно PharmacySurface
            ScreenType.PHARMACY_SURFACE -> {
                inputedScreenData.listsAddFirstSecond.clear()
                inputedScreenData.valueFields.clear()
                listsAddFirstSecond.forEach {
                    inputedScreenData.listsAddFirstSecond.add(it)
                }
                stringValues.forEachIndexed { index, it ->
                    inputedScreenData.valueFields.add(
                        ValueField(it, values[index], dimensions[index]))
                }
            }
            // Окно по умолчанию
            else -> { /* TODO: Сделать действие по умолчанию */ }
        }
    }
    // Получение исходных данных данных окна PharmacySurface
    override fun getInputedScreenData(): ScreenData {
        return inputedScreenData
    }
    //endregion
}