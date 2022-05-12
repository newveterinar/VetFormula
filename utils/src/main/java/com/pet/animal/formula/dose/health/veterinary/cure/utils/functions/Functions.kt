package com.pet.animal.formula.dose.health.veterinary.cure.utils

import android.widget.EditText
import android.widget.Spinner
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.ValueField

// Перевод строки типа String в число типа Double
fun stringToDouble(text: String): Double {
    return if (text.isEmpty()) 0.0 else text.toDouble()
}

// Получение списка List<ValueField> из списков List<Double> и List<Int>
fun valueFieldListCreator(values: List<Double>, dimensions: List<Int>): List<ValueField> {
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