package com.pet.animal.formula.dose.health.veterinary.cure.unientity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UniParamEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val formula:Int,
    val position:Int,
    val name:String,
    val type:ParamType,
    val dimension:DimensionType,
    val listType:ListType
)