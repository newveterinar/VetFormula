package com.pet.animal.formula.dose.health.veterinary.cure.utils

const val CICERONE_NAME: String = "cicerone"
const val MAIN_ACTIVITY_NAME: String = "MainActivity"
class FragmentScope() {
    companion object{
        const val  SHOW_PHARMACY_SURFACE_FRAGMENT_SCOPE = "SHOW_PHARMACY_SURFACE_FRAGMENT_SCOPE"
        const val  SHOW_PHARMACY_SURFACE_RESULT_FRAGMENT_SCOPE =
            "SHOW_PHARMACY_SURFACE_RESULT_FRAGMENT_SCOPE"
        const val  SHOW_PHARMACY_DOSE_FRAGMENT_SCOPE = "SHOW_PHARMACY_DOSE_FRAGMENT_SCOPE"
    }
}

// Названия типизированных формул для класса TypedFormula()
const val PHARMACY_SURFACE_DOG_BODYSURFACEAREA_NAME = "DOG surface area"
const val PHARMACY_SURFACE_CAT_BODYSURFACEAREA_NAME = "CAT surface area"
const val PHARMACY_SURFACE_RABBIT_BODYSURFACEAREA_NAME = "RABBIT surface area"
const val PHARMACY_SURFACE_POLECAT_BODYSURFACEAREA_NAME = "POLE_C_A_T surface area"
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
    FLUIDS_BASIC,                           // При переводе (ordinal) в Int соответствует номеру 0
    FLUIDS_KOMPREHENSIVE,                   // При переводе (ordinal) в Int соответствует номеру 1
    FLUIDS_K_INFUSION,                      // При переводе (ordinal) в Int соответствует номеру 2
    // Типы формул для раздела CONVERSTION
    CONVERSION_UNITS,                       // При переводе (ordinal) в Int соответствует номеру 3
    // Типы формул для раздела HEMATOLOGY
    HEMATOLOGY_BLOOD,                       // При переводе (ordinal) в Int соответствует номеру 4
    HEMATOLOGY_FLEBOTOMY,                   // При переводе (ordinal) в Int соответствует номеру 5
    // Типы формул для раздела PHARMACY
    PHARMACY_DOSES,                         // При переводе (ordinal) в Int соответствует номеру 6
    PHARMACY_CRI,                           // При переводе (ordinal) в Int соответствует номеру 7
    PHARMACY_SURFACE,                       // При переводе (ordinal) в Int соответствует номеру 8
    // Типы формул для раздела CALCULATOR
    CALCULATOR                              // При переводе (ordinal) в Int соответствует номеру 9
}

// Слайдер
const val SLIDER_START_ANGLE: Float = 270f
const val SLIDER_MAX_VALUE: Float = 100f
const val SLIDER_MAX_DIFFERENT_VALUE: Float = 1f

// SpannableText
const val SQUARE_TEXT_RELATIVE_SIZE: Float = 0.65f

// Типы размерностей исходных данных
enum class InputDataDimensionType {
    WEIGHT_ANIMAL,
    MASS_DOSE_PER_KG,
    MASS_DOSE_PER_KG_PER_TIME,
    VOLUME_DOSE_PER_KG_PER_TIME,
    CONCENTRATION,
    VOLUME,
    ERROR_TYPE
}

// Типы размерностей результирующих (выходных) данных
enum class OutputDataDimensionType {
    LENGTH,
    MASS,
    TIME,
    SQUARE_LENGTH
}