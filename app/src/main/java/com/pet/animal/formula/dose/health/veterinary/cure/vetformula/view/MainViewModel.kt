package com.pet.animal.formula.dose.health.veterinary.cure.vetformula.view

import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseViewModelForNavigation
import kotlinx.coroutines.*

class MainViewModel: BaseViewModelForNavigation() {
    /** Задание переменных */ //region
    val mainViewModelInteractor: MainViewModelInteractor = MainViewModelInteractor()
    // ViewModelCoroutineScope
    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })
    //endregion

    fun writeDataToBDAtFirstRun() {
        viewModelCoroutineScope.launch {
            mainViewModelInteractor.writeDataToBDAtFirstRun()
        }
    }

    private fun handleError(error: Throwable) {
    }
}