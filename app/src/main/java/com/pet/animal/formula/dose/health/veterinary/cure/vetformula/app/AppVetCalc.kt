package com.pet.animal.formula.dose.health.veterinary.cure.vetformula.app

import android.app.Application
import android.content.Context
import com.pet.animal.formula.dose.health.veterinary.cure.utils.language_utils.LocaleHelper
import com.pet.animal.formula.dose.health.veterinary.cure.vetformula.di.database
import com.pet.animal.formula.dose.health.veterinary.cure.vetformula.di.screens
import com.pet.animal.formula.dose.health.veterinary.cure.vetformula.di.utils
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppVetCalc: Application() {
    /** Задание переменных */ //region
    //endregion

    override fun onCreate() {
        super.onCreate()
        instance = this

        // Koin
        startKoin {
            androidContext(applicationContext)
            modules(listOf(database, utils, screens))
        }
    }

    companion object {
        var instance: AppVetCalc? = null
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(base))
    }
}