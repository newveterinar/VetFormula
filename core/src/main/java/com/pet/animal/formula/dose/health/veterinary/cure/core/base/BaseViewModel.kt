package com.pet.animal.formula.dose.health.veterinary.cure.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import kotlinx.coroutines.*

abstract class BaseViewModel<T: AppState>(
    protected open val _mutableLiveData: MutableLiveData<T> = MutableLiveData()
): ViewModel() {

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    abstract fun getData()

    abstract fun handleError(error: Throwable)
}