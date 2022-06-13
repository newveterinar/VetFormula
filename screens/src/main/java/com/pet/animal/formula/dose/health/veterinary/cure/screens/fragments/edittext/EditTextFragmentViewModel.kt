package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.edittext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseViewModelForNavigation
import java.lang.StringBuilder

class EditTextFragmentViewModel : BaseViewModelForNavigation() {

    var stringBuilder = StringBuilder()

    private val _urlLiveData = MutableLiveData<Pair<String, String>>()
    val urlLiveData: LiveData<Pair<String, String>> = _urlLiveData

    fun setUrlLiveData(pair: Pair<String, String>) {
        _urlLiveData.value = pair
    }

    fun addUrlAsNote(string: String){
        stringBuilder.append(string)
    }
}