package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacyBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class PharmacyFragment: BaseFragment<FragmentPharmacyBinding>(FragmentPharmacyBinding::inflate) {
    /** Задание переменных */ //region
    // Навигация
    private val navigationButtons = arrayOfNulls<View>(4)
    // ViewModel
    private lateinit var viewModel: PharmacyFragmentViewModel
    // ShowPharmacyFragmentScope
    private lateinit var showPharmacyFragmentScope: Scope
    //endregion

    /** Работа со Scope */ //region
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Задание Scope для данного фрагмента
        showPharmacyFragmentScope = KoinJavaComponent.getKoin().getOrCreateScope(
            FragmentScope.SHOW_PHARMACY_FRAGMENT_SCOPE,
            named(FragmentScope.SHOW_PHARMACY_FRAGMENT_SCOPE)
        )
    }
    override fun onDetach() {
        // Удаление скоупа для данного фрагмента
        showPharmacyFragmentScope.close()
        super.onDetach()
    }
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализация ViewModel
        initViewModel()
        // Инициализация кнопок
        initNavigationButtons()
    }

    // Инициализация кнопок
    private fun initNavigationButtons() {
        binding.apply {
            navigationButtons.also {
                it[0] = this.pharmacyPreviousButtonContainer
                it[1] = this.pharmacySurfaceButtonContainer
                it[2] = this.pharmacyDoseButtonContainer
                it[3] = this.pharmacyCriButtonContainer
            }


        }

        navigationButtons.forEachIndexed { index, button ->
            button?.setOnClickListener {
                when (index) {
                    0 -> viewModel.router.exit()
                    1 -> viewModel.router.navigateTo(viewModel.screens.pharmacySurfaceScreen())
                    2 -> viewModel.router.navigateTo(viewModel.screens.pharmacyDosesScreen())
                    3 -> viewModel.router.navigateTo(viewModel.screens.pharmacyCRIScreen())
                    else -> {
                        Toast.makeText(requireContext(), requireActivity().resources.getString(
                            R.string.error_button_is_not_assigned), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    // Инициализация ViewModel
    private fun initViewModel() {
        val _viewModel: PharmacyFragmentViewModel by showPharmacyFragmentScope.inject()
        viewModel = _viewModel
    }

    companion object {
        fun newInstance(): PharmacyFragment = PharmacyFragment()
    }
}