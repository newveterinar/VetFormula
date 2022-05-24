package com.pet.animal.formula.dose.health.veterinary.cure.repository

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.pet.animal.formula.dose.health.veterinary.cure.fakerepo.FakeBackend
import com.pet.animal.formula.dose.health.veterinary.cure.repo.Repository
import com.pet.animal.formula.dose.health.veterinary.cure.repo.dao.FormulasDatabase
import com.pet.animal.formula.dose.health.veterinary.cure.repo.dao.RepositoryImpl
import com.pet.animal.formula.dose.health.veterinary.cure.repo.dao.VetFormulaDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UniDBTest {
    private lateinit var db: FormulasDatabase
    private lateinit var dao: VetFormulaDao
    private lateinit var repo: Repository



    @Before
    fun createDB(){
        db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            FormulasDatabase::class.java).build()
        dao = db.vetFormulaDao()
        repo = RepositoryImpl(dao)
    }

    @After
    fun closeDB(){
        db.close()
    }

    @Test
    fun testCreateDb() {
        Assert.assertNotNull(db)
    }

    @Test
    fun testCreateDao(){
        Assert.assertNotNull(dao)
    }

    @Test
    fun testCreateRepository(){
        Assert.assertNotNull(repo)
    }

    fun saveSection(){
        val sections = FakeBackend.getSection()
        runBlocking {
            repo.insertSection(sections)
        }
    }

    @Test
    fun saveTestSection(){
        runBlocking {
            saveSection()
            val ls = repo.getSections()
            assertEquals(2,ls.size)
        }
    }

    fun seveFormulas(){
        runBlocking {
            val sections = repo.getSections()
            for (section in sections){
                val formulas = FakeBackend.getFormulasBySection(section.id)
                repo.saveUniFormulas(formulas)
            }
        }
    }

    fun saveParams(formulaId:Int){
        runBlocking {
            val params = FakeBackend.getParamsByFormula(formulaId)
            repo.saveUniParams(params)
        }
    }

    fun seveFormulaswParams(){
        runBlocking {
            val sections = repo.getSections()
            for (section in sections){
                val formulas = FakeBackend.getFormulasBySection(section.id)
                repo.saveUniFormulas(formulas)

            }
        }
    }


    @Test
    fun saveTestFormulas(){
        saveSection()
        seveFormulas()
        runBlocking {
            val f1 = repo.getUniFormulasBySection(1)
            val f2 = repo.getUniFormulasBySection(2)

            assertEquals(2,f1.size)
            assertEquals(1,f2.size)
        }
    }
}