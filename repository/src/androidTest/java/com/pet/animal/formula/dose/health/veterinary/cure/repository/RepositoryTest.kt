package com.pet.animal.formula.dose.health.veterinary.cure.repository

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Element
import com.pet.animal.formula.dose.health.veterinary.cure.model.formula.Formula
import com.pet.animal.formula.dose.health.veterinary.cure.repo.Repository
import com.pet.animal.formula.dose.health.veterinary.cure.repo.UrlEntity
import com.pet.animal.formula.dose.health.veterinary.cure.repo.dao.FormulasDatabase
import com.pet.animal.formula.dose.health.veterinary.cure.repo.dao.RepositoryImpl
import com.pet.animal.formula.dose.health.veterinary.cure.repo.dao.VetFormulaDao
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RepositoryTest {
    private lateinit var db: FormulasDatabase
    private lateinit var dao: VetFormulaDao
    private lateinit var repo: Repository



    @Before
    fun createDB(){
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().targetContext,FormulasDatabase::class.java).build()
        dao = db.vetFormulaDao()
        repo = RepositoryImpl(dao)
    }

    @After
    fun closeDB(){
        db.close()
    }

    @Test
    fun testCreateDb() {
        assertNotNull(db)
    }

    @Test
    fun testCreateDao(){
        assertNotNull(dao)
    }

    @Test
    fun testCreateRepository(){
        assertNotNull(repo)
    }

    @Test
    fun testAddRecordInUrl(){
        val tempUrl = "https://google.com"
        val tempUrlEntity = UrlEntity("test",1,tempUrl)
        runBlocking{
            val newRec =  repo.insertUrl(tempUrlEntity)
            assertNotNull(newRec)
        }
    }

    @Test
    fun testAddRecordInUrlAndRead(){
        val tempUrl = "https://google.com"
        val tempUrlEntity = UrlEntity("test",1,tempUrl)
        runBlocking{
            repo.insertUrl(tempUrlEntity)
            val list = repo.getUrls(1)
            assertEquals(list.size,1)
            assertEquals(list[0].url,tempUrl)
        }
    }

    @Test
    fun testAddRecordInUrlAndNoCorrectRead(){
        val tempUrl = "https://google.com"
        val tempUrlEntity = UrlEntity("test",1,tempUrl)
        runBlocking{
            repo.insertUrl(tempUrlEntity)  //добавили запись с типом 1
            val list = repo.getUrls(2) //читаем записи с типом 2
            assertEquals(list.size,0) //проверям что ничего не вернулось
        }
    }

    @Test
    fun testAddTwoRecordInUrl(){
        val tempUrl1 = "https://google.com"
        val tempUrl2 = "https://yandex.com"
        val tempUrlEntity1 = UrlEntity("test",1,tempUrl1)
        val tempUrlEntity2 = UrlEntity("test",1,tempUrl2)
        runBlocking{
            repo.insertUrl(tempUrlEntity1)
            repo.insertUrl(tempUrlEntity2)

            val list = repo.getUrls(1)
            assertEquals(list.size,2)
            assertEquals(list[0].url,tempUrl1)
            assertEquals(list[1].url,tempUrl2)
        }
    }

    @Test
    fun testOverloadedFun(){
        runBlocking {
            repo.getFormulaByScreen(0)
            repo.getFormulaByScreen(0,1)
            repo.getFormulaByScreen(0,1,2)
        }
    }

    @Test
    fun createFormula(){
        val formula = createTestFormula()
        assertNotNull(formula)
    }

    private fun createTestFormula(): Formula {
        val ml = mutableListOf<Element>()
        return Formula()
    }

    private fun createTestFormula2(): Formula {
        val ml = mutableListOf<Element>()
        return Formula()
    }

    @Test
    fun addFormulaToDB(){
        val formula = createTestFormula()
        runBlocking {
            repo.insertFormula(formula,1,1,2,3)
        }
    }

    @Test
    fun readFormulaFromDB(){
        val formula = createTestFormula()
        val formula2 = createTestFormula2()
        runBlocking {
            repo.insertFormula(formula,1,1,2,3)
            repo.insertFormula(formula2,1,1,3,3)

            var list = repo.getFormulaByScreen(1)
            assertEquals(list.size,2)

            list = repo.getFormulaByScreen(1,2)
            assertEquals(list.size,1)
        }
    }

}