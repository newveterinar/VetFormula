package com.pet.animal.formula.dose.health.veterinary.cure.repo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class NoteEntity
    (
    @PrimaryKey(autoGenerate = true)
    val id:Long?,
    val name:String,
    val note:String
){
    override fun toString(): String {
        return name
    }
}


