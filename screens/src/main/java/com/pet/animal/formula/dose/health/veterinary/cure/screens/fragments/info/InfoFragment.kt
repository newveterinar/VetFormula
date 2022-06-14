package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.info

import android.content.Context
import android.os.Bundle
import android.view.View
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentInfoBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import com.pet.animal.formula.dose.health.veterinary.cure.utils.screens.FabAndSliderControl
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class InfoFragment : BaseFragment<FragmentInfoBinding>(FragmentInfoBinding::inflate) {
    /** Задание переменных */ //region
    // Навигация
    private val navigationButtons = arrayOfNulls<View>(size = 8)

    // ViewModel
    private lateinit var viewModel: InfoFragmentViewModel

    // ShowAboutFragmentScope
    private lateinit var showInfoFragmentScope: Scope
    //endregion

    /** Работа со Scope */ //region
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Задание Scope для данного фрагмента
        showInfoFragmentScope = KoinJavaComponent.getKoin().getOrCreateScope(
            FragmentScope.SHOW_INFO_FRAGMENT_SCOPE,
            named(FragmentScope.SHOW_INFO_FRAGMENT_SCOPE)
        )
    }

    override fun onDetach() {
        // Удаление скоупа для данного фрагмента
        showInfoFragmentScope.close()
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
        binding.apply {
            navigationButtons.also {
                it[0] = this.infoAboutButton
                it[1] = this.infoPreviousButton
            }
        }
        navigationButtons.forEachIndexed { index, button ->
            button?.setOnClickListener {
                when (index) {
                    0 -> viewModel.router.navigateTo(viewModel.screens.aboutScreen())
                    1 -> viewModel.router.navigateTo(viewModel.screens.mainScreen())
                }
            }
        }
    }

    // Инициализация ViewModel
    private fun initViewModel() {
        val _viewModel: InfoFragmentViewModel by showInfoFragmentScope.inject()
        viewModel = _viewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        // Отображение слайдера и FAB
        val ma = (activity as FabAndSliderControl)
        ma.showFab()
        ma.showSlider()
    }

    companion object {
        fun newInstance(): InfoFragment = InfoFragment()
    }
}