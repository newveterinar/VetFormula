package com.pet.animal.formula.dose.health.veterinary.cure.utils.settings

import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Formula
import com.pet.animal.formula.dose.health.veterinary.cure.utils.R
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ThemesNames
import com.pet.animal.formula.dose.health.veterinary.cure.utils.resources.ResourcesProviderImpl
import org.koin.java.KoinJavaComponent

class SettingsImpl: Settings {
    /** Задание переенных */ //region
    // ResourcesProviderImpl
    private val resourcesProviderImpl: ResourcesProviderImpl = KoinJavaComponent.getKoin().get()
    // Переменная для хранения темы приложения (ThemesNames.DAY или ThemesNames.NIGHT)
    private var currentTheme: ThemesNames = ThemesNames.DAY
    // Переменная для хранения текущей формулы
    private lateinit var formula: Formula
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
        if (this::formula.isInitialized) {
            return formula
        } else {
            // TODO сделать единый класс для обработки и оповещения пользователя
            //  о всех ошибках в приложении
            throw UninitializedPropertyAccessException(
                resourcesProviderImpl.getString(R.string.error_variable_not_initialized))
        }
    }
}