package com.pet.animal.formula.dose.health.veterinary.cure.repo

interface Repository {
    suspend fun getAllVetFormulas(): List<FormulaEntity>
    suspend fun getAllVetFormulas(screenType: Int): List<FormulaEntity>
    suspend fun getFormulaVariants(formulaId:Int):List<FormulaVariantEntity>

    suspend fun getFormulaByScreen(screenType: Int,addFirst:Int,addSecond:Int): List<FormulaEntity>

    suspend fun insertFormula(formulaEntity: FormulaEntity)
    suspend fun deleteFormula(formulaEntity: FormulaEntity)
    suspend fun deleteFormulaByID(formulaId: Int)

    //URLS
    suspend fun insertUrl(urlEntity: UrlEntity):Long
    suspend fun getUrls(fType:Int):List<UrlEntity>
    suspend fun deleteUrl(urlEntity:UrlEntity)
}