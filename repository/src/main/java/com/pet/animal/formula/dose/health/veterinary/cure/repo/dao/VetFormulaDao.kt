package com.pet.animal.formula.dose.health.veterinary.cure.repo.dao

import androidx.room.*
import com.pet.animal.formula.dose.health.veterinary.cure.repo.FormulaEntity

@Dao
interface VetFormulaDao {
    @Query("select * from defFormulas where animalType=:animalType")
    suspend fun getAllVetFormulas(animalType:Int):List<FormulaEntity>

    @Query("select * from defFormulas")
    suspend fun getAllVetFormulas():List<FormulaEntity>

    @Query("select * from defFormulas where animalType=:animalType and formulaType=:formulasTypes")
    suspend fun getAllVetFormulas(animalType:Int,formulasTypes:Int):List<FormulaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFormula(formulaEntity:FormulaEntity)

    @Delete
    suspend fun deleteFormula(formulaEntity: FormulaEntity)

    @Query("delete from defFormulas where id=:id")
    suspend fun deleteFormulaByID(id:Int)


}