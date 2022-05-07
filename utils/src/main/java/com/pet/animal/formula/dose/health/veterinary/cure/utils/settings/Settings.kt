package com.pet.animal.formula.dose.health.veterinary.cure.utils.settings

import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Formula
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
}