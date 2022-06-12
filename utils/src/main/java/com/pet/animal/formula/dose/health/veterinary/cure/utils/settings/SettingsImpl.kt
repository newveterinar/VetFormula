package com.pet.animal.formula.dose.health.veterinary.cure.utils.settings

import com.pet.animal.formula.dose.health.veterinary.cure.model.calculator.CalcConstants.CALCULATOR_CURRENT_RADIUS_BUTTONS
import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Formula
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.ScreenData
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.ValueField
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ThemesNames
import com.pet.animal.formula.dose.health.veterinary.cure.utils.VETMEDICAL_URL
import com.pet.animal.formula.dose.health.veterinary.cure.utils.WSAVA_URL

class SettingsImpl: Settings {
    /** Задание переменных */ //region
    // Переменная для хранения темы приложения (ThemesNames.DAY или ThemesNames.NIGHT)
    private var currentTheme: ThemesNames = ThemesNames.DAY
    // Переменная для хранения текущей формулы
    private var formula: Formula = Formula()
    // Переменные для хранения исходных данных пользователя в расчётном окне
    private var inputedScreenData: ScreenData = ScreenData()
    // Переменная для хранения радиуса кнопок в калькуляторе
    private var calculatorRadiusButtons: Int = CALCULATOR_CURRENT_RADIUS_BUTTONS
    // Переменные для хранения текущей ссылки на сайте Vetmedical
    private var isVetmedicalOnline: Boolean = false
    private var currentVetmedicalUrl: String = VETMEDICAL_URL
    // Переменные для хранения текущей ссылки на сайте Wsava
    private var isWsavaOnline: Boolean = false
    private var currentWsavaUrl: String = WSAVA_URL
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
    override suspend fun setFormula(formula: Formula) {
        this.formula = formula
    }
    // Получение формулы
    override fun getFormula(): Formula {
        return formula
    }

    /** Функции для работы с данными окон */ // region
    // Задание данных для текущего окна
    override fun setScreenData(screenType: ScreenType,
                               listsAddFirstSecond: List<Int>,
                               stringValues: List<String>,
                               values: List<Double>,
                               dimensions: List<Int>) {
        inputedScreenData.screenTypeIndex = screenType.ordinal
        inputedScreenData.listsAddFirstSecond.clear()
        listsAddFirstSecond.forEach {
            inputedScreenData.listsAddFirstSecond.add(it)
        }
        inputedScreenData.valueFields.clear()
        stringValues.forEachIndexed { index, it ->
            inputedScreenData.valueFields.add(
                ValueField(it, values[index], dimensions[index]))
        }
    }
    // Получение исходных данных для текущего окна
    override fun getInputedScreenData(): ScreenData {
        return inputedScreenData
    }
    //endregion

    //region Методы для задания и получения текущего значения радиуса кнопок в калькуляторе
    fun setCalculatorRadiusButtons(newRadius: Int) {
        calculatorRadiusButtons = newRadius
    }
    fun getCalculatorRadiusButtons(): Int {
        return calculatorRadiusButtons
    }
    //endregion

    //region Методы для сохранения текущей ссылки в окне VetMedicalViewFragment
    fun getIsVetmedicalOnline(): Boolean {
        return isVetmedicalOnline
    }
    fun setCurrentVetmedicalUrl(currentVetmedicalUrl: String) {
        isVetmedicalOnline = true
        this.currentVetmedicalUrl = currentVetmedicalUrl
    }
    fun getCurrentVetmedicalUrl(): String {
        return currentVetmedicalUrl
    }
    //endregion

    //region Методы для сохранения текущей ссылки в окне WsavaViewFragment
    fun getIsWsavaOnline(): Boolean {
        return isWsavaOnline
    }
    fun setCurrentWsavaUrl(currentWsavaUrl: String) {
        isWsavaOnline = true
        this.currentWsavaUrl = currentWsavaUrl
    }
    fun getCurrentWsavaUrl(): String {
        return currentWsavaUrl
    }
    //endregion
}