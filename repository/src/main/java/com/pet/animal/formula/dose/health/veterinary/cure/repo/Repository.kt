package com.pet.animal.formula.dose.health.veterinary.cure.repo

interface Repository {
    suspend fun getAllVetFormulas(animalType: Int): List<FormulaEntity>
    suspend fun getAllVetFormulas(): List<FormulaEntity>
    suspend fun getAllVetFormulas(animalType: Int, formulasTypes: Int): List<FormulaEntity>

    suspend fun insertFormula(formulaEntity: FormulaEntity)
    suspend fun deleteFormula(formulaEntity: FormulaEntity)
    suspend fun deleteFormulaByID(formulaId: Int)
}