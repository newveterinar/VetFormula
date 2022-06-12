package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.edittext

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentEditTextBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import com.pet.animal.formula.dose.health.veterinary.cure.utils.settings.SettingsImpl
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class EditTextFragment: BaseFragment<FragmentEditTextBinding>(FragmentEditTextBinding::inflate) {
    /** Задание переменных */ //region
    private lateinit var viewModel: EditTextFragmentViewModel
    // Текстовый элемент
    lateinit var editText: TextView
    // SettingsImpl
    private val settings: SettingsImpl = KoinJavaComponent.getKoin().get()
    // ShowPharmacySurfaceResultFragmentScope
    private lateinit var showEditTextFragmentScope: Scope
    // newInstance для данного класса
    companion object {
        fun newInstance(): EditTextFragment = EditTextFragment()
    }
    //endregion

    /** Работа со Scope */ //region
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Задание Scope для данного фрагмента
        showEditTextFragmentScope = KoinJavaComponent.getKoin().getOrCreateScope(
            FragmentScope.SHOW_EDIT_TEXT_FRAGMENT_SCOPE,
            named(FragmentScope.SHOW_EDIT_TEXT_FRAGMENT_SCOPE)
        )
    }
    override fun onDetach() {
        // Удаление скоупа для данного фрагмента
        showEditTextFragmentScope.close()
        super.onDetach()
    }
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализация текстового элемента
        initEditText()
        // Инициализация ViewModel
        initViewModel()
    }

    // Инициализация текстового элемента
    @SuppressLint("SetTextI18n")
    private fun initEditText() {
        editText = binding.editTextText
        editText.text = "${settings.getCurrentVetmedicalUrl()}\n${settings.getCurrentWsavaUrl()}"
    }

    // Инициализация ViewModel
    private fun initViewModel() {
        val _viewModel: EditTextFragmentViewModel by showEditTextFragmentScope.inject()
        viewModel = _viewModel
    }
}