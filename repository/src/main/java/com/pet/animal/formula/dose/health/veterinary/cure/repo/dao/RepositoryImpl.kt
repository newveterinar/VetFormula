package com.pet.animal.formula.dose.health.veterinary.cure.repo.dao

import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Formula
import com.pet.animal.formula.dose.health.veterinary.cure.repo.FormulaEntity
import com.pet.animal.formula.dose.health.veterinary.cure.repo.Repository
import com.pet.animal.formula.dose.health.veterinary.cure.repo.UrlEntity

class RepositoryImpl(private val dbDao: VetFormulaDao) : Repository {

    override suspend fun getAllVetFormulas(): List<FormulaEntity> {
        return dbDao.getAllVetFormulas()
    }

    override suspend fun getFormulaByScreen(
        screenType: Int,
        addFirst: Int,
        addSecond: Int,
    ): List<FormulaEntity> {
        if (addFirst == 0 && addSecond != 0) {
            throw Exception("not correct arguments")
        }
        if (addFirst == 0 && addSecond == 0) {
            return dbDao.getFormulaByScreen(screenType)
        }
        if (addSecond == 0) {
            return dbDao.getFormulaByScreen(screenType, addFirst)
        }
        return dbDao.getFormulaByScreen(screenType, addFirst, addSecond)
    }


    override suspend fun insertFormula(
        formula: Formula,
        screenType: Int,
        elementCount: Int,
        addFirst: Int,
        addSecond: Int,
    ): Long {
        val formulaEntity = FormulaEntity(screenType, elementCount, addFirst, addSecond, formula)
        return insertFormulaEntity(formulaEntity)
    }

    override suspend fun insertFormulaEntity(formulaEntity: FormulaEntity): Long {
        return dbDao.insertFormula(formulaEntity)
    }

    override suspend fun deleteFormula(formulaEntity: FormulaEntity) {
        dbDao.deleteFormula(formulaEntity)
    }

    override suspend fun deleteFormulaByID(formulaId: Long) {
        dbDao.deleteFormulaByID(formulaId)
    }

    override suspend fun insertUrl(urlEntity: UrlEntity): Long {
        return dbDao.insertUrl(urlEntity)
    }

    override suspend fun getUrls(fType: Int): List<UrlEntity> {
        return dbDao.getUrlsByType(fType)
    }

    override suspend fun deleteUrl(urlEntity: UrlEntity) {
        dbDao.deleteUrl(urlEntity)
    }
}