package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.doses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.doses.result.PharmacyDosesInteractorImpl
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import kotlinx.coroutines.launch

class PharmacyDosesFragmentViewModel(
    private val interactor: PharmacyDosesInteractorImpl
) : BaseViewModel<AppState>() {

    // LiveData
    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    private val _checkAreTheFieldsFilledInLiveData = MutableLiveData<Boolean>()
    val checkEditTextFieldsLiveData : LiveData<Boolean>
        get() = _checkAreTheFieldsFilledInLiveData

    fun checkAreTheFieldsFilledIn(listOfFieldsValue: List<String>){
        _checkAreTheFieldsFilledInLiveData.value = listOfFieldsValue.none { it.isBlank() }
    }

    override fun getData() {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            startInteractorGetData()
        }
    }

    // FIXME: В этом фрагменте нету полей для listsAddFirstSecond. Сделать nullable или перегрузка или ...
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
//        TODO("Not yet implemented")
    }

    private suspend fun startInteractorGetData() {
        _mutableLiveData.postValue(interactor.getData())
    }

    private suspend fun startInteractorSetData(screenType: ScreenType,
                                               listsAddFirstSecond: List<Int>,
                                               values: List<Double>,
                                               dimensions: List<Int>) {
        interactor.saveData(screenType, listsAddFirstSecond, values, dimensions)
    }

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }
}