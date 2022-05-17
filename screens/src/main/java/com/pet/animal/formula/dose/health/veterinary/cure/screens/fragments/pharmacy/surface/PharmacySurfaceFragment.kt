package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputLayout
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacySurfaceBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.SHOW_PHARMACY_SURFACE_FRAGMENT_SCOPE
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.convertListEditTextToListDouble
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.convertListSpinnerToListInt
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.stringToDouble
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent

class PharmacySurfaceFragment:
    BaseFragment<FragmentPharmacySurfaceBinding>(FragmentPharmacySurfaceBinding::inflate) {
    /** Задание переменных */ //region
    // Установка типа формулы для текущего окна
    private val screenType: ScreenType = ScreenType.PHARMACY_SURFACE
    // Задание навигационных кнопок
    private val navigationButtons = arrayOfNulls<View>(size = 3)
    // Обнуление значений во всех полях
    private lateinit var pharmacyClearButtonContainer: ConstraintLayout
    // ViewModel
    private lateinit var model: PharmacySurfaceFragmentViewModel
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
            SHOW_PHARMACY_SURFACE_FRAGMENT_SCOPE,
            named(SHOW_PHARMACY_SURFACE_FRAGMENT_SCOPE))
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
        // Инициализация кнопок
        initNavigationButtons()
        initButtons()
        // Инициализация ViewModel
        initViewModel()
        // Настройка события обработки списков (должно быть в конце всех инициализаций)
        setActionsFieldsAndLists()
    }

    // Инициализация текстовых полей
    private fun initTextFields() {
        // Очистка списков
        valuesFields.clear()
        valuesFieldsLayouts.clear()
        // Сюда по порядку задаются числовые поля
        valuesFields.add(binding.pharmacyWeightTextinputlayoutTextfield)
        valuesFieldsLayouts.add(binding.pharmacyWeightTextinputlayout)
        valuesFields.forEachIndexed { index, it ->
            it.setOnKeyListener(object: View.OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                    if ((event.action == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        // Удаление фокуса с текстового поля
                        it.clearFocus()
                        // Скрытие курсора
                        it.isCursorVisible = false
                        // Снятие ошибки
                        if (it.text.isNotEmpty())
                            valuesFieldsLayouts[index].isErrorEnabled = false
                        return true
                    }
                    if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_0)) {
                        // Добавление нового символа в текущее положение курсора
                        val start: Int = it.selectionStart
                        if (((stringToDouble(it.text.toString()) > 0.0) && (start > 0)) ||
                            ((it.text.toString().indexOf(".") > -1) && (start > 0)) ||
                            (it.text.isEmpty()))
                            it.text.insert(start, "0")
                        // Снятие ошибки
                        if (it.text.isNotEmpty())
                            valuesFieldsLayouts[index].isErrorEnabled = false
                        return true
                    }
                    if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_1)) {
                        // Добавление нового символа в текущее положение курсора
                        val start: Int = it.selectionStart
                        it.text.insert(start, "1")
                        // Снятие ошибки
                        if (it.text.isNotEmpty())
                            valuesFieldsLayouts[index].isErrorEnabled = false
                        return true
                    }
                    if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_2)) {
                        // Добавление нового символа в текущее положение курсора
                        val start: Int = it.selectionStart
                        it.text.insert(start, "2")
                        // Снятие ошибки
                        if (it.text.isNotEmpty())
                            valuesFieldsLayouts[index].isErrorEnabled = false
                        return true
                    }
                    if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_3)) {
                        // Добавление нового символа в текущее положение курсора
                        val start: Int = it.selectionStart
                        it.text.insert(start, "3")
                        // Снятие ошибки
                        if (it.text.isNotEmpty())
                            valuesFieldsLayouts[index].isErrorEnabled = false
                        return true
                    }
                    if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_4)) {
                        // Добавление нового символа в текущее положение курсора
                        val start: Int = it.selectionStart
                        it.text.insert(start, "4")
                        // Снятие ошибки
                        if (it.text.isNotEmpty())
                            valuesFieldsLayouts[index].isErrorEnabled = false
                        return true
                    }
                    if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_5)) {
                        // Добавление нового символа в текущее положение курсора
                        val start: Int = it.selectionStart
                        it.text.insert(start, "5")
                        // Снятие ошибки
                        if (it.text.isNotEmpty())
                            valuesFieldsLayouts[index].isErrorEnabled = false
                        return true
                    }
                    if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_6)) {
                        // Добавление нового символа в текущее положение курсора
                        val start: Int = it.selectionStart
                        it.text.insert(start, "6")
                        // Снятие ошибки
                        if (it.text.isNotEmpty())
                            valuesFieldsLayouts[index].isErrorEnabled = false
                        return true
                    }
                    if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_7)) {
                        // Добавление нового символа в текущее положение курсора
                        val start: Int = it.selectionStart
                        it.text.insert(start, "7")
                        // Снятие ошибки
                        if (it.text.isNotEmpty())
                            valuesFieldsLayouts[index].isErrorEnabled = false
                        return true
                    }
                    if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_8)) {
                        // Добавление нового символа в текущее положение курсора
                        val start: Int = it.selectionStart
                        it.text.insert(start, "8")
                        // Снятие ошибки
                        if (it.text.isNotEmpty())
                            valuesFieldsLayouts[index].isErrorEnabled = false
                        return true
                    }
                    if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_9)) {
                        // Добавление нового символа в текущее положение курсора
                        val start: Int = it.selectionStart
                        it.text.insert(start, "9")
                        // Снятие ошибки
                        if (it.text.isNotEmpty())
                            valuesFieldsLayouts[index].isErrorEnabled = false
                        return true
                    }
                    return false
                }
            })
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
                it[2] = this.pharmacyAboutButton
            }
        }
        navigationButtons.forEachIndexed { index, button ->
            button?.setOnClickListener {
                when (index) {
                    0 -> {
                        // Сохранение текущего состояния всех числовых полей и списков
                        saveData()
                        // Переход назад
                        model.router.exit()
                    }
                    1 -> {
                        // Проверка на заполненность всех числовых полей
                        var indexEmptyField: Int = -1
                        valuesFields.forEachIndexed { index, it ->
                            if ((it.text.isEmpty() || (stringToDouble(it.text.toString()) == 0.0))
                                && (indexEmptyField == -1)) {
                                // Сохранение индекса не заполненного числового поля
                                indexEmptyField = index
                                // Установка признака ошибки в класс типа TextInputLayout
                                valuesFieldsLayouts[index].isErrorEnabled = true
                                valuesFieldsLayouts[index].error = requireActivity().
                                    resources.getString(R.string.error_not_inserted_weight_animal)
                            }
                        }
                        // Переход на окно "PharmacySurfaceResult"
                        if (indexEmptyField == -1) {
                            // Сохранение текущего состояния всех числовых полей и списков
                            saveData()
                            // Переход на окно с результатом расчётов
                            model.router.navigateTo(model.screens.pharmacySurfaceResultScreen())
                        }
                    }
                    2 -> {
                        // Сохранение текущего состояния всех числовых полей и списков
                        saveData()
                        // Переход к окну "О приложении"
                        model.router.navigateTo(model.screens.aboutScreen())
                    }
                    else -> {
                        Toast.makeText(requireContext(), "Кнопка не назначена",
                            Toast.LENGTH_SHORT).show()
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
        model = viewModel
        model.subscribe().observe(viewLifecycleOwner){
            renderData(it)
        }
        model.getData()
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
                            valueField.setText(if (it.valueFields[index].value > 0.0)
                            "${it.valueFields[index].value}" else "")
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
                Toast.makeText(requireContext(),
                    requireContext().getString(R.string.error_appstate_not_loaded_for_fragment),
                    Toast.LENGTH_SHORT).show()
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
                    Toast.makeText(this@PharmacySurfaceFragment.requireContext(),
                        "selected", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
    }
    // Сохранение текущего состояния всех числовых полей и списков
    private fun saveData() {
        Toast.makeText(requireContext(), "${listsAddFirstSecond[0].selectedItemPosition}", Toast.LENGTH_SHORT).show()
        model.saveData(screenType,
            listsAddFirstSecond.convertListSpinnerToListInt(),
            valuesFields.convertListEditTextToListDouble(),
            listsDimensions.convertListSpinnerToListInt())
    }
}