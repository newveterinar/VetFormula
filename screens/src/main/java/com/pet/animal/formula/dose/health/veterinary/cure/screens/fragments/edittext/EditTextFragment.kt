package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.edittext

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentEditTextBinding

class EditTextFragment : BaseFragment<FragmentEditTextBinding>(FragmentEditTextBinding::inflate) {

    private lateinit var viewModel: EditTextFragmentViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

    }

    // Инициализация ViewModel
    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(EditTextFragmentViewModel::class.java)
    }

    companion object {
        fun newInstance(): EditTextFragment = EditTextFragment()
    }
}