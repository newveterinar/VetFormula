package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.checkToExistCorrectDouble
import com.pet.animal.formula.dose.health.veterinary.cure.utils.resources.ResourcesProviderImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent

class PharmacySurfaceFragmentViewModel(
    private val interactor: PharmacySurfaceInteractorImpl
): BaseViewModel<AppState>() {
    /** Задание переменных */ //region
    // LiveData
    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData
    private val _checkAreTheFieldsFilledInLiveData = MutableLiveData<Boolean>()
    val checkEditTextFieldsLiveData: LiveData<Boolean>
        get() = _checkAreTheFieldsFilledInLiveData
    //endregion

    /** Базовые методы от BaseViewModel */ //region
    override fun getData() {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            startInteractorGetData()
        }
    }
    override fun saveData(screenType: ScreenType,
                          listsAddFirstSecond: List<Int>,
                          stringValues: List<String>,
                          values: List<Double>,
                          dimensions: List<Int>) {
            viewModelCoroutineScope.launch {
                withContext(Dispatchers.Main) {
                    startInteractorSetData(
                        screenType, listsAddFirstSecond, stringValues, values, dimensions)
            }
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
                                               dimensions: List<Int>) {
        interactor.saveData(screenType, listsAddFirstSecond, stringValues, values, dimensions)
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
}