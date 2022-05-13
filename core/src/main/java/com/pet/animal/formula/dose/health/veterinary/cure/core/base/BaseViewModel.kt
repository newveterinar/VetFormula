package com.pet.animal.formula.dose.health.veterinary.cure.core.base

import androidx.lifecycle.MutableLiveData
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import kotlinx.coroutines.*

abstract class BaseViewModel<T: AppState>(
    protected open val _mutableLiveData: MutableLiveData<T> = MutableLiveData()
): BaseViewModelForNavigation() {

    /** Задание переменных */ //region
    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })
    //endregion

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    // Метод получения данных
    abstract fun getData()
    /** Методы сохранения данных */
    abstract fun saveData(screenType: ScreenType,
                          listsAddFirstSecond: List<Int>,
                          values: List<Double>,
                          dimensions: List<Int>)

    abstract fun handleError(error: Throwable)
}