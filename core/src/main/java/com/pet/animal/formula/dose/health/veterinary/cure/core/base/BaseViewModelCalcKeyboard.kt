package com.pet.animal.formula.dose.health.veterinary.cure.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppStateCalcKeyboard
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import kotlinx.coroutines.*

abstract class BaseViewModelCalcKeyboard<T : AppStateCalcKeyboard>(
    protected open val _mutableLiveData: MutableLiveData<T> = MutableLiveData(),
): BaseViewModelForNavigation() {
    /** Задание переменных */ //region
    // Переменные для вывода подсказок
    private val _toastHint = MutableLiveData<String>()
    val toastHint: LiveData<String>
        get() = _toastHint
    // Скоуп для вьюмодели
    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })
    //endregion

    // Функция для вывода подсказок пользователю
    fun setToastHint(hint: String) {
        _toastHint.value = hint
    }

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
    abstract fun saveData()

    abstract fun handleError(error: Throwable)
}