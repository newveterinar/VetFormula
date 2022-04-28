package com.pet.animal.formula.dose.health.veterinary.cure.repo.dao

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import java.io.Serializable

@TypeConverters
class ClassTypeConverter {

    @TypeConverter
    fun classToJson(formulaClass: Serializable):String{
        return Gson().toJson(formulaClass).toString()
    }

    @TypeConverter
    fun jsonToClass(jsonText:String):Serializable{
        return Gson().fromJson(jsonText,Serializable::class.java)
    }
}