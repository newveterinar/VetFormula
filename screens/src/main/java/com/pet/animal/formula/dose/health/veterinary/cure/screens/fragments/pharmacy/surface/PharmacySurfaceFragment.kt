package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.terrakok.cicerone.Router
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacySurfaceBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.AppScreensImpl
import com.pet.animal.formula.dose.health.veterinary.cure.utils.SHOW_PHARMACY_SURFACE_FRAGMENT_SCOPE
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.utils.resources.ResourcesProviderImpl
import org.koin.java.KoinJavaComponent

class PharmacySurfaceFragment:
    BaseFragment<FragmentPharmacySurfaceBinding>(FragmentPharmacySurfaceBinding::inflate) {
    /** Задание переменных */ //region
    // Навигация
    private val screens: AppScreensImpl = KoinJavaComponent.getKoin().get()
    private val router: Router = KoinJavaComponent.getKoin().get()
    private lateinit var buttonToPharmacySurfaceScreen: ConstraintLayout
    private lateinit var buttonToPharmacySurfaceResultScreen: Button
    private lateinit var buttonToAboutScreen: ImageView
    // ViewModel
    private lateinit var model: PharmacySurfaceFragmentViewModel
    // ShowPharmacySurfaceFragmentScope
    private lateinit var showPharmacySurfaceFragmentScope: Scope
    private val resourcesProviderImpl: ResourcesProviderImpl = KoinJavaComponent.getKoin().get()
    // newInstance для данного класса
    companion object {
        fun newInstance(): PharmacySurfaceFragment = PharmacySurfaceFragment()
    }
    //endregion

    /** Работа со Scope */ //region
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Задание Scope для данного фрагмента
        showPharmacySurfaceFragmentScope = KoinJavaComponent.getKoin().getOrCreateScope(
            SHOW_PHARMACY_SURFACE_FRAGMENT_SCOPE,
            named(SHOW_PHARMACY_SURFACE_FRAGMENT_SCOPE))
    }

    override fun onDetach() {
        // Удаление скоупа для данного фрагмента
        showPharmacySurfaceFragmentScope.close()
        super.onDetach()
    }
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализация кнопок
        initButtons()
        // Инициализация ViewModel
        initViewModel()
    }

    // Инициализация кнопок
    fun initButtons() {
        buttonToPharmacySurfaceScreen = binding.pharmacyPreviousButtonContainer
        buttonToPharmacySurfaceScreen.setOnClickListener {
            requireActivity().onBackPressed()
        }
        buttonToPharmacySurfaceResultScreen = binding.pharmacyCalculateButton
        buttonToPharmacySurfaceResultScreen.setOnClickListener {
            router.navigateTo(screens.pharmacySurfaceResultScreen())
        }
        buttonToAboutScreen = binding.pharmacyAboutButton
        buttonToAboutScreen.setOnClickListener {
            router.navigateTo(screens.aboutScreen())
        }
    }

    // Инициализация ViewModel
    fun initViewModel() {
        val viewModel: PharmacySurfaceFragmentViewModel by showPharmacySurfaceFragmentScope.inject()
        model = viewModel
        model.subscribe().observe(viewLifecycleOwner, Observer<AppState> {
            renderData(it)
        })
    }

    private fun renderData(appState: AppState) {
        Toast.makeText(requireContext(), "!!!", Toast.LENGTH_LONG).show()
        when (appState) {
            is AppState.Success -> {
                appState.data?.let {
                    Toast.makeText(requireContext(), "${it.proba}", Toast.LENGTH_LONG).show()
                }
            }
            is AppState.Loading -> {
                if (appState.progress != null) {
                } else {
                }
            }
            is AppState.Error -> {
                Toast.makeText(requireContext(),
                    resourcesProviderImpl.getString(
                        R.string.error_appstate_not_loaded_for_fragment),
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}