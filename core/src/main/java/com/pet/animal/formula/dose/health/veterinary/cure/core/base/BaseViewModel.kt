package com.pet.animal.formula.dose.health.veterinary.cure.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import kotlinx.coroutines.*

abstract class BaseViewModel<T : AppState>(
    protected open val _mutableLiveData: MutableLiveData<T> = MutableLiveData(),
) : BaseViewModelForNavigation() {

    private val _toastHint = MutableLiveData<String>()
    val toastHint: LiveData<String>
        get() = _toastHint

    fun setToastHint(hint: String) {
        _toastHint.value = hint
    }

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
    abstract fun saveData(
        screenType: ScreenType,
        listsAddFirstSecond: List<Int>,
        stringValues: List<String>,
        values: List<Double>,
        dimensions: List<Int>,
        isGoToResultScreen: Boolean,
    )

    abstract fun handleError(error: Throwable)
}