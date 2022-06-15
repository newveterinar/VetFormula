package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.about

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentAboutTimerBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import com.pet.animal.formula.dose.health.veterinary.cure.utils.NUMBER_NAVIGATION_BUTTONS_ON_OUTPUT_DATA_SCREENS
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class AboutTimerFragment :
    BaseFragment<FragmentAboutTimerBinding>(FragmentAboutTimerBinding::inflate) {
    /** Задание переменных */ //region
    // Навигация
    private val navigationButtons =
        arrayOfNulls<View>(size = NUMBER_NAVIGATION_BUTTONS_ON_OUTPUT_DATA_SCREENS)

    // ViewModel
    private lateinit var viewModel: AboutTimerFragmentViewModel

    // ShowAboutFragmentScope
    private lateinit var showAboutTimerFragmentScope: Scope

    // newInstance для данного класса
    companion object {
        fun newInstance(): AboutTimerFragment = AboutTimerFragment()
    }
    //endregion

    /** Работа со Scope */ //region
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Задание Scope для данного фрагмента
        showAboutTimerFragmentScope = KoinJavaComponent.getKoin().getOrCreateScope(
            FragmentScope.SHOW_ABOUT_TIMER_SCOPE,
            named(FragmentScope.SHOW_ABOUT_TIMER_SCOPE)
        )
    }

    override fun onDetach() {
        // Удаление скоупа для данного фрагмента
        showAboutTimerFragmentScope.close()
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

    private fun initNavigationButtons() {
        binding.apply {
            navigationButtons.also {
                it[0] = this.timerPreviousButtonContainer
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
    private fun initViewModel() {
        val _viewModel: AboutTimerFragmentViewModel by showAboutTimerFragmentScope.inject()
        viewModel = _viewModel
    }
}