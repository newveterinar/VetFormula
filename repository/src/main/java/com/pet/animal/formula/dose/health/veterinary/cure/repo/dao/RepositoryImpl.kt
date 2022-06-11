package com.pet.animal.formula.dose.health.veterinary.cure.repo.dao

import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Formula
import com.pet.animal.formula.dose.health.veterinary.cure.repo.FormulaEntity
import com.pet.animal.formula.dose.health.veterinary.cure.repo.Repository
import com.pet.animal.formula.dose.health.veterinary.cure.repo.UrlEntity
import com.pet.animal.formula.dose.health.veterinary.cure.unientity.UniFormulaEntity
import com.pet.animal.formula.dose.health.veterinary.cure.unientity.UniParamEntity
import com.pet.animal.formula.dose.health.veterinary.cure.unientity.UniSectionEntity
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.resources.ResourcesProviderImpl
import org.koin.java.KoinJavaComponent

class RepositoryImpl(private val dbDao:VetFormulaDao): Repository {
    /** Исходные данные */ //region
    // Получение доступа к ресурсам
    private val resourcesProviderImpl: ResourcesProviderImpl = KoinJavaComponent.getKoin().get()
    //endregion

    override suspend fun getAllVetFormulas(): List<FormulaEntity> {
        return dbDao.getAllVetFormulas()
    }

    override suspend fun getFormulaByScreen(
        screenType: Int,
        addFirst: Int,
        addSecond: Int
    ): List<FormulaEntity> {
        // Почему выявляется критерий равенства или не равенства нулю,
        // ведь 0 это первое значение индекса в addFirst и addSecond?
        if (addFirst == 0  &&  addSecond != 0) {
            throw Exception(resourcesProviderImpl.context.resources.getString(
                com.pet.animal.formula.dose.health.veterinary.cure.utils.
                R.string.error_not_correct_argument))
        }
        if (addFirst == 0  &&  addSecond == 0) {
            return dbDao.getFormulaByScreen(screenType)
        }
        return dbDao.getFormulaByScreen(screenType, addFirst, addSecond)
    }

    override suspend fun insertFormula(formula: Formula,screenType:Int,elementCount:Int,addFirst:Int,addSecond:Int):Long {
        val formulaEntity = FormulaEntity(screenType,elementCount,addFirst,addSecond,formula)
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

    override suspend fun getFormula(
        screenType: ScreenType,
        listsAddFirstSecond: List<Int>
    ): Formula {
        val listEntity = getFormulaByScreen(
            screenType.ordinal, if (listsAddFirstSecond.isNotEmpty()) listsAddFirstSecond[0] else 0,
            if (listsAddFirstSecond.size > 1) listsAddFirstSecond[1] else 0)
        if (listEntity.isNotEmpty()) {
            return listEntity[0].formula
        }
        // Возвращаем пустую формулу, а информацию о том,
        // что произошла ошибка отобразим в текущем окне. У пустого класса Formula()
        // количество типизированных формул равно нулю, по их количеству можно отловить такую ошибку
        return Formula()
    }

    override suspend fun insertSection(sections: List<UniSectionEntity>) {

        dbDao.insertSection(sections)
    }

    override suspend fun getSections(): List<UniSectionEntity> {
        return dbDao.getSections()
    }

    override suspend fun saveUniFormulas(formulas: List<UniFormulaEntity>) {
        dbDao.saveUniFormulas(formulas)
    }

    override suspend fun getUniFormulasBySection(sectionId: Int): List<UniFormulaEntity> {
        return dbDao.getUniFormulaByScreen(sectionId)
    }

    override suspend fun saveUniParams(params: List<UniParamEntity>) {
        dbDao.saveUniParams(params)
    }

    override suspend fun getUniParamsByFormula(formulaId: Int): List<UniParamEntity> {
        return dbDao.getUniParamsByFormula(formulaId)
    }
}