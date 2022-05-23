package com.pet.animal.formula.dose.health.veterinary.cure.utils.settings

import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Formula
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.ScreenData
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ThemesNames

interface Settings {
    // Установка темы приложения
    fun setTheme(theme: ThemesNames)
    // Получение текущей темы приложения
    fun getTheme(): ThemesNames
    // Сохранение текущей формулы
    fun setFormula(formula: Formula)
    // Получение текущей формулы
    fun getFormula(): Formula
    // Задание данных для окна PharmacySurface
    fun setScreenData(formulaType: ScreenType,
                      listsAddFirstSecond: List<Int>,
                      stringValues: List<String>,
                      values: List<Double>,
                      dimensions: List<Int>)
    // Получение данных окна PharmacySurface
    fun getPharmacySurfaceScreenData(): ScreenData
    // Получение данных окна PharmacySurfaceResult
    fun getPharmacySurfaceResultScreenData(): ScreenData
    // Получение данных окна PharmacyDoses
    fun getPharmacyDosesScreenData(): ScreenData
}