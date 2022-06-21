package com.pet.animal.formula.dose.health.veterinary.cure.repo.dao

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Formula

@TypeConverters
class ClassTypeConverter {

    @TypeConverter
    fun classToJson(formulaClass: Formula):String{
        return Gson().toJson(formulaClass).toString()
    }

    @TypeConverter
    fun jsonToClass(jsonText:String):Formula{
        return Gson().fromJson(jsonText,Formula::class.java)
    }
}