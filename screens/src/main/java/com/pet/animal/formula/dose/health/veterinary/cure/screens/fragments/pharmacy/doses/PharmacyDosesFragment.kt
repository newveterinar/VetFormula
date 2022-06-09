package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.doses

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputLayout
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacyDosesBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.DIMENSION_MEQ_POSITION
import com.pet.animal.formula.dose.health.veterinary.cure.utils.DIMENSION_U_POSITION
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.convertListEditTextToListDouble
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.convertListEditTextToListString
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.convertListSpinnerToListInt
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.stringToDouble
import org.koin.android.ext.android.getKoin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class PharmacyDosesFragment :
    BaseFragment<FragmentPharmacyDosesBinding>(FragmentPharmacyDosesBinding::inflate) {

    /** Задание переменных */ //region
    // Установка типа формулы для текущего окна
    private val screenType: ScreenType = ScreenType.PHARMACY_DOSES

    // Навигационные кнопки (для перехода на другие экраны)
    private val navigationButtons = arrayOfNulls<View>(size = 3)

    // Обнуление значений во всех полях
    private lateinit var clearButton: ConstraintLayout

    // ViewModel
    private lateinit var viewModel: PharmacyDosesFragmentViewModel
    private lateinit var showPharmacyDoseFragmentScope: Scope

    // Списки (Spinner)
    private val listsAddFirstSecond: MutableList<Spinner> = mutableListOf()
    private val listsDimensions: MutableList<Spinner> = mutableListOf()

    // Текстовые поля для ввода чисел
    private val valuesFields: MutableList<EditText> = mutableListOf()
    private val valuesFieldsLayouts: MutableList<TextInputLayout> = mutableListOf()

    // Текстовое поле для отображения общей информации о том, что нужно делать в данном окне
    private lateinit var helpInfoText: TextView

    // newInstance для данного класса
    companion object {
        fun newInstance(): PharmacyDosesFragment = PharmacyDosesFragment()
    }
    //endregion

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Задание Scope для данного фрагмента
        showPharmacyDoseFragmentScope =
            getKoin().getOrCreateScope(
                FragmentScope.SHOW_PHARMACY_DOSES_FRAGMENT_SCOPE,
                named(FragmentScope.SHOW_PHARMACY_DOSES_FRAGMENT_SCOPE)
            )
    }

    override fun onDetach() {
        // Удаление скоупа для данного фрагмента
        showPharmacyDoseFragmentScope.close()
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализация текстовых полей
        initTextFields()
        // Инициализация списков
        initLists()
        // Инициализация кнопок навигации
        initNavigationButtons()
        // Инициализация кнопок
        initButtons()
        // Инициализация ViewModel
        initViewModel()
        // Настройка события обработки списков (должно быть в конце всех инициализаций)
        setActionsFieldsAndLists()
        //Обзервер для подсказок
        showToastHint()
    }

    private fun showToastHint() {
        viewModel.toastHint.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

    // Инициализация текстовых полей
    private fun initTextFields() {
        // Очистка списков
        valuesFieldsLayouts.clear()
        valuesFields.clear()
        // Сюда по порядку задаются TextInputLayout числовых полей
        valuesFieldsLayouts.add(binding.pharmacyWeightTextinputlayout)
        valuesFieldsLayouts.add(binding.pharmacyDoseTextinputlayout)
        valuesFieldsLayouts.add(binding.pharmacyConcentrationTextinputlayout)
        // Сюда по порядку задаются числовые поля
        valuesFields.add(binding.pharmacyWeightTextinputlayoutTextfield)
        valuesFields.add(binding.pharmacyDoseTextinputlayoutTextfield)
        valuesFields.add(binding.pharmacyConcentrationTextinputlayoutTextfield)
        showFieldHintOnLongClick(valuesFields)
        // Настройка события изменения значений в полях ввода чисел
        valuesFields.forEach { field ->
            field.doOnTextChanged { _, _, _, _ ->
                viewModel.checkAreTheFieldsFilledIn(valuesFields.map { it.text.toString() })
            }
        }
        // Настройка события завершения ввода числового значения
        valuesFields.forEachIndexed { index, field ->
            field.setOnKeyListener(object : View.OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                    // Переопределение события от нажатия на кнопку "Enter"
                    if ((event.action == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)
                    ) {
                        // Скрытие курсора
                        field.isCursorVisible = false
                        // Снятие ошибки
                        if (stringToDouble(field.text.toString()) > 0.0) {
                            // Сохранение текущего состояния всех числовых полей и списков
                            saveData(false)
                            // Снятие признака ошибки с текущего числового поля
                            valuesFieldsLayouts[index].isErrorEnabled = false
                            // Скрытие сообщения с общей информацией о том, что делать в данном окне
                            helpInfoText.visibility = View.INVISIBLE
                            // Нажатие на кнопку "Расчитать", если сняты признаки ошибки
                            // со всех числовых полей
                            var isErrorEnabled: Boolean = false
                            valuesFields.forEach {
                                if (stringToDouble(it.text.toString()) <= 0.0) isErrorEnabled = true
                            }
                            if (!isErrorEnabled) binding.pharmacyCalculateButton.callOnClick()
                        } else {
                            // Установка признака ошибки на текущем числовом поле
                            valuesFieldsLayouts[index].isErrorEnabled = true
                            valuesFieldsLayouts[index].error =
                                requireActivity().resources.getString(R.string.error_not_inserted_weight_animal)
                            // Отображение сообщения с общей информацией о том,
                            // что делать в данном окне
                            helpInfoText.visibility = View.VISIBLE
                        }
                        return true
                    }
                    // Переопределение события от нажатия кнопки "0"
                    if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_0)) {
                        // Добавление нового символа в текущее положение курсора
                        val start: Int = field.selectionStart
                        if (((stringToDouble(field.text.toString()) > 0.0) && (start > 0)) ||
                            ((field.text.toString().indexOf(".") > -1) && (start > 0)) ||
                            (field.text.isEmpty())
                        )
                            field.text.insert(start, "0")
                        return true
                    }
                    // Передать ход другим слушателям
                    return false
                }
            })
        }
        // Инициализация текстового сообщения о том, что нужно делать на странице
        helpInfoText = binding.pharmacyHelpInfo
    }

    // Инициализация списков
    private fun initLists() {
        // Очистка списков
        listsAddFirstSecond.clear()
        listsDimensions.clear()
        // Сюда нужно по порядку добавлять все существующие списки,
        // которые относятся к свойствам addFirst и addSecond,
        // т.е. в них нет дополняющей информации о размерности для поля ввода числа
        // !!! В данном окне нет списков addFirst и addSecond, поэтому заносить
        // в listsAddFirstSecond нечего, но переменная listsAddFirstSecond должна быть
        // Сюда нужно вносить списки с размерностью для числового поля
        listsDimensions.add(binding.pharmacyWeightDimensionList)
        listsDimensions.add(binding.pharmacyDoseDimensionList)
        listsDimensions.add(binding.pharmacyConcentrationDimensionList)
    }


    private fun showFieldHintOnLongClick(valuesFields: MutableList<EditText>) {
        for (field in 0 until valuesFields.size) {
            valuesFields[field].setOnLongClickListener {
                when (field) {
                    0 -> {
                        setToastHint(getString(R.string.pharmacy_dose_animal_weight_description))
                        true
                    }
                    1 -> {
                        setToastHint(getString(R.string.pharmacy_dose_dose_description))
                        true
                    }
                    2 -> {
                        setToastHint(getString(R.string.pharmacy_dose_drug_concentration_description))
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
        }
    }

    private fun setToastHint(hint: String) {
        viewModel.setToastHint(getString(R.string.long_click_hint,
            hint))
    }

    // Инициализация навигационных кнопок
    private fun initNavigationButtons() {
        binding.apply {
            navigationButtons.also {
                it[0] = this.pharmacyPreviousButtonContainer
                it[1] = this.pharmacyCalculateButton
            }
        }
        // Настройка события на клик по каждой кнопке
        navigationButtons.forEachIndexed { index, button ->
            button?.setOnClickListener {
                when (index) {
                    0 -> {
                        saveData(false)
                        viewModel.router.exit()
                    }
                    1 -> saveData(true)
                    else -> {
                        Toast.makeText(requireContext(),
                            requireActivity().resources.getString(
                                R.string.error_button_is_not_assigned), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    // Инициализация кнопок
    private fun initButtons() {
        clearButton = binding.pharmacyClearButtonContainer.also { button ->
            button.setOnClickListener {
                listsAddFirstSecond.forEach { it.setSelection(0) }
                valuesFields.forEach { it.setText("") }
                listsDimensions.forEach { it.setSelection(0) }
                // Сохранение текущего состояния всех числовых полей и списков
                saveData(false)
            }
        }
    }

    // Инициализация ViewModel
    private fun initViewModel() {
        val _viewModel: PharmacyDosesFragmentViewModel by showPharmacyDoseFragmentScope.inject()
        viewModel = _viewModel
        // Отображение текущих значений числовых поле и списков
        viewModel.subscribe().observe(viewLifecycleOwner) {
            renderData(it)
        }
        // Разблокировка кнопки при заполнении всех числовых полей
        viewModel.checkEditTextFieldsLiveData.observe(viewLifecycleOwner) {
            // Снятие ошибок со всех полей
            if (it == true) {
                valuesFieldsLayouts.forEach { field ->
                    // Снятие признака ошибки с текущего числового поля
                    field.isErrorEnabled = false
                }
                // Скрытие сообщения с общей информацией о том, что делать в данном окне
                helpInfoText.visibility = View.INVISIBLE
            } else {
                // Отображение сообщения с общей информацией о том, что делать в данном окне
                helpInfoText.visibility = View.VISIBLE
            }
            // Разблокировка или блоикировка кнопки "Рассчитать"
            binding.pharmacyCalculateButton.isEnabled = it
        }
        // Отображение ранее сохранённых значений в полях и списках окна
        viewModel.getData()
    }

    // Установка события при выборе элементов списков
    private fun setActionsFieldsAndLists() {
        listsAddFirstSecond.forEachIndexed { index, spinnerList ->
            spinnerList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                var selectCounter: Int = 0
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?,
                    position: Int, id: Long,
                ) {
                    // Сохранение текущего состояния всех числовых полей и списков
                    if (selectCounter++ > 0) saveData(false)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }
        listsDimensions.forEachIndexed { index, spinnerList ->
            spinnerList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                var selectCounter: Int = 0
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?,
                    position: Int, id: Long,
                ) {
                    // Сохранение текущего состояния всех числовых полей и списков
                    if (selectCounter++ > 0) {
                        // Синхронное выделение mEq или U в поле Formulation с полем Dose
                        if ((index == 1) && ((position == DIMENSION_MEQ_POSITION) ||
                                    (position == DIMENSION_U_POSITION))
                        ) {
                            listsDimensions[2].setSelection(position)
                        }
                        // Синхронное выделение mEq или U в поле Dose с полем Formulation
                        if ((index == 2) && ((position == DIMENSION_MEQ_POSITION) ||
                                    (position == DIMENSION_U_POSITION))
                        ) {
                            listsDimensions[1].setSelection(position)
                        }
                        // Синхронное снятие выделения mEq или U в поле Formulation с полем Dose
                        if ((index == 1) && (position != DIMENSION_MEQ_POSITION) &&
                            (position != DIMENSION_U_POSITION) &&
                            ((listsDimensions[2].selectedItemPosition == DIMENSION_MEQ_POSITION) ||
                                    (listsDimensions[2].selectedItemPosition == DIMENSION_U_POSITION))
                        ) {
                            listsDimensions[2].setSelection(0)
                        }
                        // Синхронное снятие выделения mEq или U в поле Dose с полем Formulation
                        if ((index == 2) && (position != DIMENSION_MEQ_POSITION) &&
                            (position != DIMENSION_U_POSITION) &&
                            ((listsDimensions[1].selectedItemPosition == DIMENSION_MEQ_POSITION) ||
                                    (listsDimensions[1].selectedItemPosition == DIMENSION_U_POSITION))
                        ) {
                            listsDimensions[1].setSelection(0)
                        }
                        // Сохранение состояния
                        saveData(false)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }
    }

    // Отображение изменения LiveData у viewModel
    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                appState.screenData.let {
                    if (!it.isGoToResultScreen) {
                        if (it.screenTypeIndex == screenType.ordinal) {
                            // Установка значений типа addFirst или addSecond
                            listsAddFirstSecond.forEachIndexed { index, addFirstSecond ->
                                if (it.listsAddFirstSecond.count() > index)
                                    addFirstSecond.setSelection(it.listsAddFirstSecond[index])
                            }
                            // Установка строчных значений в числовые поля
                            valuesFields.forEachIndexed { index, valueField ->
                                if (it.valueFields.count() > index)
                                    valueField.setText(
                                        if (it.valueFields[index].value > 0.0)
                                            it.valueFields[index].stringValue else ""
                                    )
                            }
                            // Установка размерности для числовых полей
                            listsDimensions.forEachIndexed { index, dimension ->
                                if (it.valueFields.count() > index)
                                    dimension.setSelection(it.valueFields[index].dimension)
                            }
                        }
                    } else {
                        // Переход на экран с результатами расчётов
                        viewModel.router.navigateTo(viewModel.screens.pharmacyDosesResultScreen())
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
                    ), Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // Сохранение текущего состояния всех числовых полей и списков
    private fun saveData(isGoToResultScreen: Boolean) {
        viewModel.saveData(
            screenType,
            listsAddFirstSecond.convertListSpinnerToListInt(),
            valuesFields.convertListEditTextToListString(),
            valuesFields.convertListEditTextToListDouble(listsDimensions, null),
            listsDimensions.convertListSpinnerToListInt(),
            isGoToResultScreen
        )
    }
}