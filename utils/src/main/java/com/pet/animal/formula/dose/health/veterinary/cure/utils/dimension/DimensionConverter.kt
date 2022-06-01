package com.pet.animal.formula.dose.health.veterinary.cure.utils.dimension

import android.widget.Toast
import com.pet.animal.formula.dose.health.veterinary.cure.utils.InputDataDimensionType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.OutputDataDimensionType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.resources.ResourcesProviderImpl
import org.koin.java.KoinJavaComponent

// Конвертрер типов исходных данных
fun inputDataDimensionConverter(
    inputDataDimensionType: InputDataDimensionType,
    index: Int,
    checkIndex: Int
): Double {
    var result: Double = -1.0
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
                0 -> (1E-9) / 60.0
                1 -> (1E-6) / 60.0
                2 -> (1E-3) / 60.0
                3 -> (1E-9) / 3600.0
                4 -> (1E-6) / 3600.0
                5 -> (1E-3) / 3600.0
                else -> -1.0
            }
        }
        InputDataDimensionType.VOLUME_DOSE_PER_KG_PER_TIME -> {
            result = when(index) {
                0 -> (1E-6) / 3600.0
                1 -> (1E-9) / 3600.0
                2 -> (1E-6) / 60.0
                3 -> (1E-9) / 60.0
                else -> -1.0
            }
        }
        InputDataDimensionType.CONCENTRATION -> {
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
    outputDataDimensionType: OutputDataDimensionType,
    currentResult: Double,
    dimension: List<Int>
): Double {
    /** Задание переменных */ //region
    // Получение доступа к ресурсам
    val resourcesProviderImpl: ResourcesProviderImpl = KoinJavaComponent.getKoin().get()
    var result: Double = -1.0
    //endregion

    when(outputDataDimensionType) {
        OutputDataDimensionType.LENGTH -> result = currentResult * 1.0 // Имеет только академический смысл
        OutputDataDimensionType.SQUARE_LENGTH -> result = currentResult * 1.0 // PharmacySurfaceResult
        OutputDataDimensionType.VOLUME -> {
            Toast.makeText(resourcesProviderImpl.context, "${dimension.size}", Toast.LENGTH_SHORT).show()
            var mass: Double = -1.0
            when(dimension[1]) {
                0 -> mass = 1E+9
                1 -> mass = 1E+6
                2 -> mass = 1E+3
                3 -> mass = 1.0
                4 -> mass = 1.0
                else -> mass = -1.0
            }
            result = when(dimension[2]) {
                0 -> result * mass * (1E-6) / (1E-9)
                1 -> result * mass * (1E-6) * (1E-6)
                2 -> result * mass * (1E-6) * (1E-3)
                3 -> result * mass * (1E-6) / 1.0
                4 -> result * mass * (1E-6) / 1.0
                5 -> result * mass * (1E-3) / (1E-9)
                6 -> result * mass * (1E-3) / (1E-6)
                7 -> result * mass * (1E-3) / (1E-3)
                8 -> result * mass * (1E+2) / (1E-6)
                else -> -1.0
            }
        }
        OutputDataDimensionType.MASS -> {
            result = when(dimension[1]) {
                0 -> currentResult * (1E-3) * (1E+9)
                1 -> currentResult * (1E-3) * (1E+6)
                2 -> currentResult * (1E-3) * (1E+3)
                3 -> currentResult * (1E-3) * 1
                4 -> currentResult * (1E-3) * 1
                else -> -1.0
            }
        }
        OutputDataDimensionType.TIME -> result = currentResult * 1.0          // TODO ДОРАБОТАТЬ
        OutputDataDimensionType.RATE -> result = currentResult * 1.0          // TODO ДОРАБОТАТЬ
        else -> result = -1.0
    }
    return result
}