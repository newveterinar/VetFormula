package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.pharmacy.surface

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.github.terrakok.cicerone.Router
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentPharmacySurfaceBinding
import com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.navigator.AppScreensImpl
import com.pet.animal.formula.dose.health.veterinary.cure.utils.SHOW_PHARMACY_SURFACE_FRAGMENT_SCOPE
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import androidx.lifecycle.Observer
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppState
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.utils.ScreenType
import com.pet.animal.formula.dose.health.veterinary.cure.utils.convertListEditTextToListDouble
import com.pet.animal.formula.dose.health.veterinary.cure.utils.convertListSpinnerToListInt
import com.pet.animal.formula.dose.health.veterinary.cure.utils.resources.ResourcesProviderImpl
import org.koin.java.KoinJavaComponent

class PharmacySurfaceFragment:
    BaseFragment<FragmentPharmacySurfaceBinding>(FragmentPharmacySurfaceBinding::inflate) {
    /** Задание переменных */ //region
    // Установка типа формулы для текущего окна
    private val screenType: ScreenType = ScreenType.PHARMACY_SURFACE
    // Навигация
    private val screens: AppScreensImpl = KoinJavaComponent.getKoin().get()
    private val router: Router = KoinJavaComponent.getKoin().get()
    private lateinit var buttonToPharmacySurfaceScreen: ConstraintLayout
    private lateinit var buttonToPharmacySurfaceResultScreen: Button
    private lateinit var buttonToAboutScreen: ImageView
    // Обнуление значений во всех полях
    private lateinit var pharmacy_clear_button_container: ConstraintLayout
    // ViewModel
    private lateinit var model: PharmacySurfaceFragmentViewModel
    // ShowPharmacySurfaceFragmentScope
    private lateinit var showPharmacySurfaceFragmentScope: Scope
    private val resourcesProviderImpl: ResourcesProviderImpl = KoinJavaComponent.getKoin().get()
    // Задание интерфеса для ввода координат (только числа, десятичная точка)
    private val inputTypeCoordinatesInterface =
            InputType.TYPE_CLASS_NUMBER or // разрешить ввод числа
            InputType.TYPE_NUMBER_FLAG_DECIMAL // разрешить ввод десятичной точки
    private lateinit var firstNumeralField: EditText
    // Списки (Spinner)
    private var listsAddFirstSecond: MutableList<Spinner> = mutableListOf()
    private var listsDimensions: MutableList<Spinner> = mutableListOf()
    // Текстовые поля для ввода чисел
    private var valuesFields: MutableList<EditText> = mutableListOf()
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
        initButtons()
        // Настройка клавиатуры ввода для числовых полей
        initKeyboard()
        // Инициализация ViewModel
        initViewModel()
        // Настройка события обработки списков (должно быть в конце всех инициализаций)
        setActionsFieldsAndLists()
    }

    // Инициализация текстовых полей
    fun initTextFields() {
        // Сюда по порядку задаются числовые поля
        valuesFields.add(binding.pharmacyWeightTextinputlayoutTextfield)
    }

    // Инициализация списков
    fun initLists() {
        // Сюда нужно по порядку добавлять все существующие списки,
        // которые относятся к свойствам addFirst и addSecond,
        // т.е. в них нет дополняющей информации о размерности для поля ввода числа
        listsAddFirstSecond.add(binding.pharmacyTypeAnimalList)
        // Сюда нужно вносить списки с размерностью для числового поля
        listsDimensions.add(binding.pharmacyWeightDimensionList)
    }

    // Инициализация кнопок
    fun initButtons() {
        pharmacy_clear_button_container = binding.pharmacyClearButtonContainer
        pharmacy_clear_button_container.setOnClickListener {
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
            model.saveData(screenType,
                convertListSpinnerToListInt(listsAddFirstSecond),
                convertListEditTextToListDouble(valuesFields),
                convertListSpinnerToListInt(listsDimensions))
        }
        buttonToPharmacySurfaceScreen = binding.pharmacyPreviousButtonContainer
        buttonToPharmacySurfaceScreen.setOnClickListener {
            // Сохранение текущего состояния всех числовых полей и списков
            model.saveData(screenType,
                convertListSpinnerToListInt(listsAddFirstSecond),
                convertListEditTextToListDouble(valuesFields),
                convertListSpinnerToListInt(listsDimensions))
            // Переход на предыдущее окно
            requireActivity().onBackPressed()
        }
        buttonToPharmacySurfaceResultScreen = binding.pharmacyCalculateButton
        buttonToPharmacySurfaceResultScreen.setOnClickListener {
            router.navigateTo(screens.pharmacySurfaceResultScreen())
        }
        buttonToAboutScreen = binding.pharmacyAboutButton
        buttonToAboutScreen.setOnClickListener {
            router.navigateTo(screens.aboutScreen())
        }
    }
    // Настройка клавиатуры ввода для числовых полей
    fun initKeyboard() {
        firstNumeralField = binding.pharmacyWeightTextinputlayoutTextfield
        firstNumeralField.inputType = inputTypeCoordinatesInterface
    }

    // Инициализация ViewModel
    fun initViewModel() {
        val viewModel: PharmacySurfaceFragmentViewModel by showPharmacySurfaceFragmentScope.inject()
        model = viewModel
        model.subscribe().observe(viewLifecycleOwner, Observer<AppState> {
            renderData(it)
        })
        model.getData()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessPharmacySurface -> {
                appState.pharmacySurfaceScreenData.let {
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
                    resourcesProviderImpl.getString(
                        R.string.error_appstate_not_loaded_for_fragment),
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Установка события при выборе элемента списка
    private fun setActionsFieldsAndLists() {
        listsAddFirstSecond.forEachIndexed { index, spinnerList ->
            spinnerList.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?,
                                            position: Int, id: Long) {
                    // Сохранение текущего состояния всех числовых полей и списков
                    model.saveData(screenType,
                        convertListSpinnerToListInt(listsAddFirstSecond),
                        convertListEditTextToListDouble(valuesFields),
                        convertListSpinnerToListInt(listsDimensions))
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
    }
}