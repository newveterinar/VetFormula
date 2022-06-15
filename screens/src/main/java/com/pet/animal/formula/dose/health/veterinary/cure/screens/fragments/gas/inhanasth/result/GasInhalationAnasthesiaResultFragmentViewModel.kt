package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.gas.inhanasth.result

import androidx.lifecycle.LiveData
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.ResultValueField
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.ScreenData
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentGasesAnihalationAnasthesiaResultBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.gas.inhanasth.GasInhalationAnasthesiaFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface.result.PharmacySurfaceResultInteractorImpl
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import kotlinx.coroutines.launch

class GasInhalationAnasthesiaResultFragmentViewModel: BaseViewModel<AppState>() {
    /** Исходные данные */ //region
    // LiveData
    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData
    // Интерактор
    private val interactor: GasInhalationAnasthesiaResultFragmentInteractorImpl =
        GasInhalationAnasthesiaResultFragmentInteractorImpl(this)
    //endregion

    //region Базовые методы
    override fun getData() {
//        TODO("Not yet implemented")
    }
    override fun saveData(
        screenType: ScreenType,
        listsAddFirstSecond: List<Int>,
        stringValues: List<String>,
        values: List<Double>,
        dimensions: List<Int>,
        isGoToResultScreen: Boolean
    ) {
        viewModelCoroutineScope.launch {
            interactor.saveData(
                screenType,
                listsAddFirstSecond,
                stringValues,
                values,
                dimensions,
                isGoToResultScreen
            )
        }
    }
    override fun handleError(error: Throwable) {
//        TODO("Not yet implemented")
    }
    //endregion

    // Получение LiveData
    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    // Установка признака в liveDataForViewToObserve перехода на окно с результатом
    fun setResultScreenToLiveData(resultValueField: MutableList<ResultValueField>) {
        _mutableLiveData.postValue(AppState.Success(ScreenData(resultValueField)))
    }

}