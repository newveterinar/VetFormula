package com.pet.animal.formula.dose.health.veterinary.cure.repo.di

import com.pet.animal.formula.dose.health.veterinary.cure.repo.Repository
import com.pet.animal.formula.dose.health.veterinary.cure.repo.dao.FormulasDatabase
import com.pet.animal.formula.dose.health.veterinary.cure.repo.dao.RepositoryImpl
import org.koin.dsl.module


val databaseModule = module {
    single { FormulasDatabase.getInstance(get()) }
    single { get<FormulasDatabase>().vetFormulaDao() }
    single<Repository> {RepositoryImpl(get())}
}
