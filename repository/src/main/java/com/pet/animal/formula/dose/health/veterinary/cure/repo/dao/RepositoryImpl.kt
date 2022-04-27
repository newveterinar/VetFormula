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
        TODO("Not yet implemented")
    }

    override suspend fun deleteFormula(formulaEntity: FormulaEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFormulaByID(formulaId: Int) {
        TODO("Not yet implemented")
    }
}