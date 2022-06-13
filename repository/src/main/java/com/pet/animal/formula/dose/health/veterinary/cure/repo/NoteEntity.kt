package com.pet.animal.formula.dose.health.veterinary.cure.repo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity
    (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val note:String
)
