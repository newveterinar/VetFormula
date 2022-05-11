package com.pet.animal.formula.dose.health.veterinary.cure.repo.dao

import com.pet.animal.formula.dose.health.veterinary.cure.repo.FormulaEntity
import com.pet.animal.formula.dose.health.veterinary.cure.repo.FormulaVariantEntity
import com.pet.animal.formula.dose.health.veterinary.cure.repo.Repository
import com.pet.animal.formula.dose.health.veterinary.cure.repo.UrlEntity

class RepositoryImpl(val dbDao:VetFormulaDao): Repository {

    override suspend fun getAllVetFormulas(): List<FormulaEntity> {
        return dbDao.getAllVetFormulas()
    }

    override suspend fun getAllVetFormulas(screenType: Int): List<FormulaEntity> {
        return dbDao.getAllVetFormulas(screenType)
    }

    override suspend fun getFormulaVariants(formulaId:Int): List<FormulaVariantEntity> {
        return dbDao.getFormulaVariants(formulaId)
    }

    override suspend fun getFormulaByScreen(
        screenType: Int,
        addFirst: Int=0,
        addSecond: Int=0
    ): List<FormulaEntity> {
        if (addFirst==0&&addSecond==0){
            return dbDao.getAllVetFormulas(screenType)
        }
        if (addSecond==0){

        }
        return dbDao.getAllVetFormulas(screenType)
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