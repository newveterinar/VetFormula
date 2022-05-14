package com.pet.animal.formula.dose.health.veterinary.cure.utils.settings

import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Formula
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.ScreenData
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ThemesNames
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.valueFieldListCreator

class SettingsImpl: Settings {
    /** Задание переменных */ //region
    // Переменная для хранения темы приложения (ThemesNames.DAY или ThemesNames.NIGHT)
    private var currentTheme: ThemesNames = ThemesNames.DAY
    // Переменная для хранения текущей формулы
    private var formula: List<Formula> = listOf()
    // Переменные для окна Fluids
    // TODO: Добавить классы для сохранения данных в окнах раздела Fluids
    // Переменные для окна Hematology
    // TODO: Добавить классы для сохранения данных в окнах раздела Hematology
    // Переменные для окна Conversion
    // TODO: Добавить классы для сохранения данных в окнах раздела Conversion
    // Переменные для окна Pharmacy
    private var pharmacySurfaceScreenData: ScreenData = ScreenData()
    private var pharmacyDoseScreenData: ScreenData = ScreenData()
    private var pharmacyCRIScreenData: ScreenData = ScreenData()
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
    override fun setFormula(formula: List<Formula>) {
        this.formula = formula
    }
    // Получение формулы
    override fun getFormula(): List<Formula> {
        return formula
    }

    /** Функции для работы с данными окон */ // region
    // Задание данных для окон
    override fun setScreenData(screenType: ScreenType,
                               listsAddFirstSecond: List<Int>,
                               values: List<Double>,
                               dimensions: List<Int>) {
        when(screenType) {
            // Окно PharmacySurface
            ScreenType.PHARMACY_SURFACE -> {
                pharmacySurfaceScreenData.listsAddFirstSecond = listsAddFirstSecond
                pharmacySurfaceScreenData.valueFields = valueFieldListCreator(values, dimensions)
            }
            // Окно по умолчанию
            else -> { /* TODO: Сделать действие по умолчанию */ }
        }
    }
    // Получение данных окна PharmacySurface
    override fun getPharmacySurfaceScreenData(): ScreenData {
        return pharmacySurfaceScreenData
    }
    //endregion
}