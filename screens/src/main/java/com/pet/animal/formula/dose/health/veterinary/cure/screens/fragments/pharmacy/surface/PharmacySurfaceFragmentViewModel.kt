package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface

import android.widget.Toast
import androidx.lifecycle.LiveData
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.utils.resources.ResourcesProviderImpl
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.getKoin

class PharmacySurfaceFragmentViewModel(
    private val interactor: PharmacySurfaceInteractorImpl,
    private val resourcesProviderImpl: ResourcesProviderImpl = getKoin().get()
): BaseViewModel<AppState>() {

    /** Задание переменных */ //region
    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData
    //endregion

    /** Базовые методы от BaseViewModel */ //region
    override fun getData() {
        Toast.makeText(resourcesProviderImpl.context, "!!!", Toast.LENGTH_LONG).show()
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            startInteractor()
        }
    }
    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }
    private suspend fun startInteractor() {
        _mutableLiveData.postValue(interactor.getData())
    }
    //endregion

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }
}