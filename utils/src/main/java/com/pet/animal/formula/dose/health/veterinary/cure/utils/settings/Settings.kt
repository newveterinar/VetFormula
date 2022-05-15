package com.pet.animal.formula.dose.health.veterinary.cure.utils.settings

import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Formula
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.ScreenData
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ThemesNames

interface Settings {
    // Установка темы приложения
    suspend fun setTheme(theme: ThemesNames)
    // Получение текущей темы приложения
    suspend fun getTheme(): ThemesNames
    // Сохранение текущей формулы
    suspend fun setFormula(formula: Formula)
    // Получение текущей формулы
    suspend fun getFormula(): Formula
    // Задание данных для окна PharmacySurface
    suspend fun setScreenData(formulaType: ScreenType,
                      listsAddFirstSecond: List<Int>,
                      values: List<Double>,
                      dimensions: List<Int>)
    // Получение данных окна PharmacySurface
    suspend fun getPharmacySurfaceScreenData(): ScreenData
}