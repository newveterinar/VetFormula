package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.doses

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacyDosesBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class PharmacyDosesFragment :
    BaseFragment<FragmentPharmacyDosesBinding>(FragmentPharmacyDosesBinding::inflate) {

    /** Задание переменных */ //region
    // Навигация
    private val navigationButtons = arrayOfNulls<View>(size = 3)

    private lateinit var weightEditText: EditText
    private lateinit var doseEditText: EditText
    private lateinit var concentrationEditText: EditText

    private lateinit var weightDimensionSpinner: Spinner
    private lateinit var doseDimensionSpinner: Spinner
    private lateinit var concentrationDimensionSpinner: Spinner

    private lateinit var clearButton: View

    private val listOfDimensionSpinners = arrayOfNulls<Spinner>(size = 3)
    private val listOfEditTexts = arrayOfNulls<EditText>(size = 3)

    private lateinit var showPharmacyDoseFragmentScope: Scope

    // ViewModel
    private lateinit var viewModel: PharmacyDosesFragmentViewModel
    //endregion

    override fun onAttach(context: Context) {
        super.onAttach(context)
        showPharmacyDoseFragmentScope =
            getKoin().getOrCreateScope(FragmentScope.SHOW_PHARMACY_DOSE_FRAGMENT_SCOPE,
                named(FragmentScope.SHOW_PHARMACY_DOSE_FRAGMENT_SCOPE))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация вьюшек
        initNavigationButtons()
        initTextFields()
        initSpinners()
        initButtons()
        // Инициализация ViewModel
        initViewModel()
    }

    // Инициализация кнопок
    private fun initNavigationButtons() {
        binding.apply {
            navigationButtons.also {
                it[0] = this.pharmacyPreviousButtonContainer ?: null
                it[1] = this.pharmacyAboutButton
                it[2] = this.pharmacyCalculateButton
            }
        }
        navigationButtons.forEachIndexed { index, button ->
            button?.setOnClickListener {
                when (index) {
                    0 -> viewModel.router.exit()
                    1 -> viewModel.router.navigateTo(viewModel.screens.aboutScreen())
                    2 -> viewModel.router.navigateTo(viewModel.screens.pharmacyDosesResultScreen())
                    else -> {
                        Toast.makeText(requireContext(), "Кнопка не назначена", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun initTextFields() {
        binding.apply {
            weightEditText = this.pharmacyWeightTextinputlayoutTextfield
            doseEditText = this.pharmacyDoseTextinputlayoutTextfield
            concentrationEditText = this.pharmacyConcentrationTextinputlayoutTextfield
        }
        listOfEditTexts.also {
            it[0] = weightEditText
            it[1] = doseEditText
            it[2] = concentrationEditText
        }
        listOfEditTexts.forEach { field ->
            field?.doOnTextChanged { _, _, _, _ ->
                viewModel.checkAreTheFieldsFilledIn(listOfEditTexts.map { it?.text.toString() })
            }
        }
    }

    private fun initSpinners() {
        binding.apply {
            weightDimensionSpinner = this.pharmacyWeightDimensionList
            doseDimensionSpinner = this.pharmacyDoseDimensionList
            concentrationDimensionSpinner = this.pharmacyConcentrationDimensionList
        }
        listOfDimensionSpinners.also {
            it[0] = weightDimensionSpinner
            it[1] = doseDimensionSpinner
            it[2] = concentrationDimensionSpinner
        }.forEach { spinner ->
            spinner?.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    saveData()
                    Toast.makeText(this@PharmacyDosesFragment.requireContext(),
                        "${spinner?.selectedItem} selected", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }

        }
    }

    private fun initButtons() {
        clearButton = binding.pharmacyClearButton.also { button ->
            button.setOnClickListener {
                listOfEditTexts.forEach {
                    it?.setText("")
                }
                listOfDimensionSpinners.forEach {
                    it?.setSelection(0)
                }
                saveData()
            }
        }
    }

    // Инициализация ViewModel
    private fun initViewModel() {
        val _viewModel: PharmacyDosesFragmentViewModel by showPharmacyDoseFragmentScope.inject()
        viewModel = _viewModel
        viewModel.subscribe().observe(viewLifecycleOwner) {
            //FIXME: Пока не реализовано сохранение. renderData(it) не будет работать
        }
        //FIXME: Пока не реализовано сохранение. getData() не будет работать
        viewModel.checkEditTextFieldsLiveData.observe(viewLifecycleOwner) {
            binding.pharmacyCalculateButton.isEnabled = it
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                appState.screenData.let {
                    // Установка численного значения поля
                    listOfEditTexts.forEachIndexed { index, valueField ->
                        if (it.valueFields.count() > index)
                            valueField?.setText(if (it.valueFields[index].value > 0.0)
                                "${it.valueFields[index].value}" else "")
                    }
                    // Установка размерности поля
                    listOfDimensionSpinners.forEachIndexed { index, dimension ->
                        if (it.valueFields.count() > index)
                            dimension?.setSelection(it.valueFields[index].dimension)
                    }
                }
            }
            is AppState.Loading -> Unit
            is AppState.Error -> {
                Toast.makeText(requireContext(),
                    requireContext().getString(
                        R.string.error_appstate_not_loaded_for_fragment),
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveData() {
        // FIXME: В этом фрагменте нету полей для listsAddFirstSecond. Сделать nullable или перегрузка в логике сохранения или другое
    }

    companion object {
        fun newInstance(): PharmacyDosesFragment = PharmacyDosesFragment()
        private const val TAG = "PharmacyDosesFragment"
        private val SCREEN_TYPE = ScreenType.PHARMACY_DOSES
    }
}
