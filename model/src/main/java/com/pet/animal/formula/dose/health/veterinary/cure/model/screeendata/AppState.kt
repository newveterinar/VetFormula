package com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata

sealed class AppState {
    data class SuccessPharmacySurface(
        val pharmacySurfaceScreenData: ScreenData
    ): AppState()
    data class SuccessPharmacyDose(
        val pharmacyDoseScreenData: ScreenData): AppState()
    data class SuccessPharmacyCRI(
        val pharmacyCRIScreenData: ScreenData): AppState()
    data class Loading(val progress: Int?): AppState()
    data class Error(val error: Throwable): AppState()
}