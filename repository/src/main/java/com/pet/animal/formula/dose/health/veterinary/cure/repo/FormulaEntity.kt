package com.pet.animal.formula.dose.health.veterinary.cure.repo


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "defFormulas")
class FormulaEntity(
    val name:String,
    val screenType:Int,
    val elementCunt:Int
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Long?=null
}



