package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface.result

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacySurfaceResultBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface.PharmacySurfaceFragmentViewModel
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class PharmacySurfaceResultFragment: BaseFragment<FragmentPharmacySurfaceResultBinding>(
    FragmentPharmacySurfaceResultBinding::inflate) {
    /** Задание переменных */ //region
    // Навигация
    private val navigationButtons = arrayOfNulls<View>(size = 2)

    // ViewModel
    private lateinit var viewModel: PharmacySurfaceResultFragmentViewModel
    // ShowPharmacySurfaceFragmentScope
    private lateinit var showPharmacySurfaceResultFragmentScope: Scope
    //endregion

    /** Работа со Scope */ //region
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Задание Scope для данного фрагмента
        showPharmacySurfaceResultFragmentScope = KoinJavaComponent.getKoin().getOrCreateScope(
            FragmentScope.SHOW_PHARMACY_SURFACE_RESULT_FRAGMENT_SCOPE,
            named(FragmentScope.SHOW_PHARMACY_SURFACE_RESULT_FRAGMENT_SCOPE)
        )
    }
    override fun onDetach() {
        // Удаление скоупа для данного фрагмента
        showPharmacySurfaceResultFragmentScope.close()
        super.onDetach()
    }
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализация кнопок
        initNavigationButtons()
        // Инициализация ViewModel
        initViewModel()
    }

    // Инициализация кнопок
    private fun initNavigationButtons() {
        binding.apply {
            navigationButtons.also {
                it[0] = this.pharmacyPreviousButtonContainer
            }
        }

        navigationButtons.forEachIndexed { index, button ->
            button?.setOnClickListener {
                when (index) {
                    0 -> viewModel.router.exit()
                    else -> {
                         Toast.makeText(requireContext(),
                             requireActivity().resources.getString(
                            R.string.error_button_is_not_assigned), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    // Инициализация ViewModel
    fun initViewModel() {
        val _viewModel: PharmacySurfaceResultFragmentViewModel by
            showPharmacySurfaceResultFragmentScope.inject()
        viewModel = _viewModel
    }

    companion object {
        fun newInstance(): PharmacySurfaceResultFragment =
            PharmacySurfaceResultFragment()
    }
}