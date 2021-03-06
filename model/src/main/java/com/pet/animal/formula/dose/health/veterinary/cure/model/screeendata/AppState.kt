package com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata

sealed class AppState {
    data class Success(val screenData: ScreenData): AppState()
    data class Loading(val progress: Int?): AppState()
    data class Error(val error: Throwable): AppState()
}