package com.pet.animal.formula.dose.health.veterinary.cure.repo.dao

import androidx.room.*
import com.pet.animal.formula.dose.health.veterinary.cure.repo.FormulaEntity
import com.pet.animal.formula.dose.health.veterinary.cure.repo.FormulaVariantEntity
import com.pet.animal.formula.dose.health.veterinary.cure.repo.UrlEntity

@Dao
interface VetFormulaDao {

    @Query("select * from defFormulas")
    suspend fun getAllVetFormulas():List<FormulaEntity>

    @Query("select * from defFormulas where screenType=:screenType")
    suspend fun getAllVetFormulas(screenType:Int):List<FormulaEntity>

    @Query("select * from defFormulaVariants where formulaId=:formulaId")
    suspend fun getFormulaVariants(formulaId:Int):List<FormulaVariantEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFormula(formulaEntity:FormulaEntity):Long

    @Delete
    suspend fun deleteFormula(formulaEntity: FormulaEntity)

    @Query("delete from defFormulas where id=:id")
    suspend fun deleteFormulaByID(id:Int)

    //URLS

    @Query("select * from urls where screenType=:screenType")
    suspend fun getUrlsByType(screenType:Int):List<UrlEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUrl(urlEntity:UrlEntity):Long

    @Delete
    suspend fun deleteUrl(url:UrlEntity)


}