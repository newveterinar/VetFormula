package com.pet.animal.formula.dose.health.veterinary.cure.repo


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Formula

@Entity(tableName = "defFormulas")
class FormulaEntity(
    val screenType:Int,
    val elementCunt:Int,
    val addFirst:Int,
    val addSecond:Int,
    val formula: Formula
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Long?=null
}



