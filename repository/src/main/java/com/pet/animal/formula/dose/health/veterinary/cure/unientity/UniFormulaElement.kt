package com.pet.animal.formula.dose.health.veterinary.cure.unientity

import androidx.room.Entity

@Entity(primaryKeys = ["formula","addFirst","addSecond"])
data class UniFormulaElement(
    val formula:Int,
    val addFirst:Int,
    val addSecond:Int,
    val body:String //** тут будет хранится json
)
