package com.pet.animal.formula.dose.health.veterinary.cure.unientity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UniSectionEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String
)
