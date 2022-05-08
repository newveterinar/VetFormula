package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface

import android.util.Log
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.Interactor
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.DataModel

class PharmacySurfaceInteractorImpl: Interactor<AppState> {
    override suspend fun getData(): AppState {
        return AppState.Success(DataModel(proba = 5))
    }
}