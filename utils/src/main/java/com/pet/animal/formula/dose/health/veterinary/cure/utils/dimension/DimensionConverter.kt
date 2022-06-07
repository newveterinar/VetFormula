package com.pet.animal.formula.dose.health.veterinary.cure.utils.dimension

import android.widget.Toast
import com.pet.animal.formula.dose.health.veterinary.cure.utils.*
import com.pet.animal.formula.dose.health.veterinary.cure.utils.resources.ResourcesProviderImpl
import org.koin.java.KoinJavaComponent

// Конвертрер типов исходных данных
fun inputDataDimensionConverter(
    inputDataDimensionType: InputDataDimensionType,
    index: Int,
    checkIndex: Int
): Double {
    /** Задание переменных */ //region
    var result: Double = -1.0
    //endregion
    when(inputDataDimensionType) {
        InputDataDimensionType.WEIGHT_ANIMAL -> {
            result = when(index) {
                0 -> RESULT_VALUE_NOT_CHANGED
                1 -> TEN_IN_MINUS_THREE_POWER
                else -> ERROR_VALUE
            }
        }
        InputDataDimensionType.MASS_DOSE_PER_KG -> {
            result = when(index) {
                0 -> TEN_IN_MINUS_NINE_POWER
                1 -> TEN_IN_MINUS_SIX_POWER
                2 -> TEN_IN_MINUS_THREE_POWER
                3 -> {
                    if (checkIndex != 3) ERROR_VALUE
                    else RESULT_VALUE_NOT_CHANGED
                }
                4 -> {
                    if (checkIndex != 4) ERROR_VALUE
                    else RESULT_VALUE_NOT_CHANGED
                }
                else -> ERROR_VALUE
            }
        }
        InputDataDimensionType.MASS_DOSE_PER_KG_PER_TIME -> {
            result = when(index) {
                0 -> TEN_IN_MINUS_NINE_POWER * NUMBER_SECONDS_IN_MINUTE
                1 -> TEN_IN_MINUS_SIX_POWER * NUMBER_SECONDS_IN_MINUTE
                2 -> TEN_IN_MINUS_THREE_POWER * NUMBER_SECONDS_IN_MINUTE
                3 -> TEN_IN_MINUS_NINE_POWER * NUMBER_SECONDS_IN_HOUR
                4 -> TEN_IN_MINUS_SIX_POWER * NUMBER_SECONDS_IN_HOUR
                5 -> TEN_IN_MINUS_THREE_POWER * NUMBER_SECONDS_IN_HOUR
                else -> ERROR_VALUE
            }
        }
        InputDataDimensionType.VOLUME_DOSE_PER_KG_PER_TIME -> { // Rate
            result = when(index) {
                0 -> TEN_IN_MINUS_SIX_POWER * NUMBER_SECONDS_IN_HOUR
                1 -> TEN_IN_MINUS_NINE_POWER * NUMBER_SECONDS_IN_HOUR
                2 -> TEN_IN_MINUS_SIX_POWER * NUMBER_SECONDS_IN_MINUTE
                3 -> TEN_IN_MINUS_NINE_POWER * NUMBER_SECONDS_IN_MINUTE
                else -> ERROR_VALUE
            }
        }
        InputDataDimensionType.CONCENTRATION -> { // Formulation
            result = when(index) {
                0 -> TEN_IN_MINUS_NINE_POWER / TEN_IN_MINUS_SIX_POWER
                1 -> TEN_IN_MINUS_SIX_POWER / TEN_IN_MINUS_SIX_POWER
                2 -> TEN_IN_MINUS_THREE_POWER / TEN_IN_MINUS_SIX_POWER
                3 -> {
                    if (checkIndex != 3) ERROR_VALUE
                    else RESULT_VALUE_NOT_CHANGED / TEN_IN_MINUS_SIX_POWER
                }
                4 -> {
                    if (checkIndex != 4) ERROR_VALUE
                    else RESULT_VALUE_NOT_CHANGED / TEN_IN_MINUS_SIX_POWER
                }
                5 -> TEN_IN_MINUS_NINE_POWER / TEN_IN_MINUS_THREE_POWER
                6 -> TEN_IN_MINUS_SIX_POWER / TEN_IN_MINUS_THREE_POWER
                7 -> TEN_IN_MINUS_THREE_POWER / TEN_IN_MINUS_THREE_POWER
                8 -> TEN_IN_MINUS_SIX_POWER / TEN_IN_PLUS_TWO_POWER
                else -> ERROR_VALUE
            }
        }
        InputDataDimensionType.VOLUME -> {
            result = when(index) {
                0 -> TEN_IN_MINUS_SIX_POWER
                1 -> TEN_IN_MINUS_NINE_POWER
                else -> ERROR_VALUE
            }
        }
        else -> result = ERROR_VALUE
    }
    return result
}

// Конвертер типов результирующих значений
fun outputDataDimensionConverter(
    screenType: ScreenType,
    outputDataDimensionType: OutputDataDimensionType,
    currentResult: Double,
    dimension: List<Int>
): Double {
    /** Задание переменных */ //region
    // Задание начального значения для результата
    var result: Double = RESULT_VALUE_NOT_CHANGED
    // Получение доступа к ресурсам
    val resourcesProviderImpl: ResourcesProviderImpl = KoinJavaComponent.getKoin().get()
    //endregion

    when(outputDataDimensionType) {
        // Имеет только академический смысл
        OutputDataDimensionType.LENGTH -> result = currentResult * RESULT_VALUE_NOT_CHANGED
        // PharmacySurfaceResult
        OutputDataDimensionType.SQUARE_LENGTH -> result = currentResult * RESULT_VALUE_NOT_CHANGED
        OutputDataDimensionType.VOLUME -> {
            when(screenType) {
                ScreenType.PHARMACY_DOSES -> {
                    result = when(dimension[2]) {
                        0 -> currentResult * TEN_IN_PLUS_SIX_POWER
                        1 -> currentResult * TEN_IN_PLUS_SIX_POWER
                        2 -> currentResult * TEN_IN_PLUS_SIX_POWER
                        3 -> currentResult * RESULT_VALUE_NOT_CHANGED
                        4 -> currentResult * RESULT_VALUE_NOT_CHANGED
                        5 -> currentResult * TEN_IN_PLUS_THREE_POWER
                        6 -> currentResult * TEN_IN_PLUS_THREE_POWER
                        7 -> currentResult * TEN_IN_PLUS_THREE_POWER
                        8 -> currentResult * TEN_IN_MINUS_THREE_POWER
                        else -> ERROR_VALUE
                    }
                }
                ScreenType.PHARMACY_CRI -> {
                    result = when(dimension[3]) {
                        0 -> currentResult * TEN_IN_PLUS_SIX_POWER
                        1 -> currentResult * TEN_IN_PLUS_NINE_POWER
                        else -> ERROR_VALUE
                    }
                    result = when(dimension[4]) {
                        0 -> result * NUMBER_SECONDS_IN_HOUR
                        1 -> result * NUMBER_SECONDS_IN_HOUR
                        2 -> result * RESULT_VALUE_NOT_CHANGED
                        3 -> result * RESULT_VALUE_NOT_CHANGED
                        else -> ERROR_VALUE
                    }
                }
                else -> result = ERROR_VALUE
            }
        }
        OutputDataDimensionType.MASS -> {
            result = when(dimension[1]) {
                0 -> currentResult * TEN_IN_PLUS_NINE_POWER
                1 -> currentResult * TEN_IN_PLUS_SIX_POWER
                2 -> currentResult * TEN_IN_PLUS_THREE_POWER
                3 -> currentResult * RESULT_VALUE_NOT_CHANGED
                4 -> currentResult * RESULT_VALUE_NOT_CHANGED
                else -> ERROR_VALUE
            }
        }
        OutputDataDimensionType.TIME -> {
            result = when(screenType) {
                ScreenType.PHARMACY_CRI -> {
                    when (dimension[4]) {
                        0 -> currentResult * NUMBER_SECONDS_IN_HOUR
                        1 -> currentResult * NUMBER_SECONDS_IN_HOUR
                        2 -> currentResult * NUMBER_SECONDS_IN_MINUTE
                        3 -> currentResult * NUMBER_SECONDS_IN_MINUTE
                        else -> ERROR_VALUE
                    }
                }
                else -> currentResult
            }
        }
        OutputDataDimensionType.DROP_TIME -> {
            result = when(screenType) {
                ScreenType.PHARMACY_CRI -> {
                    when (dimension[4]) {
                        0 -> currentResult * NUMBER_SECONDS_IN_HOUR *
                                (TEN_IN_MINUS_SIX_POWER * NUMBER_SECONDS_IN_HOUR)
                        1 -> currentResult * NUMBER_SECONDS_IN_HOUR *
                                (TEN_IN_MINUS_NINE_POWER * NUMBER_SECONDS_IN_HOUR)
                        2 -> currentResult * NUMBER_SECONDS_IN_MINUTE *
                                (TEN_IN_MINUS_SIX_POWER * NUMBER_SECONDS_IN_MINUTE)
                        3 -> currentResult * NUMBER_SECONDS_IN_MINUTE *
                                (TEN_IN_MINUS_NINE_POWER * NUMBER_SECONDS_IN_MINUTE)
                        else -> ERROR_VALUE
                    }
                }
                else -> currentResult
            }
        }
        OutputDataDimensionType.RATE -> {
            result = when (dimension[4]) {
                0 -> currentResult * TEN_IN_PLUS_SIX_POWER / NUMBER_SECONDS_IN_HOUR
                1 -> currentResult * TEN_IN_PLUS_NINE_POWER / NUMBER_SECONDS_IN_HOUR
                2 -> currentResult * TEN_IN_PLUS_SIX_POWER / NUMBER_SECONDS_IN_MINUTE
                3 -> currentResult * TEN_IN_PLUS_NINE_POWER / NUMBER_SECONDS_IN_MINUTE
                else -> ERROR_VALUE
            }
        }
        OutputDataDimensionType.MASS_DOSE_PER_KG_PER_TIME -> {
            result = when(dimension[2]) {
                0 -> currentResult * TEN_IN_PLUS_THREE_POWER *
                        TEN_IN_PLUS_NINE_POWER / NUMBER_SECONDS_IN_MINUTE
                1 -> currentResult * TEN_IN_PLUS_THREE_POWER *
                        TEN_IN_PLUS_SIX_POWER / NUMBER_SECONDS_IN_MINUTE
                2 -> currentResult * TEN_IN_PLUS_THREE_POWER *
                        TEN_IN_PLUS_THREE_POWER / NUMBER_SECONDS_IN_MINUTE
                3 -> currentResult * TEN_IN_PLUS_THREE_POWER *
                        TEN_IN_PLUS_NINE_POWER / NUMBER_SECONDS_IN_HOUR
                4 -> currentResult * TEN_IN_PLUS_THREE_POWER *
                        TEN_IN_PLUS_SIX_POWER / NUMBER_SECONDS_IN_HOUR
                5 -> currentResult * TEN_IN_PLUS_THREE_POWER *
                        TEN_IN_PLUS_THREE_POWER / NUMBER_SECONDS_IN_HOUR
                else -> ERROR_VALUE
            }
        }
        OutputDataDimensionType.DROP -> {
            result = currentResult
        }
        else -> result = ERROR_VALUE
    }
    return result
}