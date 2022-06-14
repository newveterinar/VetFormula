package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.about

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentAboutBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentAboutCalcBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import com.pet.animal.formula.dose.health.veterinary.cure.utils.screens.FabAndSliderControl
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class AboutCalcFragment : BaseFragment<FragmentAboutCalcBinding>(FragmentAboutCalcBinding::inflate) {
    /** Задание переменных */ //region
    // Навигация
    private lateinit var buttonToBackScreen: ImageView

    // ViewModel
    private lateinit var viewModel: AboutCaclFragmentViewModel

    // ShowAboutFragmentScope
    private lateinit var showAboutCalcFragmentScope: Scope


    // newInstance для данного класса
    companion object {
        fun newInstance(): AboutCalcFragment = AboutCalcFragment()
    }
    //endregion

    /** Работа со Scope */ //region
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Задание Scope для данного фрагмента
        showAboutCalcFragmentScope = KoinJavaComponent.getKoin().getOrCreateScope(
            FragmentScope.SHOW_ABOUT_CALC_SCOPE,
            named(FragmentScope.SHOW_ABOUT_CALC_SCOPE)
        )
    }

    override fun onDetach() {
        // Удаление скоупа для данного фрагмента
        showAboutCalcFragmentScope.close()
        super.onDetach()
    }
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализация кнопок
        initButtons()
        // Инициализация ViewModel
        initViewModel()
        // Скрыти слайдера и FAB
        val ma = (activity as FabAndSliderControl)
        ma.hideFab()
        ma.hideSlider()
    }

    // Инициализация кнопок
    private fun initButtons() {
        buttonToBackScreen = binding.aboutAboutButton
        buttonToBackScreen.setOnClickListener {
            viewModel.router.exit()
        }
    }

    // Инициализация ViewModel
    private fun initViewModel() {
        val _viewModel: AboutCaclFragmentViewModel by showAboutCalcFragmentScope.inject()
        viewModel = _viewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        // Отображение слайдера и FAB
        val ma = (activity as FabAndSliderControl)
        ma.showFab()
        ma.showSlider()
    }
}