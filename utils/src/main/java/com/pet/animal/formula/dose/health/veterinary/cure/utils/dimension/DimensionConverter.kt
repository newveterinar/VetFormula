package com.pet.animal.formula.dose.health.veterinary.cure.utils.dimension

import com.pet.animal.formula.dose.health.veterinary.cure.utils.InputDataDimensionType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.OutputDataDimensionType

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
): Double {
    var result: Double = -1.0
    when(outputDataDimensionType) {
        OutputDataDimensionType.LENGTH -> result = currentResult * 1.0
        OutputDataDimensionType.MASS -> result = currentResult * 1.0
        OutputDataDimensionType.TIME -> result = currentResult * 1.0
        OutputDataDimensionType.SQUARE_LENGTH -> result = currentResult * 1.0
        else -> result = -1.0
    }
    return result
}