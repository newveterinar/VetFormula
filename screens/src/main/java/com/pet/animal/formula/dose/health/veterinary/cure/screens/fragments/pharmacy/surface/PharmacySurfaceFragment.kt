package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputLayout
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacySurfaceBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.convertListEditTextToListDouble
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.convertListSpinnerToListInt
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class PharmacySurfaceFragment :
    BaseFragment<FragmentPharmacySurfaceBinding>(FragmentPharmacySurfaceBinding::inflate) {
    /** Задание переменных */ //region
    // Установка типа формулы для текущего окна
    private val screenType: ScreenType = ScreenType.PHARMACY_SURFACE

    // Навигационные кнопки (для перехода на другие экраны)
    private val navigationButtons = arrayOfNulls<View>(size = 3)

    // Обнуление значений во всех полях
    private lateinit var pharmacyClearButtonContainer: ConstraintLayout

    // ViewModel
    private lateinit var viewModel: PharmacySurfaceFragmentViewModel

    // ShowPharmacySurfaceFragmentScope
    private lateinit var showPharmacySurfaceFragmentScope: Scope

    // Списки (Spinner)
    private val listsAddFirstSecond: MutableList<Spinner> = mutableListOf()
    private val listsDimensions: MutableList<Spinner> = mutableListOf()

    // Текстовые поля для ввода чисел
    private val valuesFields: MutableList<EditText> = mutableListOf()
    private val valuesFieldsLayouts: MutableList<TextInputLayout> = mutableListOf()

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
            FragmentScope.SHOW_PHARMACY_SURFACE_FRAGMENT_SCOPE,
            named(FragmentScope.SHOW_PHARMACY_SURFACE_FRAGMENT_SCOPE)
        )
    }

    override fun onDetach() {
        // Удаление скоупа для данного фрагмента
        showPharmacySurfaceFragmentScope.close()
        super.onDetach()
    }
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализация текстовых полей
        initTextFields()
        // Инициализация списков
        initLists()
        // Инициализация кнопок навигации
        initNavigationButtons()
        //Инициализация кнопок
        initButtons()
        // Инициализация ViewModel
        initViewModel()
        // Настройка события обработки списков (должно быть в конце всех инициализаций)
        setActionsFieldsAndLists()
    }

    // Инициализация текстовых полей
    private fun initTextFields() {
        // Очистка списков
        valuesFieldsLayouts.clear()
        valuesFields.clear()
        // Сюда по порядку задаются числовые поля
        valuesFieldsLayouts.add(binding.pharmacyWeightTextinputlayout)
        valuesFields.add(binding.pharmacyWeightTextinputlayoutTextfield)
        // Настройка события изменения значений в полях ввода чисел
        valuesFields.forEach { field ->
            field.doOnTextChanged { _, _, _, _ ->
                viewModel.checkAreTheFieldsFilledIn(valuesFields.map { it.text.toString() })
            }
        }
    }

    // Инициализация списков
    private fun initLists() {
        // Очистка списков
        listsAddFirstSecond.clear()
        listsDimensions.clear()
        // Сюда нужно по порядку добавлять все существующие списки,
        // которые относятся к свойствам addFirst и addSecond,
        // т.е. в них нет дополняющей информации о размерности для поля ввода числа
        listsAddFirstSecond.add(binding.pharmacyTypeAnimalList)
        // Сюда нужно вносить списки с размерностью для числового поля
        listsDimensions.add(binding.pharmacyWeightDimensionList)
    }

    // Инициализация кнопок
    private fun initNavigationButtons() {
        binding.apply {
            navigationButtons.also {
                it[0] = this.pharmacyPreviousButtonContainer
                it[1] = this.pharmacyCalculateButton
            }
        }
        navigationButtons.forEachIndexed { index, button ->
            button?.setOnClickListener {
                when (index) {
                    0 -> viewModel.router.exit()
                    1 -> viewModel.router.navigateTo(viewModel.screens.pharmacySurfaceResultScreen())
                    else -> {
                         Toast.makeText(requireContext(), requireActivity().resources.getString(
                            R.string.error_button_is_not_assigned), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun initButtons() {
        pharmacyClearButtonContainer = binding.pharmacyClearButtonContainer
        pharmacyClearButtonContainer.setOnClickListener {
            listsAddFirstSecond.forEach {
                it.setSelection(0)
            }
            valuesFields.forEach {
                it.setText("")
            }
            listsDimensions.forEach {
                it.setSelection(0)
            }
            // Сохранение текущего состояния всех числовых полей и списков
            saveData()
        }
    }

    // Инициализация ViewModel
    private fun initViewModel() {
        val viewModel: PharmacySurfaceFragmentViewModel by showPharmacySurfaceFragmentScope.inject()
        this.viewModel = viewModel
        this.viewModel.subscribe().observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.checkEditTextFieldsLiveData.observe(viewLifecycleOwner) {
            binding.pharmacyCalculateButton.isEnabled = it
        }
        this.viewModel.getData()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                appState.screenData.let {
                    // Установка значений типа addFirst или addSecond
                    listsAddFirstSecond.forEachIndexed { index, addFirstSecond ->
                        if (it.listsAddFirstSecond.count() > index)
                            addFirstSecond.setSelection(it.listsAddFirstSecond[index])
                    }
                    // Установка численного значения поля
                    valuesFields.forEachIndexed { index, valueField ->
                        if (it.valueFields.count() > index)
                            valueField.setText(
                                if (it.valueFields[index].value > 0.0)
                                    "${it.valueFields[index].value}" else ""
                            )
                    }
                    // Установка размерности поля
                    listsDimensions.forEachIndexed { index, dimension ->
                        if (it.valueFields.count() > index)
                            dimension.setSelection(it.valueFields[index].dimension)
                    }
                }
            }
            is AppState.Loading -> {
                if (appState.progress != null) {
                } else {
                }
            }
            is AppState.Error -> {
                Toast.makeText(
                    requireContext(),
                    requireContext().getString(
                        R.string.error_appstate_not_loaded_for_fragment
                    ),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // Установка события при выборе элемента списка
    private fun setActionsFieldsAndLists() {
        listsAddFirstSecond.forEachIndexed { index, spinnerList ->
            spinnerList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?,
                    position: Int, id: Long,
                ) {
                    // Сохранение текущего состояния всех числовых полей и списков
                    saveData()
                    Toast.makeText(
                        this@PharmacySurfaceFragment.requireContext(),
                        "${spinnerList.selectedItem} selected", Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
    }

    // Сохранение текущего состояния всех числовых полей и списков
    private fun saveData() {
        Toast.makeText(requireContext(), "${listsAddFirstSecond[0].selectedItemPosition}", Toast.LENGTH_SHORT).show()
        viewModel.saveData(
            screenType,
            listsAddFirstSecond.convertListSpinnerToListInt(),
            valuesFields.convertListEditTextToListDouble(),
            listsDimensions.convertListSpinnerToListInt()
        )
    }
}