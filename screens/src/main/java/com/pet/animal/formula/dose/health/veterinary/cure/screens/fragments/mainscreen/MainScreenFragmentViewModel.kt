package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.mainscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseViewModelForNavigation

class MainScreenFragmentViewModel: BaseViewModelForNavigation() {

    private val _toastHint = MutableLiveData<String>()
    val toastHint: LiveData<String>
        get() = _toastHint

    fun setToastHint(hint: String) {
        _toastHint.value = hint
    }
}