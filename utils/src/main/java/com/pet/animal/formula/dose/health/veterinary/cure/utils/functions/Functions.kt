package com.pet.animal.formula.dose.health.veterinary.cure.utils.functions

import android.widget.EditText
import android.widget.Spinner
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.ValueField
import com.pet.animal.formula.dose.health.veterinary.cure.utils.*

// Перевод строки типа String в число типа Double
fun stringToDouble(text: String): Double {
    return if (text.isEmpty() || (text.indexOf(".") > -1 && (text.length == 1))) 0.0
           else text.toDouble()
}

// Проверка на то, что число типа Double > 0.0
fun String.checkToExistCorrectDouble(): Boolean {
    return stringToDouble(this) > 0.0
}

// Получение списка List<ValueField> из списков List<Double> и List<Int>
fun valueFieldListCreator(values: List<Double>, dimensions: List<Int>): List<ValueField> {
    if (dimensions.size > values.size){
        //TODO: Обработка ошибки
    }
    val resultList: MutableList<ValueField> = mutableListOf()
    values.forEachIndexed { index, value ->
            resultList.add(ValueField(value, dimensions[index]))
    }
    return resultList
}

// Получение списка MutableList<Int> из списка MutableList<Spinner>
fun MutableList<Spinner>.convertListSpinnerToListInt(): List<Int> {
    val resultList: MutableList<Int> = mutableListOf()
    this.forEach {
        resultList.add(it.selectedItemPosition)
    }
    return resultList
}

// Получение списка MutableList<Double> из списка MutableList<EditText>
fun MutableList<EditText>.convertListEditTextToListDouble(): List<Double> {
    val resultList: MutableList<Double> = mutableListOf()
    this.forEach {
        resultList.add(stringToDouble(it.text.toString()))
    }
    return resultList
}

// Перевод списка параметров addFirstSecond в название типизированной формулы TypedFormula
fun List<Int>.convertAddFirstSecondToTypedFormulaName(screenType: ScreenType): String {
    var resultTypedFormulaName: String = ""
    when (screenType) {
            // Типы формул для раздела FLUIDS
        ScreenType.FLUIDS_BASIC -> {}
        ScreenType.FLUIDS_KOMPREHENSIVE -> {}
        ScreenType.FLUIDS_K_INFUSION -> {}
            // Типы формул для раздела CONVERSTION
        ScreenType.CONVERSION_UNITS -> {}
            // Типы формул для раздела HEMATOLOGY
        ScreenType.HEMATOLOGY_BLOOD -> {}
        ScreenType.HEMATOLOGY_FLEBOTOMY -> {}
            // Типы формул для раздела PHARMACY
        ScreenType.PHARMACY_DOSES -> {}
        ScreenType.PHARMACY_CRI -> {}
        ScreenType.PHARMACY_SURFACE -> {
            if (this[ADDFIRST_INDEX] == PHARMACY_SURFACE_DOG_INDEX)
                resultTypedFormulaName = PHARMACY_SURFACE_DOG_BODYSURFACEAREA_NAME
            if (this[ADDFIRST_INDEX] == PHARMACY_SURFACE_CAT_INDEX)
                resultTypedFormulaName = PHARMACY_SURFACE_CAT_BODYSURFACEAREA_NAME
            if (this[ADDFIRST_INDEX] == PHARMACY_SURFACE_RABBIT_INDEX)
                resultTypedFormulaName = PHARMACY_SURFACE_RABBIT_BODYSURFACEAREA_NAME
            if (this[ADDFIRST_INDEX] == PHARMACY_SURFACE_POLECAT_INDEX)
                resultTypedFormulaName = PHARMACY_SURFACE_POLECAT_BODYSURFACEAREA_NAME
            if (this[ADDFIRST_INDEX] == PHARMACY_SURFACE_GUINEAPIG_INDEX)
                resultTypedFormulaName = PHARMACY_SURFACE_GUINEAPIG_BODYSURFACEAREA_NAME
            if (this[ADDFIRST_INDEX] == PHARMACY_SURFACE_HAMSTER_INDEX)
                resultTypedFormulaName = PHARMACY_SURFACE_HAMSTER_BODYSURFACEAREA_NAME
            if (this[ADDFIRST_INDEX] == PHARMACY_SURFACE_HORSEEXCEPTLOMUSTIN_INDEX)
                resultTypedFormulaName = PHARMACY_SURFACE_HORSEEXCEPTLOMUSTIN_BODYSURFACEAREA_NAME
            if (this[ADDFIRST_INDEX] == PHARMACY_SURFACE_HORSEONLYLOMUSTIN_INDEX)
                resultTypedFormulaName = PHARMACY_SURFACE_HORSEONLYLOMUSTIN_BODYSURFACEAREA_NAME
            if (this[ADDFIRST_INDEX] == PHARMACY_SURFACE_RAT_INDEX)
                resultTypedFormulaName = PHARMACY_SURFACE_RAT_BODYSURFACEAREA_NAME
            if (this[ADDFIRST_INDEX] == PHARMACY_SURFACE_MOUSE_INDEX)
                resultTypedFormulaName = PHARMACY_SURFACE_MOUSE_BODYSURFACEAREA_NAME
        }
            // Типы формул для раздела CALCULATOR
        ScreenType.CALCULATOR -> {}
    }
    return resultTypedFormulaName
}

