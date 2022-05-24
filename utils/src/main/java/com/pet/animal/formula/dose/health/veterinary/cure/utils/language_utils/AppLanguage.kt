package com.pet.animal.formula.dose.health.veterinary.cure.utils.language_utils

sealed class AppLanguage(val locale: String){
    class English(locale: String = "en"): AppLanguage(locale)
    class Russian(locale: String = "ru"): AppLanguage(locale)
}
