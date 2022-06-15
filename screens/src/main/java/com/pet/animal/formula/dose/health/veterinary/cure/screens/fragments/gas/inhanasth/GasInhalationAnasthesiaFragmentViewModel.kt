package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.gas.inhanasth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.ScreenData
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.checkToExistCorrectDouble
import kotlinx.coroutines.launch

class GasInhalationAnasthesiaFragmentViewModel: BaseViewModel<AppState>() {
    /** Задание переменных */ //region
    // LiveData
    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData
    private val _checkAreTheFieldsFilledInLiveData = MutableLiveData<Boolean>()
    val checkEditTextFieldsLiveData: LiveData<Boolean>
        get() = _checkAreTheFieldsFilledInLiveData
    // Интерактор
    private val interactor: GasInhalationAnasthesiaFragmentInteractorImpl =
        GasInhalationAnasthesiaFragmentInteractorImpl(this)
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
        stringValues: List<String>,
        values: List<Double>,
        dimensions: List<Int>,
        isGoToResultScreen: Boolean) {
        viewModelCoroutineScope.launch {
            startInteractorSetData(
                screenType,
                listsAddFirstSecond,
                stringValues,
                values,
                dimensions,
                isGoToResultScreen)
        }
    }
    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }
    private suspend fun startInteractorGetData() {
        _mutableLiveData.postValue(interactor.getData())
    }
    private suspend fun startInteractorSetData(screenType: ScreenType,
                                               listsAddFirstSecond: List<Int>,
                                               stringValues: List<String>,
                                               values: List<Double>,
                                               dimensions: List<Int>,
                                               isGoToResultScreen: Boolean) {
        interactor.saveData(
            screenType, listsAddFirstSecond, stringValues, values, dimensions, isGoToResultScreen)
    }
    //endregion

    // Получение LiveData
    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    // Проверка наличие в текстовых полях данных
    fun checkAreTheFieldsFilledIn(listOfFieldsValue: List<String>){
        _checkAreTheFieldsFilledInLiveData.value = listOfFieldsValue.none {
            !it.checkToExistCorrectDouble()
        }
    }

    // Установка признака в liveDataForViewToObserve перехода на окно с результатом
    fun setIsGoToResultScreenToLiveData() {
        _mutableLiveData.postValue(AppState.Success(ScreenData(true)))
    }
}