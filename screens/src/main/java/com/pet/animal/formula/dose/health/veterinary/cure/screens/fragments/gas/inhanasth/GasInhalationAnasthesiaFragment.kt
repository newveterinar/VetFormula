package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.gas.inhanasth

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
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentGasesAnihalationAnasthesiaBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import com.pet.animal.formula.dose.health.veterinary.cure.utils.NUMBER_NAVIGATION_BUTTONS_ON_INPUT_DATA_SCREENS
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.convertListEditTextToListDouble
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.convertListEditTextToListString
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.convertListSpinnerToListInt
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.stringToDouble
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class GasInhalationAnasthesiaFragment:
    BaseFragment<FragmentGasesAnihalationAnasthesiaBinding>(FragmentGasesAnihalationAnasthesiaBinding::inflate) {
    /** Задание переменных */ //region
    // Установка типа формулы для текущего окна
    private val screenType: ScreenType = ScreenType.GASES_INHALATION_ANESTHESIA
    // Навигационные кнопки (для перехода на другие экраны)
    private val navigationButtons = arrayOfNulls<View>(
        size = NUMBER_NAVIGATION_BUTTONS_ON_INPUT_DATA_SCREENS
    )
    // Обнуление значений во всех полях
    private lateinit var clearButton: ConstraintLayout
    // ViewModel
    private lateinit var viewModel: GasInhalationAnasthesiaFragmentViewModel
    // ShowPharmacySurfaceFragmentScope
    private lateinit var showGasInhalationAnasthesiaFragmentScope: Scope
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
        fun newInstance(): GasInhalationAnasthesiaFragment = GasInhalationAnasthesiaFragment()
    }
    //endregion

    /** Работа со Scope */ //region
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Задание Scope для данного фрагмента
        showGasInhalationAnasthesiaFragmentScope = KoinJavaComponent.getKoin().getOrCreateScope(
            FragmentScope.SHOW_GAS_INHALATION_ANASTHESIA_FRAGMENT_SCOPE,
            named(FragmentScope.SHOW_GAS_INHALATION_ANASTHESIA_FRAGMENT_SCOPE)
        )
    }
    override fun onDetach() {
        // Удаление скоупа для данного фрагмента
        showGasInhalationAnasthesiaFragmentScope.close()
        super.onDetach()
    }
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализация кнопок навигации
        initNavigationButtons()
        // Инициализация текстовых полей
        initTextFields()
        // Инициализация списков
        initLists()
        // Инициализация кнопок
        initButtons()
        // Инициализация ViewModel
        initViewModel()
        // Настройка события обработки списков (должно быть в конце всех инициализаций)
        setActionsFieldsAndLists()
        //Обзервер для подсказок
        setToastHintObserver()
    }

    private fun setToastHintObserver() {
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
        valuesFieldsLayouts.add(binding.gasesMiddleFlowTextinputlayout)
        valuesFieldsLayouts.add(binding.gasesMiddleAnesthesiaConcentrationTextinputlayout)
        valuesFieldsLayouts.add(binding.gasesDurationAnesthesiaTextinputlayout)
        // Сюда по порядку задаются числовые поля
        valuesFields.add(binding.gasesMiddleFlowTextinputlayoutTextfield)
        valuesFields.add(binding.gasesMiddleAnesthesiaConcentrationTextinputlayoutTextfield)
        valuesFields.add(binding.gasesDurationAnesthesiaTextinputlayoutTextfield)
        //Всплывающа подсказка при долгом нажатии
        showFiledHintOnLongClick(valuesFields)
        // Настройка события изменения значений в полях ввода чисел
        valuesFields.forEach { field ->
            field.doOnTextChanged { _, _, _, _ ->
                viewModel.checkAreTheFieldsFilledIn(valuesFields.map { it.text.toString() })
            }
        }
        // Настройка события завершения ввода числового значения
        valuesFields.forEachIndexed { index, field ->
            field.setOnKeyListener(object: View.OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                    // Переопределение события от нажатия на кнопку "Enter"
                    if ((event.action == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
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
                            if (!isErrorEnabled) binding.calculateButton.callOnClick()
                        } else {
                            // Установка признака ошибки на текущем числовом поле
                            valuesFieldsLayouts[index].isErrorEnabled = true
                            valuesFieldsLayouts[index].error = requireActivity().
                            resources.getString(R.string.error_not_inserted_weight_animal)
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
                            (field.text.isEmpty()))
                            field.text.insert(start, "0")
                        return true
                    }
                    // Передать ход другим слушателям
                    return false
                }
            })
        }
        // Инициализация текстового сообщения о том, что нужно делать на странице
        helpInfoText = binding.helpInfo
    }

    // Инициализация списков
    private fun initLists() {
        // Очистка списков
        listsAddFirstSecond.clear()
        listsDimensions.clear()
        // Сюда нужно по порядку добавлять все существующие списки,
        // которые относятся к свойствам addFirst и addSecond,
        // т.е. в них нет дополняющей информации о размерности для поля ввода числа
        listsAddFirstSecond.add(binding.gasesAnestheticsList)
        // Сюда нужно вносить списки с размерностью для числового поля
        listsDimensions.add(binding.gasesMiddleFlowDimensionList)
        listsDimensions.add(binding.gasesMiddleAnesthesiaConcentrationDimensionList)
        listsDimensions.add(binding.gasesDurationAnesthesiaDimensionList)
        showSpinnerHintOnLongClick(listsAddFirstSecond)
    }

    private fun showFiledHintOnLongClick(valuesFields: MutableList<EditText>) {
        valuesFields[0].setOnLongClickListener {
            setToastHint(getString(R.string.gases_inhalation_anasthesia_middle_flow_hint))
            true
        }
        valuesFields[1].setOnLongClickListener {
            setToastHint(getString(R.string.gases_inhalation_anasthesia_concentration_hint))
            true
        }
        valuesFields[2].setOnLongClickListener {
            setToastHint(getString(R.string.gases_inhalation_anasthesia_duration_anesthesia_hint))
            true
        }
    }

    private fun showSpinnerHintOnLongClick(listsAddFirstSecond: MutableList<Spinner>) {
        listsAddFirstSecond[0].setOnLongClickListener{
            setToastHint(getString(R.string.gases_inhalation_anasthesia_gases_anesthetics_hint))
            true
        }
    }

    private fun setToastHint(hint: String) {
        viewModel.setToastHint(getString(R.string.long_click_hint, hint))
    }

    // Инициализация навигационных кнопок
    private fun initNavigationButtons() {
        // Непосредственная инициализация навигационных кнопок на окне
        binding.apply {
            navigationButtons.also {
                it[0] = this.previousButtonContainer
                it[1] = this.calculateButton
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
                    1 -> {
                        saveData(true)
                    }
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
        clearButton = binding.clearButtonContainer.also { button ->
            button.setOnClickListener {
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
                saveData(false)
            }
        }
    }

    // Инициализация ViewModel
    private fun initViewModel() {
        val _viewModel: GasInhalationAnasthesiaFragmentViewModel
            by showGasInhalationAnasthesiaFragmentScope.inject()
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
            binding.calculateButton.isEnabled = it
        }
        // Отображение ранее сохранённых значений в полях и списках окна
        viewModel.getData()
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
                        viewModel.router.navigateTo(viewModel.screens.gasesInhalationAnasthesiaResultScreen())
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
                    requireContext(), requireContext().getString(
                        R.string.error_appstate_not_loaded_for_fragment
                    ),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    // Установка события при выборе элементов списков
    private fun setActionsFieldsAndLists() {
        listsAddFirstSecond.forEachIndexed { index, spinnerList ->
            spinnerList.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
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
            spinnerList.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
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