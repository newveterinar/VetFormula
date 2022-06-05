package com.pet.animal.formula.dose.health.veterinary.cure.utils.screens

import android.content.Context
import android.widget.TextView
import android.widget.Toast

fun TextView.showToastedHint(context: Context, hint: String){
    Toast.makeText(context, hint, Toast.LENGTH_SHORT).show()
}