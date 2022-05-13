package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface

import androidx.lifecycle.LiveData
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import kotlinx.coroutines.launch

class PharmacySurfaceFragmentViewModel(
    private val interactor: PharmacySurfaceInteractorImpl,
) : BaseViewModel<AppState>() {
    /** Задание переменных */ //region
    // LiveData
    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData
    //endregion

    /** Базовые методы от BaseViewModel */ //region
    override fun getData() {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            startInteractorGetData()
        }
    }

    override fun saveData(
        screenType: ScreenType,
        listsAddFirstSecond: List<Int>,
        values: List<Double>,
        dimensions: List<Int>,
    ) {
        viewModelCoroutineScope.launch {
            startInteractorSetData(screenType, listsAddFirstSecond, values, dimensions)
        }
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    private suspend fun startInteractorGetData() {
        _mutableLiveData.postValue(interactor.getData())
    }

    private suspend fun startInteractorSetData(
        screenType: ScreenType,
        listsAddFirstSecond: List<Int>,
        values: List<Double>,
        dimensions: List<Int>,
    ) {
        interactor.saveData(screenType, listsAddFirstSecond, values, dimensions)
    }
    //endregion

    // Получение LiveData
    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }
}