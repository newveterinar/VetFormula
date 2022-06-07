package com.pet.animal.formula.dose.health.veterinary.cure.utils.functions

import android.annotation.SuppressLint
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.text.style.SuperscriptSpan
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.ResultValueField
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.ValueField
import com.pet.animal.formula.dose.health.veterinary.cure.utils.*
import com.pet.animal.formula.dose.health.veterinary.cure.utils.dimension.inputDataDimensionConverter
import com.pet.animal.formula.dose.health.veterinary.cure.utils.resources.ResourcesProviderImpl
import org.koin.java.KoinJavaComponent

// Перевод строки типа String в число типа Double
fun stringToDouble(text: String): Double {
    return if (text.isEmpty() || (text.indexOf(".") > -1 && (text.length == 1))) 0.0
    else text.toDouble()
}

// Проверка на то, что число типа Double > 0.0
fun String.checkToExistCorrectDouble(): Boolean {
    return stringToDouble(this) > 0.0
}

// Получение списка MutableList<Int> из списка MutableList<Spinner>
fun MutableList<Spinner>.convertListSpinnerToListInt(): List<Int> {
    val resultList: MutableList<Int> = mutableListOf()
    this.forEach {
        resultList.add(it.selectedItemPosition)
    }
    return resultList
}

// Получение списка MutableList<String> из списка MutableList<EditText>
fun MutableList<EditText>.convertListEditTextToListString(): List<String> {
    val resultList: MutableList<String> = mutableListOf()
    this.forEach {
        if (it.text.toString().isEmpty()) resultList.add("")
        else resultList.add(if (it.text.toString()[0] == '.') "0${it.text}" else it.text.toString())
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

// Получение списка MutableList<Double> из списка MutableList<EditText>
fun MutableList<EditText>.convertListEditTextToListDouble(
    listsDimensions: MutableList<Spinner>,
    checkDimension: Spinner?
): List<Double> {
    /** Задание переменных */ //region
    // Получение доступа к ресурсам
    val resourcesProviderImpl: ResourcesProviderImpl = KoinJavaComponent.getKoin().get()
    //endregion

    return if (this.size != listsDimensions.size)
        throw Exception(resourcesProviderImpl.context.getString(
            R.string.error_variable_size_not_equal_dimension_size))
    else {
        val resultList: MutableList<Double> = mutableListOf()
        this.forEachIndexed { index, it ->
            resultList.add(stringToDouble(it.text.toString()) *
                    inputDataDimensionConverter(listsDimensions[index].tag.toString().
                    convertStringToInputDataDimensionType(),
                        listsDimensions[index].selectedItemPosition,
                        checkDimension?.selectedItemPosition ?: -1
                    ))
        }
        resultList
    }
}

// Получение из списка MutableList<ValueField> списка List<Int> со значениями dimension
fun MutableList<ValueField>.convertMutableListValueFieldToListIntDimension(): List<Int> {
    val result: MutableList<Int> = mutableListOf()
    this.forEach {
        result.add(it.dimension)
    }
    return result
}

// Получение значения InputDataDimensionType из переменной типа String
fun String.convertStringToInputDataDimensionType(): InputDataDimensionType {
    return when {
        this == InputDataDimensionType.WEIGHT_ANIMAL.toString() -> {
            InputDataDimensionType.WEIGHT_ANIMAL
        }
        this == InputDataDimensionType.MASS_DOSE_PER_KG.toString() -> {
            InputDataDimensionType.MASS_DOSE_PER_KG
        }
        this == InputDataDimensionType.MASS_DOSE_PER_KG_PER_TIME.toString() -> {
            InputDataDimensionType.MASS_DOSE_PER_KG_PER_TIME
        }
        this == InputDataDimensionType.VOLUME_DOSE_PER_KG_PER_TIME.toString() -> {
            InputDataDimensionType.VOLUME_DOSE_PER_KG_PER_TIME
        }
        this == InputDataDimensionType.CONCENTRATION.toString() -> {
            InputDataDimensionType.CONCENTRATION
        }
        this == InputDataDimensionType.VOLUME.toString() -> {
            InputDataDimensionType.VOLUME
        }
        else -> InputDataDimensionType.ERROR_TYPE
    }
}

// Перевод списка параметров addFirstSecond в название типизированной формулы TypedFormula
fun List<Int>.convertAddFirstSecondToTypedFormulaName(screenType: ScreenType): String {
    /** Задание переменных */ //region
    var resultTypedFormulaName: String = ""
    //endregion

    when (screenType) {
        // Типы формул для раздела FLUIDS
        ScreenType.FLUIDS_BASIC -> {
        }
        ScreenType.FLUIDS_KOMPREHENSIVE -> {
        }
        ScreenType.FLUIDS_K_INFUSION -> {
        }
        // Типы формул для раздела CONVERSION
        ScreenType.CONVERSION_UNITS -> {
        }
        // Типы формул для раздела HEMATOLOGY
        ScreenType.HEMATOLOGY_BLOOD -> {
        }
        ScreenType.HEMATOLOGY_FLEBOTOMY -> {
        }
        // Типы формул для раздела PHARMACY
        ScreenType.PHARMACY_DOSES -> {
            if ((this[ADDFIRST_INDEX] == PHARMACY_DOSES_INDEX) &&
                (this[ADDSECOND_INDEX] == 0))
                resultTypedFormulaName = PHARMACY_DOSES_NAME
        }
        ScreenType.PHARMACY_CRI -> {
            if ((this[ADDFIRST_INDEX] == PHARMACY_CRI_NO_GIVING_SET_INDEX) &&
                (this[ADDSECOND_INDEX] == 0)) {
                resultTypedFormulaName = PHARMACY_CRI_NO_GIVING_SET_NAME
            }
            if ((this[ADDFIRST_INDEX] == PHARMACY_CRI_20_DROPS_PER_ML_INDEX) &&
                (this[ADDSECOND_INDEX] == 0)) {
                resultTypedFormulaName = PHARMACY_CRI_20_DROPS_PER_ML_NAME
            }
            if ((this[ADDFIRST_INDEX] == PHARMACY_CRI_60_DROPS_PER_ML_INDEX) &&
                (this[ADDSECOND_INDEX] == 0)) {
                resultTypedFormulaName = PHARMACY_CRI_60_DROPS_PER_ML_NAME
            }
        }
        ScreenType.PHARMACY_SURFACE -> {
            if ((this[ADDFIRST_INDEX] == PHARMACY_SURFACE_DOG_INDEX) &&
                (this[ADDSECOND_INDEX] == 0))
                resultTypedFormulaName = PHARMACY_SURFACE_DOG_BODYSURFACEAREA_NAME
            if ((this[ADDFIRST_INDEX] == PHARMACY_SURFACE_CAT_INDEX) &&
                (this[ADDSECOND_INDEX] == 0))
                resultTypedFormulaName = PHARMACY_SURFACE_CAT_BODYSURFACEAREA_NAME
            if ((this[ADDFIRST_INDEX] == PHARMACY_SURFACE_RABBIT_INDEX) &&
                (this[ADDSECOND_INDEX] == 0))
                resultTypedFormulaName = PHARMACY_SURFACE_RABBIT_BODYSURFACEAREA_NAME
            if ((this[ADDFIRST_INDEX] == PHARMACY_SURFACE_POLECAT_INDEX) &&
                (this[ADDSECOND_INDEX] == 0))
                resultTypedFormulaName = PHARMACY_SURFACE_POLECAT_BODYSURFACEAREA_NAME
            if ((this[ADDFIRST_INDEX] == PHARMACY_SURFACE_GUINEAPIG_INDEX) &&
                (this[ADDSECOND_INDEX] == 0))
                resultTypedFormulaName = PHARMACY_SURFACE_GUINEAPIG_BODYSURFACEAREA_NAME
            if ((this[ADDFIRST_INDEX] == PHARMACY_SURFACE_HAMSTER_INDEX) &&
                (this[ADDSECOND_INDEX] == 0))
                resultTypedFormulaName = PHARMACY_SURFACE_HAMSTER_BODYSURFACEAREA_NAME
            if ((this[ADDFIRST_INDEX] == PHARMACY_SURFACE_HORSEEXCEPTLOMUSTIN_INDEX) &&
                (this[ADDSECOND_INDEX] == 0))
                resultTypedFormulaName = PHARMACY_SURFACE_HORSEEXCEPTLOMUSTIN_BODYSURFACEAREA_NAME
            if ((this[ADDFIRST_INDEX] == PHARMACY_SURFACE_HORSEONLYLOMUSTIN_INDEX)
                && (this[ADDSECOND_INDEX] == 0))
                resultTypedFormulaName = PHARMACY_SURFACE_HORSEONLYLOMUSTIN_BODYSURFACEAREA_NAME
            if ((this[ADDFIRST_INDEX] == PHARMACY_SURFACE_RAT_INDEX) &&
                (this[ADDSECOND_INDEX] == 0))
                resultTypedFormulaName = PHARMACY_SURFACE_RAT_BODYSURFACEAREA_NAME
            if ((this[ADDFIRST_INDEX] == PHARMACY_SURFACE_MOUSE_INDEX) &&
                (this[ADDSECOND_INDEX] == 0))
                resultTypedFormulaName = PHARMACY_SURFACE_MOUSE_BODYSURFACEAREA_NAME
        }
        // Типы формул для раздела CALCULATOR
        ScreenType.CALCULATOR -> {
        }
    }
    return resultTypedFormulaName
}

@SuppressLint("SetTextI18n")
fun TextView.createStringResult(
        resultValueField: MutableList<ResultValueField>,
        indexData: Int,
        valueFields: List<ValueField>
    ) {
    /** Задание переменных */ //region
    // Получение доступа к ресурсам
    val resourcesProviderImpl: ResourcesProviderImpl = KoinJavaComponent.getKoin().get()
    //endregion

    var initialString: String = if (indexData <= resultValueField.size - 1)
        "${resultValueField[indexData].value}" else ""
    var result: SpannableString = SpannableString(initialString)
    // Изменение формата размерности текста
    if (initialString.isNotEmpty()) {
        when (this.tag) {
            OutputDataDimensionType.LENGTH.toString() -> {
                result = SpannableString(initialString)
            }
            OutputDataDimensionType.SQUARE_LENGTH.toString() -> {
                initialString += " ${resourcesProviderImpl.context.resources.getString(
                    R.string.output_data_dimension_square_length)}"
                result = SpannableString(initialString)
                result.setSpan(
                    SuperscriptSpan(),
                    initialString.length - 1,
                    initialString.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                result.setSpan(
                    RelativeSizeSpan(SQUARE_TEXT_RELATIVE_SIZE),
                    initialString.length - 1,
                    initialString.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
            OutputDataDimensionType.VOLUME.toString() -> {
                val addString: String = resourcesProviderImpl.context.resources.getStringArray(
                    R.array.input_data_dimension_concentration_list)[
                        valueFields[2].dimension]
                if (valueFields[2].dimension !=
                    resourcesProviderImpl.context.resources.getStringArray(
                        R.array.input_data_dimension_concentration_list).size - 1)
                    initialString +=
                        " ${addString.substring(addString.lastIndexOf('/') + 1)}"
                else initialString += " ${resourcesProviderImpl.context.resources.getStringArray(
                    R.array.input_data_dimension_volume_list)[0]}"
                result = SpannableString(initialString)
            }
            OutputDataDimensionType.MASS.toString() -> {
                val addString: String = resourcesProviderImpl.context.resources.getStringArray(
                    R.array.input_data_dimension_mass_dose_per_kg_list)[
                        valueFields[1].dimension]
                initialString += " $addString"
                result = SpannableString(initialString)
            }
            OutputDataDimensionType.TIME.toString() -> {
                result = SpannableString(initialString)
            }
            OutputDataDimensionType.DROP_TIME_IN_SEC.toString() -> {
                result = SpannableString(initialString)
            }
            OutputDataDimensionType.DROP_TIME_IN_TEN_SEC.toString() -> {
                result = SpannableString(initialString)
            }
            OutputDataDimensionType.DROP_TIME_IN_MIN.toString() -> {
                result = SpannableString(initialString)
            }
            OutputDataDimensionType.RATE.toString() -> {
                result = SpannableString(initialString)
            }
            OutputDataDimensionType.MASS_DOSE_PER_KG_PER_TIME.toString() -> {
                result = SpannableString(initialString)
            }
            OutputDataDimensionType.DROP.toString() -> {
                result = SpannableString(initialString)
            }
            OutputDataDimensionType.ERROR_TYPE.toString() -> {
                result = SpannableString(initialString)
            }
            else -> {
                initialString = ""
                result = SpannableString(initialString)
            }
        }
    }
    this.text = result
}
