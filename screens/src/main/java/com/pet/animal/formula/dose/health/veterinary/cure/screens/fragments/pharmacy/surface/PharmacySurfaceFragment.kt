package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface

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
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacySurfaceBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.convertListEditTextToListDouble
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.convertListEditTextToListString
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.convertListSpinnerToListInt
import com.pet.animal.formula.dose.health.veterinary.cure.utils.functions.stringToDouble
import com.pet.animal.formula.dose.health.veterinary.cure.utils.settings.SettingsImpl
import kotlinx.coroutines.delay
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

    // Текстовое поле для отображения общей информации о том, что нужно делать в данном окне
    private lateinit var helpInfoText: TextView

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
        // Настройка события завершения ввода числового значения
        valuesFields.forEachIndexed { index, field ->
            field.setOnKeyListener(object: View.OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                    // Переопределение события от нажатия на кнопку "Enter"
                    if ((event.action == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        // Удаление фокуса с текстового поля
                        field.clearFocus()
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
        listsAddFirstSecond.add(binding.pharmacyTypeAnimalList)
        // Сюда нужно вносить списки с размерностью для числового поля
        listsDimensions.add(binding.pharmacyWeightDimensionList)
    }

    // Инициализация навигационных кнопок
    private fun initNavigationButtons() {
        // Непосредственная инициализация навигационных кнопок на окне
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

    private fun initButtons() {
        pharmacyClearButtonContainer = binding.pharmacyClearButtonContainer.also { button ->
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
        val _viewModel: PharmacySurfaceFragmentViewModel by showPharmacySurfaceFragmentScope.inject()
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
        // Необходимо запустить для отображения ранее сохранённых значений в полях и списках окна
        viewModel.getData()
    }

    // Отображение изменения LiveData у viewModel
    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                appState.screenData.let {
                    if (!it.isGoToResultScreen) {
                        // Установка значений типа addFirst или addSecond
                        listsAddFirstSecond.forEachIndexed { index, addFirstSecond ->
                            if (it.listsAddFirstSecond.count() > index)
                                addFirstSecond.setSelection(it.listsAddFirstSecond[index])
                        }
                        // Установка строчного значения в поле
                        valuesFields.forEachIndexed { index, valueField ->
                            if (it.valueFields.count() > index)
                                valueField.setText(
                                    if (it.valueFields[index].value > 0.0)
                                        it.valueFields[index].stringValue else ""
                                )
                        }
                        // Установка размерности поля
                        listsDimensions.forEachIndexed { index, dimension ->
                            if (it.valueFields.count() > index)
                                dimension.setSelection(it.valueFields[index].dimension)
                        }
                    } else {
                        // Переход на экран с результатами расчётов
                        viewModel.router.navigateTo(viewModel.screens.pharmacySurfaceResultScreen())
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
            valuesFields.convertListEditTextToListDouble(),
            listsDimensions.convertListSpinnerToListInt(),
            isGoToResultScreen
        )
    }
}