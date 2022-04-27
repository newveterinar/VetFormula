package com.pet.animal.formula.dose.health.veterinary.cure.repo.dao

import androidx.room.Dao
import androidx.room.Query
import com.pet.animal.formula.dose.health.veterinary.cure.repo.FormulaEntity

@Dao
interface VetFormulaDao {
    @Query("select * from defFormulas where animalType=:animalType")
    suspend fun getAllVetFormulas(animalType:Int):List<FormulaEntity>

    @Query("select * from defFormulas")
    suspend fun getAllVetFormulas():List<FormulaEntity>

    @Query("select * from defFormulas where animalType=:animalType and formulaType=:formulasTypes")
    suspend fun getAllVetFormulas(animalType:Int,formulasTypes:Int):List<FormulaEntity>


}