package com.pet.animal.formula.dose.health.veterinary.cure.vetformula.app

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router

class appVetCalc: Application() {
    /** Задание переменных */ //region
    // Cicerone
    private var cicerone: Cicerone<Router>? = null
    //endregion

    override fun onCreate() {
        super.onCreate()
        instance = this
        cicerone = Cicerone.create()
    }

    val navigatorHolder: NavigatorHolder
        get() = cicerone!!.getNavigatorHolder()
    val router: Router
        get() = cicerone!!.router

    companion object {
        var instance: appVetCalc? = null
    }
}