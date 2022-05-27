package com.pet.animal.formula.dose.health.veterinary.cure.screens

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import java.util.*

fun Spinner.onItemSelected(action: (position: Int) -> Unit) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            action(position)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

    }
}

fun Spinner.getItemByValue(value: String): Int{
    val adapter = adapter
    val count = adapter.count
    for (itemPosition in 0 until count){
        if ((adapter.getItem(itemPosition) as String).lowercase() == value.lowercase()){
            return itemPosition
        }
    }
    return 0
}

fun Spinner.selectItemByValue(value: String, animate: Boolean = true){
    setSelection(getItemByValue(value), animate)
}