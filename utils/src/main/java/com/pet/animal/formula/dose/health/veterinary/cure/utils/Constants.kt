package com.pet.animal.formula.dose.health.veterinary.cure.utils

const val CICERONE_NAME: String = "cicerone"
const val MAIN_ACTIVITY_NAME: String = "MainActivity"

// Класс с названиями тем
enum class ThemesNames {
    // Светлая тема
    DAY,
    // Тёмная тема
    NIGHT
}

// Класс с типами формул
enum class FormulaType {
    // Типы формул для раздела FLUIDS
    FLUIDS_BASIC,                           // При переводе в Int соответствует номеру 1
    FLUIDS_KOMPREHENSIVE,                   // При переводе в Int соответствует номеру 2
    FLUIDS_K_INFUSION,                      // При переводе в Int соответствует номеру 3
    // Типы формул для раздела CONVERSTION
    CONVERSION_UNITS,                       // При переводе в Int соответствует номеру 4
    // Типы формул для раздела HEMATOLOGY
    HEMATOLOGY_BLOOD,                       // При переводе в Int соответствует номеру 5
    HEMATOLOGY_FLEBOTOMY,                   // При переводе в Int соответствует номеру 6
    // Типы формул для раздела PHARMACY
    PHARMACY_DOSES,                         // При переводе в Int соответствует номеру 7
    PHARMACY_CRI,                           // При переводе в Int соответствует номеру 8
    PHARMACY_SURFACE,                       // При переводе в Int соответствует номеру 9
    // Типы формул для раздела CALCULATOR
    CALCULATOR                              // При переводе в Int соответствует номеру 10
}