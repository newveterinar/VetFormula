package com.pet.animal.formula.dose.health.veterinary.cure.repo

import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Formula
import com.pet.animal.formula.dose.health.veterinary.cure.unientity.UniFormulaEntity
import com.pet.animal.formula.dose.health.veterinary.cure.unientity.UniParamEntity
import com.pet.animal.formula.dose.health.veterinary.cure.unientity.UniSectionEntity
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType

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
    suspend fun getFormula(screenType: ScreenType, listsAddFirstSecond: List<Int>): Formula

    //UNI
    suspend fun insertSection(section:List<UniSectionEntity>)
    suspend fun getSections():List<UniSectionEntity>

    suspend fun saveUniFormulas(formulas:List<UniFormulaEntity>)
    suspend fun getUniFormulasBySection(sectionId:Int):List<UniFormulaEntity>

    suspend fun saveUniParams(params:List<UniParamEntity>)
    suspend fun getUniParamsByFormula(formulaId:Int):List<UniParamEntity>

    //NOTE_ENTITY
    suspend fun saveNote(noteEntity:NoteEntity):Long
    suspend fun loadNote(id:Long):List<NoteEntity>
    suspend fun deleteNote(id:Long)
    suspend fun getNotesList():List<NoteEntity>
}