package com.pet.animal.formula.dose.health.veterinary.cure.repo.dao

import com.pet.animal.formula.dose.health.veterinary.cure.repo.FormulaEntity
import com.pet.animal.formula.dose.health.veterinary.cure.repo.Repository

class RepositoryImpl(val dbDao:VetFormulaDao): Repository {
    override suspend fun getAllVetFormulas(animalType: Int): List<FormulaEntity> {
        return dbDao.getAllVetFormulas(animalType)
    }

    override suspend fun getAllVetFormulas(): List<FormulaEntity> {
        return dbDao.getAllVetFormulas()
    }

    override suspend fun getAllVetFormulas(animalType: Int, formulasTypes: Int): List<FormulaEntity>
    {
        return dbDao.getAllVetFormulas(animalType,formulasTypes)
    }

    override suspend fun insertFormula(formulaEntity: FormulaEntity) {
        dbDao.insertFormula(formulaEntity)
    }

    override suspend fun deleteFormula(formulaEntity: FormulaEntity) {
        dbDao.deleteFormula(formulaEntity)
    }

    override suspend fun deleteFormulaByID(formulaId: Int) {
        dbDao.deleteFormulaByID(formulaId)
    }
}