package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.conversions

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentConversionsBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.AppScreensImpl
import org.koin.java.KoinJavaComponent

class ConversionsFragment :
    BaseFragment<FragmentConversionsBinding>(FragmentConversionsBinding::inflate) {

    /** Задание переменных */ //region
    // Навигация
    private val navigationButtons = mutableListOf<View>()
    // ViewModel
    lateinit var model: ConversionsFragmentViewModel
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
        binding.apply {
            navigationButtons.addAll(listOf(
                this.conversionsPreviousButtonContainer,
                this.conversionsSurfaceButtonContainer,
                this.conversionsAboutButton,
            ))
        }

        navigationButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                when (index) {
                    0 -> model.router.exit()
                    1 -> Toast.makeText(requireContext(), "Кнопка не назначена", Toast.LENGTH_SHORT).show()
                    2 -> model.router.navigateTo(model.screens.aboutScreen())
                    else ->{
                        Toast.makeText(requireContext(), "Кнопка не назначена", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    // Инициализация ViewModel
    fun initViewModel() {
        model = ViewModelProvider(this).get(ConversionsFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): ConversionsFragment = ConversionsFragment()
    }
}