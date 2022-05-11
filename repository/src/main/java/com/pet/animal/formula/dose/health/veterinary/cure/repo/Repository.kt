package com.pet.animal.formula.dose.health.veterinary.cure.repo

import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Formula

interface Repository {
    suspend fun getAllVetFormulas(): List<FormulaEntity>

    suspend fun getFormulaByScreen(screenType: Int,addFirst:Int=0,addSecond:Int=0): List<FormulaEntity>

    suspend fun insertFormula(formula: Formula,screenType:Int,elementCount:Int,addFirst:Int,addSecond:Int):Long
    suspend fun insertFormulaEntity(formulaEntity: FormulaEntity):Long

    suspend fun deleteFormula(formulaEntity: FormulaEntity)
    suspend fun deleteFormulaByID(formulaId: Long)

    //URLS
    suspend fun insertUrl(urlEntity: UrlEntity):Long
    suspend fun getUrls(fType:Int):List<UrlEntity>
    suspend fun deleteUrl(urlEntity:UrlEntity)
}