package com.pet.animal.formula.dose.health.veterinary.cure.repo

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "defFormulas")
class FormulaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name:String,
    val addFirst: Int?,
    val addSecond: Int?,
    val animalType:Int,
    val formulaType:Int,
    val formula:String //TODO тут надо сделать ссылку на правильный класс и сделать TypeConverters из класса в строку и обратно. Пока класс формулы отсутсвует, оставлю стринг
)



