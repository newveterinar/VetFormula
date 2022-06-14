package com.pet.animal.formula.dose.health.veterinary.cure.utils

const val CICERONE_NAME: String = "cicerone"
const val MAIN_ACTIVITY_NAME: String = "MainActivity"

class FragmentScope {
    companion object{
        const val SHOW_MAIN_SCREEN_FRAGMENT_SCOPE = "SHOW_MAIN_SCREEN_FRAGMENT_SCOPE"
        const val SHOW_PHARMACY_FRAGMENT_SCOPE = "SHOW_PHARMACY_FRAGMENT_SCOPE"
        const val SHOW_PHARMACY_SURFACE_FRAGMENT_SCOPE = "SHOW_PHARMACY_SURFACE_FRAGMENT_SCOPE"
        const val SHOW_PHARMACY_SURFACE_RESULT_FRAGMENT_SCOPE =
            "SHOW_PHARMACY_SURFACE_RESULT_FRAGMENT_SCOPE"
        const val SHOW_PHARMACY_DOSES_FRAGMENT_SCOPE = "SHOW_PHARMACY_DOSES_FRAGMENT_SCOPE"
        const val SHOW_PHARMACY_DOSES_RESULT_FRAGMENT_SCOPE =
            "SHOW_PHARMACY_DOSES_RESULT_FRAGMENT_SCOPE"
        const val SHOW_PHARMACY_CRI_FRAGMENT_SCOPE = "SHOW_PHARMACY_CRI_FRAGMENT_SCOPE"
        const val SHOW_PHARMACY_CRI_RESULT_FRAGMENT_SCOPE =
            "SHOW_PHARMACY_CRI_RESULT_FRAGMENT_SCOPE"
        const val SHOW_CALCULATOR_FRAGMENT_SCOPE = "SHOW_CALCULATOR_FRAGMENT_SCOPE"
        const val SHOW_CALCULATOR_KEYBOARD_FRAGMENT_SCOPE =
            "SHOW_CALCULATOR_KEYBOARD_FRAGMENT_SCOPE"
        const val SHOW_ABOUT_FRAGMENT_SCOPE = "SHOW_ABOUT_FRAGMENT_SCOPE"
        const val SHOW_INFO_FRAGMENT_SCOPE = "SHOW_ABOUT_FRAGMENT_SCOPE"
        const val SHOW_SETTINGS_FRAGMENT_SCOPE = "SHOW_SETTINGS_FRAGMENT_SCOPE"
        const val SHOW_VETMEDICAL_VIEW_FRAGMENT_SCOPE = "SHOW_VETMEDICAL_VIEW_FRAGMENT_SCOPE"
        const val SHOW_EDIT_TEXT_FRAGMENT_SCOPE = "SHOW_EDIT_TEXT_FRAGMENT_SCOPE"
        const val SHOW_WSAVA_VIEW_FRAGMENT_SCOPE = "SHOW_WSAVA_VIEW_FRAGMENT_SCOPE"
    }
}

// SharedPreferences
const val SHARED_PREFERENCES_KEY: String = "Shared Preferences"
const val SHARED_PREFERENCES_THEME_KEY: String = "Shared Preferences Is Theme"

// Константы для WebView
const val VETMEDICAL_URL: String = "https://vetmedical.ru/"
const val WSAVA_URL: String = "https://wsava.org/"

// Тэги страниц нижнего окна
const val TAG_VETMEDICAL_BOTTOM_WINDOW: String = "vetmadical"
const val TAG_WSAVA_BOTTOM_WINDOW: String = "WSAVA"
const val TAG_NOTE_BOTTOM_WINDOW: String = "note"

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
const val PHARMACY_DOSES_NAME = "DOSES formula"
const val PHARMACY_CRI_NO_GIVING_SET_NAME = "CRI no giving set formula"
const val PHARMACY_CRI_20_DROPS_PER_ML_NAME = "CRI 20 drops/ml formula"
const val PHARMACY_CRI_60_DROPS_PER_ML_NAME = "CRI 60 drops/ml formula"

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
const val PHARMACY_DOSES_INDEX = 0
const val PHARMACY_CRI_NO_GIVING_SET_INDEX = 0
const val PHARMACY_CRI_20_DROPS_PER_ML_INDEX = 1
const val PHARMACY_CRI_60_DROPS_PER_ML_INDEX = 2

// Константы для таймера
const val DELAY_TIME: Long = 1000
const val START_VALUE: Double = 0.00
const val SHORT_CONSTANT = 100
const val LONG_CONSTANT = 200
const val START_LABEL_TIMER: String = "1:00"

// Индексы в массиве addFirstSecond
const val ADDFIRST_INDEX = 0
const val ADDSECOND_INDEX = 1

// Константы для конвертера размерностей
const val RESULT_VALUE_NOT_CHANGED: Double = 1.0
const val ERROR_VALUE: Double = -1.0
const val TEN_SECONDS: Double = 10.0
const val NUMBER_SECONDS_IN_MINUTE: Double = 60.0
const val NUMBER_SECONDS_IN_HOUR: Double = 3600.0
const val NUMBER_MINUTES_IN_HOUR: Double = 60.0
const val TEN_IN_PLUS_TWO_POWER: Double = 1E+2
const val TEN_IN_PLUS_THREE_POWER: Double = 1E+3
const val TEN_IN_PLUS_SIX_POWER: Double = 1E+6
const val TEN_IN_PLUS_NINE_POWER: Double = 1E+9
const val TEN_IN_MINUS_THREE_POWER: Double = 1E-3
const val TEN_IN_MINUS_SIX_POWER: Double = 1E-6
const val TEN_IN_MINUS_NINE_POWER: Double = 1E-9

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

// Количество навигационных кнопок на окнах
const val NUMBER_NAVIGATION_BUTTONS_ON_INPUT_DATA_SCREENS: Int = 2
const val NUMBER_NAVIGATION_BUTTONS_ON_OUTPUT_DATA_SCREENS: Int = 1


// Количество формул по параметру addFirst
const val PHARMACY_DOSES_ADDFIRST_FORMULA_NUMBER: Int = 1
const val PHARMACY_CRI_ADDFIRST_FORMULA_NUMBER: Int = 3

// Максимальные количества пользовательских элементов в формулах
const val PHARMACY_SURFACE_FORMULA_ELEMENT_COUNT: Int = 1
const val PHARMACY_DOSES_FORMULA_ELEMENT_COUNT: Int = 3
const val PHARMACY_CRI_FORMULA_ELEMENT_COUNT: Int = 5

// Слайдер
const val SLIDER_START_ANGLE: Float = 270f
const val SLIDER_MAX_VALUE: Float = 100f
const val SLIDER_MAX_DIFFERENT_VALUE: Float = 1f

// SpannableText
const val SQUARE_TEXT_RELATIVE_SIZE: Float = 0.65f

// Позиции размерностей mEq и U в списках размерностей
const val DIMENSION_MEQ_POSITION: Int = 3
const val DIMENSION_U_POSITION: Int = 4

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
    LENGTH,                         // Пример: м
    SQUARE_LENGTH,                  // Пример: м2
    VOLUME,                         // Пример: м3
    MASS,                           // Пример: кг
    MASS_DOSE_PER_KG_PER_TIME,      // Пример: кг/кг/с
    TIME,                           // Пример: с
    DROP_TIME_IN_SEC,               // Пример: капель за 1 с
    DROP_TIME_IN_TEN_SEC,           // Пример: капель за 10 с
    DROP_TIME_IN_MIN,               // Пример: капель за 1 мин
    RATE,                           // Пример: мл/с
    DROP,                           // Пример: капля
    ERROR_TYPE                      // Ошибка с выбором индекса
}