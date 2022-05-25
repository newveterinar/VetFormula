package com.pet.animal.formula.dose.health.veterinary.cure.utils.language_utils

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import java.util.*


object LocaleHelper {

    private const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
    private var isInitialised = false

    var currentLocale: Locale = Locale.getDefault()

    fun onAttach(context: Context): Context {
        if (!isInitialised) {
            Locale.setDefault(loadLocale(context))
            isInitialised = true
        }
        return updateContextResources(context, Locale.getDefault())
    }

    fun onResume(activity: Activity){
        if (currentLocale == Locale.getDefault()) {
            return
        }
        activity.recreate()
    }

    fun onPause(){
        currentLocale = Locale.getDefault()
    }


    private fun updateContextResources(context: Context, locale: Locale): Context {
        if (context.currentLocale == locale && context is Application) {
            return context
        }

        val resources = context.resources
        val config = resources.configuration
        config.setCurrentLocale(locale)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.createConfigurationContext(config)
        } else {
            resources.updateConfiguration(config, resources.displayMetrics)
            context
        }
    }

    private fun loadLocale(context: Context): Locale {
        val sPref = getSharedPreferences(context)
        val defaultLocale = Locale.getDefault()
        val language =
            sPref.getString(SELECTED_LANGUAGE, defaultLocale.language) ?: return defaultLocale
        return Locale(language)
    }

    private fun getSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(LocaleHelper::class.java.name, Context.MODE_PRIVATE)

    fun getLocale(context: Context) = loadLocale(context)

    private fun setLocale(context: Context, newLocale: Locale): Context {
        persist(context, newLocale)
        Locale.setDefault(newLocale)
        currentLocale = newLocale
        return updateContextResources(context, newLocale)
    }

    fun setLocale(activity: Activity, newLocale: Locale){
        setLocale((activity as Context), newLocale)
        activity.recreate()
    }

    private fun persist(context: Context, locale: Locale?) {
        if (locale == null) {
            return
        }
        getSharedPreferences(context).edit()
            .putString(SELECTED_LANGUAGE, locale.language)
            .apply()
    }
}