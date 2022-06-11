package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.calculator.keyboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseViewModelCalcKeyboard
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppStateCalcKeyboard

class CalculatorKeyboardFragmentViewModel: BaseViewModelCalcKeyboard<AppStateCalcKeyboard>() {
    /** Задание переменных */ //region
    // LiveData
    private val liveDataForViewToObserve: LiveData<AppStateCalcKeyboard> = _mutableLiveData
    private val _checkAreTheFieldsFilledInLiveData = MutableLiveData<Boolean>()
    val checkEditTextFieldsLiveData: LiveData<Boolean>
        get() = _checkAreTheFieldsFilledInLiveData
    // Интерактор
    private val interactor: CalculatorKeyboardFragmentInteractorImpl =
        CalculatorKeyboardFragmentInteractorImpl(this)

    override fun getData() {
//        TODO("Not yet implemented")
    }

    override fun saveData() {
//        TODO("Not yet implemented")
    }

    override fun handleError(error: Throwable) {
//        TODO("Not yet implemented")
    }
    //endregion
}