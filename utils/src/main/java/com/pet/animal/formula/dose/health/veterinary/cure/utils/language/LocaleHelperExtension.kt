package com.pet.animal.formula.dose.health.veterinary.cure.utils.language

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import java.util.*

val Context.currentLocale: Locale
    get() = resources.configuration.currentLocale

val Configuration.currentLocale: Locale
    get() = locales[0]

fun Configuration.setCurrentLocale(locale: Locale) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        this.setLocale(locale)
    } else {
        this.locale = locale
    }
}



