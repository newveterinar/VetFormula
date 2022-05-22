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
    // Переменные для окна Fluids
    // TODO: Добавить классы для сохранения данных в окнах раздела Fluids
    // Переменные для окна Hematology
    // TODO: Добавить классы для сохранения данных в окнах раздела Hematology
    // Переменные для окна Conversion
    // TODO: Добавить классы для сохранения данных в окнах раздела Conversion
    // Переменные для окна Pharmacy
    private var pharmacySurfaceScreenData: ScreenData = ScreenData()
    private var pharmacySurfaceResultScreenData: ScreenData = ScreenData()
    private var pharmacyDoseScreenData: ScreenData = ScreenData()
    private var pharmacyCRIScreenData: ScreenData = ScreenData()
    // Переменные для окна с газовым наркозом
    // TODO: Добавить классы для сохранения данных в окнах раздела с газовым наркозом
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
                pharmacySurfaceScreenData.listsAddFirstSecond.clear()
                pharmacySurfaceScreenData.valueFields.clear()
                listsAddFirstSecond.forEach {
                    pharmacySurfaceScreenData.listsAddFirstSecond.add(it)
                }
                stringValues.forEachIndexed { index, it ->
                    pharmacySurfaceScreenData.valueFields.add(
                        ValueField(it, values[index], dimensions[index]))
                }
            }
            // Окно по умолчанию
            else -> { /* TODO: Сделать действие по умолчанию */ }
        }
    }
    // Получение данных окна PharmacySurface
    override fun getPharmacySurfaceScreenData(): ScreenData {
        return pharmacySurfaceScreenData
    }
    // Получение данных окна PharmacySurface
    override fun getPharmacySurfaceResultScreenData(): ScreenData {
        return pharmacySurfaceResultScreenData
    }
    override fun getPharmacyDosesScreenData(): ScreenData {
        return pharmacyDoseScreenData
    }
    //endregion
}