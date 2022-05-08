package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface

import com.pet.animal.formula.dose.health.veterinary.cure.core.base.Interactor
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.DataModel
import com.pet.animal.formula.dose.health.veterinary.cure.utils.resources.ResourcesProviderImpl
import org.koin.java.KoinJavaComponent

class PharmacySurfaceInteractorImpl(
    private val resourcesProviderImpl: ResourcesProviderImpl = KoinJavaComponent.getKoin().get()
): Interactor<AppState> {
    override suspend fun getData(): AppState {
        return AppState.Success(DataModel(proba = 5))
    }
}