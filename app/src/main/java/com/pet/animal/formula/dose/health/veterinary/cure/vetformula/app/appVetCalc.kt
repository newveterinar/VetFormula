package com.pet.animal.formula.dose.health.veterinary.cure.vetformula.app

import android.app.Application
import com.pet.animal.formula.dose.health.veterinary.cure.repo.di.databaseModule
import com.pet.animal.formula.dose.health.veterinary.cure.vetformula.di.database
import com.pet.animal.formula.dose.health.veterinary.cure.vetformula.di.screens
import com.pet.animal.formula.dose.health.veterinary.cure.vetformula.di.utils
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class appVetCalc: Application() {
    /** Задание переменных */ //region
    //endregion

    override fun onCreate() {
        super.onCreate()
        instance = this

        // Koin
        startKoin {
            androidContext(applicationContext)
            modules(listOf(database, utils, screens, databaseModule))
        }
    }

    companion object {
        var instance: appVetCalc? = null
    }
}