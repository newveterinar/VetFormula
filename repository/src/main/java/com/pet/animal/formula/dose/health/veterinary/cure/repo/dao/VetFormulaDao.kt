package com.pet.animal.formula.dose.health.veterinary.cure.repo.dao

import androidx.room.*
import com.pet.animal.formula.dose.health.veterinary.cure.repo.FormulaEntity
import com.pet.animal.formula.dose.health.veterinary.cure.repo.UrlEntity
import com.pet.animal.formula.dose.health.veterinary.cure.unientity.UniFormulaEntity
import com.pet.animal.formula.dose.health.veterinary.cure.unientity.UniParamEntity
import com.pet.animal.formula.dose.health.veterinary.cure.unientity.UniSectionEntity

@Dao
interface VetFormulaDao {

    @Query("select * from defFormulas")
    suspend fun getAllVetFormulas():List<FormulaEntity>

    @Query("select * from defFormulas where screenType=:screenType")
    suspend fun getFormulaByScreen(screenType:Int):List<FormulaEntity>

    // НЕ ИМЕЕТ СМЫСЛА
//    @Query("select * from defFormulas where screenType=:screenType and addFirst =:addFirst ")
//    suspend fun getFormulaByScreen(screenType:Int,addFirst:Int):List<FormulaEntity>

    @Query("select * from defFormulas where screenType=:screenType and addFirst =:addFirst and addSecond=:addSecond")
    suspend fun getFormulaByScreen(screenType:Int,addFirst:Int,addSecond:Int):List<FormulaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFormula(formulaEntity:FormulaEntity):Long

    @Delete
    suspend fun deleteFormula(formulaEntity: FormulaEntity)

    @Query("delete from defFormulas where id=:id")
    suspend fun deleteFormulaByID(id:Long)

    //URLS

    @Query("select * from urls where screenType=:screenType")
    suspend fun getUrlsByType(screenType:Int):List<UrlEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUrl(urlEntity:UrlEntity):Long

    @Delete
    suspend fun deleteUrl(url:UrlEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSection(sections: List<UniSectionEntity>)


    @Query("select * from UniSectionEntity")
    fun getSections(): List<UniSectionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUniFormulas(formulas: List<UniFormulaEntity>)

    @Query("select * from UniFormulaEntity where section=:sectionId")
    fun getUniFormulaByScreen(sectionId: Int): List<UniFormulaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUniParams(params: List<UniParamEntity>)

    @Query("select * from UniParamEntity where formula = :formulaId")
    fun getUniParamsByFormula(formulaId: Int): List<UniParamEntity>


}