package com.pet.animal.formula.dose.health.veterinary.cure.repository



import androidx.test.platform.app.InstrumentationRegistry
import com.pet.animal.formula.dose.health.veterinary.cure.repo.dao.FormulasDatabase

import org.junit.After

import org.junit.Assert.assertNotNull
import org.junit.Before

import org.junit.Test


import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.inject
import java.security.AccessController.getContext

//@RunWith(AndroidJUnit4::class)
class RepositoryKoinTest {

    private val mockModule = module {
        single { FormulasDatabase.getInstance(get()) }
        single { get<FormulasDatabase>().vetFormulaDao() }
    }

    private val formulasDatabase:FormulasDatabase by inject(FormulasDatabase::class.java)

    @Before
    fun before() {
        startKoin {
            modules(mockModule)
        }
    }

    @After
    fun after(){
        stopKoin()
    }

    @Test
    fun test(){
        assertNotNull(formulasDatabase)
    }
}