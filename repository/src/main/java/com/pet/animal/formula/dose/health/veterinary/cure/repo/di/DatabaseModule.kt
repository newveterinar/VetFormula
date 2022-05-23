package com.pet.animal.formula.dose.health.veterinary.cure.repo.di

import com.pet.animal.formula.dose.health.veterinary.cure.repo.dao.FormulasDatabase
import org.koin.dsl.module


val DatabaseModule = module {
    single { FormulasDatabase.getInstance(get()) }
    single { get<FormulasDatabase>().vetFormulaDao() }
}
