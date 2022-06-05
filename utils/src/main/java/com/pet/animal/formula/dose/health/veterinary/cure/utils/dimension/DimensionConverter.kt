package com.pet.animal.formula.dose.health.veterinary.cure.utils.dimension

import android.widget.Toast
import com.pet.animal.formula.dose.health.veterinary.cure.utils.InputDataDimensionType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.OutputDataDimensionType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
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
    // Получение доступа к ресурсам
    val resourcesProviderImpl: ResourcesProviderImpl = KoinJavaComponent.getKoin().get()
    //endregion
    when(inputDataDimensionType) {
        InputDataDimensionType.WEIGHT_ANIMAL -> {
            result = when(index) {
                0 -> 1.0
                1 -> 1E-3
                else -> -1.0
            }
        }
        InputDataDimensionType.MASS_DOSE_PER_KG -> {
            result = when(index) {
                0 -> 1E-9
                1 -> 1E-6
                2 -> 1E-3
                3 -> {
                    if (checkIndex != 3) -1.0
                    else 1.0
                }
                4 -> {
                    if (checkIndex != 4) -1.0
                    else 1.0
                }
                else -> -1.0
            }
        }
        InputDataDimensionType.MASS_DOSE_PER_KG_PER_TIME -> {
            result = when(index) {
                0 -> (1E-9) * 60.0
                1 -> (1E-6) * 60.0
                2 -> (1E-3) * 60.0
                3 -> (1E-9) * 3600.0
                4 -> (1E-6) * 3600.0
                5 -> (1E-3) * 3600.0
                else -> -1.0
            }
            Toast.makeText(resourcesProviderImpl.context, "Input: $result", Toast.LENGTH_SHORT).show()
        }
        InputDataDimensionType.VOLUME_DOSE_PER_KG_PER_TIME -> {
            result = when(index) {
                0 -> (1E-6) * 3600.0
                1 -> (1E-9) * 3600.0
                2 -> (1E-6) * 60.0
                3 -> (1E-9) * 60.0
                else -> -1.0
            }
        }
        InputDataDimensionType.CONCENTRATION -> { // Formulation
            result = when(index) {
                0 -> (1E-9) / (1E-6)
                1 -> (1E-6) / (1E-6)
                2 -> (1E-3) / (1E-6)
                3 -> {
                    if (checkIndex != 3) -1.0
                    else 1.0 / 1E-6
                }
                4 -> {
                    if (checkIndex != 4) -1.0
                    else 1.0 / 1E-6
                }
                5 -> (1E-9) / (1E-3)
                6 -> (1E-6) / (1E-3)
                7 -> (1E-3) / (1E-3)
                8 -> (1E-6) / (1E+2)
                else -> -1.0
            }
        }
        InputDataDimensionType.VOLUME -> {
            result = when(index) {
                0 -> (1E-6)
                1 -> (1E-9)
                else -> -1.0
            }
        }
        else -> result = -1.0
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
    var result: Double = -1.0
    // Получение доступа к ресурсам
    val resourcesProviderImpl: ResourcesProviderImpl = KoinJavaComponent.getKoin().get()
    //endregion

    when(outputDataDimensionType) {
        OutputDataDimensionType.LENGTH -> result = currentResult * 1.0 // Имеет только академический смысл
        OutputDataDimensionType.SQUARE_LENGTH -> result = currentResult * 1.0 // PharmacySurfaceResult
        OutputDataDimensionType.VOLUME -> {
            when(screenType) {
                ScreenType.PHARMACY_DOSES -> {
                    result = when(dimension[2]) {
                        0 -> currentResult * (1E+6)
                        1 -> currentResult * (1E+6)
                        2 -> currentResult * (1E+6)
                        3 -> currentResult * 1.0
                        4 -> currentResult * 1.0
                        5 -> currentResult * (1E+3)
                        6 -> currentResult * (1E+3)
                        7 -> currentResult * (1E+3)
                        8 -> currentResult * (1E-3)
                        else -> -1.0
                    }
                }
                ScreenType.PHARMACY_CRI -> {
                    result = when(dimension[3]) {
                        0 -> currentResult * (1E+6)
                        1 -> currentResult * (1E+9)
                        else -> -1.0
                    }
                    result = when(dimension[4]) {
                        0 -> result * 3600
                        1 -> result * 3600
                        2 -> result * 1.0
                        3 -> result * 1.0
                        else -> -1.0
                    }
                }
                else -> {}
            }
        }
        OutputDataDimensionType.MASS -> {
            result = when(dimension[1]) {
                0 -> currentResult * (1E+9)
                1 -> currentResult * (1E+6)
                2 -> currentResult * (1E+3)
                3 -> currentResult * 1.0
                4 -> currentResult * 1.0
                else -> -1.0
            }
        }
        OutputDataDimensionType.TIME -> {
            result = currentResult * 1.0
        }
        OutputDataDimensionType.RATE -> {
            result = when (dimension[4]) {
                0 -> currentResult * (1E+6) / 3600.0
                1 -> currentResult * (1E+9) / 3600.0
                2 -> currentResult * (1E+6) / 60.0
                3 -> currentResult * (1E+9) / 60.0
                else -> -1.0
            }
        }
        OutputDataDimensionType.MASS_DOSE_PER_KG_PER_TIME -> {
            result = when(dimension[2]) {
                0 -> currentResult * (1E+3) * (1E+9) / 60.0
                1 -> currentResult * (1E+3) * (1E+6) / 60.0
                2 -> currentResult * (1E+3) * (1E+3) / 60.0
                3 -> currentResult * (1E+3) * (1E+9) / 3600.0
                4 -> currentResult * (1E+3) * (1E+6) / 3600.0
                5 -> currentResult * (1E+3) * (1E+3) / 3600.0
                else -> -1.0
            }
            Toast.makeText(resourcesProviderImpl.context, "Output: $currentResult", Toast.LENGTH_SHORT).show()
        }
        OutputDataDimensionType.DROP -> {
            result = currentResult
        }
        else -> result = -1.0
    }
    return result
}