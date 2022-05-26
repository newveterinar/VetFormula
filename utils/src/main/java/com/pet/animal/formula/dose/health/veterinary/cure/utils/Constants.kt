package com.pet.animal.formula.dose.health.veterinary.cure.utils

const val CICERONE_NAME: String = "cicerone"
const val MAIN_ACTIVITY_NAME: String = "MainActivity"

class FragmentScope() {
    companion object {
        const val SHOW_PHARMACY_SURFACE_FRAGMENT_SCOPE = "SHOW_PHARMACY_SURFACE_FRAGMENT_SCOPE"
        const val SHOW_PHARMACY_DOSE_FRAGMENT_SCOPE = "SHOW_PHARMACY_DOSE_FRAGMENT_SCOPE"
    }
}

const val SHARED_PREFERENCES_KEY: String = "Shared Preferences"
const val SHARED_PREFERENCES_THEME_KEY: String = "Shared Preferences Is Theme"

// Названия типизированных формул для класса TypedFormula()
const val PHARMACY_SURFACE_DOG_BODYSURFACEAREA_NAME = "DOG surface area"
const val PHARMACY_SURFACE_CAT_BODYSURFACEAREA_NAME = "CAT surface area"
const val PHARMACY_SURFACE_RABBIT_BODYSURFACEAREA_NAME = "RABBIT surface area"
const val PHARMACY_SURFACE_POLECAT_BODYSURFACEAREA_NAME = "POLECAT surface area"
const val PHARMACY_SURFACE_GUINEAPIG_BODYSURFACEAREA_NAME = "GUINEAPIG surface area"
const val PHARMACY_SURFACE_HAMSTER_BODYSURFACEAREA_NAME = "HAMSTER surface area"
const val PHARMACY_SURFACE_HORSEEXCEPTLOMUSTIN_BODYSURFACEAREA_NAME =
    "HORSEEXCEPTLOMUSTIN surface area"
const val PHARMACY_SURFACE_HORSEONLYLOMUSTIN_BODYSURFACEAREA_NAME = "HORSEONLYLOMUSTIN surface area"
const val PHARMACY_SURFACE_RAT_BODYSURFACEAREA_NAME = "RAT surface area"
const val PHARMACY_SURFACE_MOUSE_BODYSURFACEAREA_NAME = "MOUSE surface area"

// Индексы в массиве addFirstSecond типа Int для класса TypedFormula()
const val PHARMACY_SURFACE_DOG_INDEX = 0
const val PHARMACY_SURFACE_CAT_INDEX = 1
const val PHARMACY_SURFACE_RABBIT_INDEX = 2
const val PHARMACY_SURFACE_POLECAT_INDEX = 3
const val PHARMACY_SURFACE_GUINEAPIG_INDEX = 4
const val PHARMACY_SURFACE_HAMSTER_INDEX = 5
const val PHARMACY_SURFACE_HORSEEXCEPTLOMUSTIN_INDEX = 6
const val PHARMACY_SURFACE_HORSEONLYLOMUSTIN_INDEX = 7
const val PHARMACY_SURFACE_RAT_INDEX = 8
const val PHARMACY_SURFACE_MOUSE_INDEX = 9

// Индексы в массиве addFirstSecond
const val ADDFIRST_INDEX = 0
const val ADDSECOND_INDEX = 1

// Класс с названиями тем
enum class ThemesNames {
    // Светлая тема
    DAY,

    // Тёмная тема
    NIGHT
}

// Класс с типами формул
enum class ScreenType {
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

// Слайдер
const val SLIDER_START_ANGLE: Float = 270f
const val SLIDER_MAX_VALUE: Float = 100f
const val SLIDER_MAX_DIFFERENT_VALUE: Float = 1f