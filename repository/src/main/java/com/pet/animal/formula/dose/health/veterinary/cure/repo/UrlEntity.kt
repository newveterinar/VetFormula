package com.pet.animal.formula.dose.health.veterinary.cure.repo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "urls")
class UrlEntity(val name:String, val screenType:Int, val url:String) {
    @PrimaryKey(autoGenerate = true)
    var id:Long?=null
}