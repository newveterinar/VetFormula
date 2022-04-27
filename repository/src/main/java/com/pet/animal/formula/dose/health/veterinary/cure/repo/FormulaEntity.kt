package com.pet.animal.formula.dose.health.veterinary.cure.repo

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.pet.animal.formula.dose.health.veterinary.cure.repo.dao.ClassTypeConverter
import java.io.Serializable

@Entity(tableName = "defFormulas")
class FormulaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name:String,
    val addFirst: Int?,
    val addSecond: Int?,
    val animalType:Int,
    val formulaType:Int,
    val formula:Serializable
)



