package com.pet.animal.formula.dose.health.veterinary.cure.unientity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Formula

@Entity
data class UniFormulaEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val section:Int,
    val name:String,
    val formula: Formula
)
