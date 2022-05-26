package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.doses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.doses.result.PharmacyDosesInteractorImpl
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface.PharmacySurfaceInteractorImpl
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import kotlinx.coroutines.launch

class PharmacyDosesFragmentViewModel: BaseViewModel<AppState>() {
    /** Исходные данные */ //region
    // LiveData
    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData
    private val _checkAreTheFieldsFilledInLiveData = MutableLiveData<Boolean>()
    val checkEditTextFieldsLiveData : LiveData<Boolean>
        get() = _checkAreTheFieldsFilledInLiveData
    // Интерактор
    private val interactor: PharmacyDosesInteractorImpl =
        PharmacyDosesInteractorImpl(this)
    //endregion

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
        stringValues: List<String>,
        values: List<Double>,
        dimensions: List<Int>,
        isGoToResultScreen: Boolean
    ) {
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
//        TODO("Not yet implemented")
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

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }
}