package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.calculator.keyboard

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.model.calculator.CalcConstants
import com.pet.animal.formula.dose.health.veterinary.cure.model.calculator.CalcConstants.ACTIONS
import com.pet.animal.formula.dose.health.veterinary.cure.model.calculator.CalcConstants.FUNCTIONS
import com.pet.animal.formula.dose.health.veterinary.cure.model.screeendata.AppStateCalcKeyboard
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentCalculatorKeyboardBinding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.FragmentScope
import com.pet.animal.formula.dose.health.veterinary.cure.utils.NUMBER_NAVIGATION_BUTTONS_ON_OUTPUT_DATA_SCREENS
import com.pet.animal.formula.dose.health.veterinary.cure.utils.settings.SettingsImpl
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.java.KoinJavaComponent


class CalculatorKeyboardFragment:
    BaseFragment<FragmentCalculatorKeyboardBinding>(FragmentCalculatorKeyboardBinding::inflate),
    View.OnClickListener {
    /** Задание переменных */ //region
    // Навигация
    private val navigationButtons =
        arrayOfNulls<View>(size = NUMBER_NAVIGATION_BUTTONS_ON_OUTPUT_DATA_SCREENS)
    // ViewModel
    private lateinit var viewModel: CalculatorKeyboardFragmentViewModel
    // ShowCalculatorKeyboardFragmentScope
    private lateinit var showCalculatorKeyboardFragmentScope: Scope
    // Текстовые поля ввода и вывода данных калькулятора
    lateinit var outputResultText: TextView
    lateinit var inputedHistoryText: TextView
    // Кнопки калькулятора
    lateinit var button_0: Button
    lateinit var button_1: Button
    lateinit var button_2: Button
    lateinit var button_3: Button
    lateinit var button_4: Button
    lateinit var button_5: Button
    lateinit var button_6: Button
    lateinit var button_7: Button
    lateinit var button_8: Button
    lateinit var button_9: Button
    lateinit var button_equal: Button
    lateinit var button_zapitay: Button
    lateinit var button_zapitay_on_off: Button
    lateinit var button_bracket_close: Button
    lateinit var button_backspace: Button
    lateinit var button_backspace_one: Button
    lateinit var button_backspace_two: Button
    lateinit var button_bracket_open: Button
    lateinit var button_divide: Button
    lateinit var button_minus: Button
    lateinit var button_multiply: Button
    lateinit var button_percent: Button
    lateinit var button_plus: Button
    lateinit var button_plus_minus: Button
    lateinit var button_sqrt: Button
    lateinit var button_stepen: Button
    // SettingsImpl
    private val settings: SettingsImpl = KoinJavaComponent.getKoin().get()
    // Параметры для изменения радиуса расположения кнопок калькулятора
    private var koeff_DP: Float = 0f
    private var calculatorRadiusButtons: Int = settings.getCalculatorRadiusButtons()
    // newInstance для данного класса
    companion object {
        fun newInstance(): CalculatorKeyboardFragment = CalculatorKeyboardFragment()
    }
    //endregion

    /** Работа со Scope */ //region
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Задание Scope для данного фрагмента
        showCalculatorKeyboardFragmentScope = KoinJavaComponent.getKoin().getOrCreateScope(
            FragmentScope.SHOW_CALCULATOR_KEYBOARD_FRAGMENT_SCOPE,
            named(FragmentScope.SHOW_CALCULATOR_KEYBOARD_FRAGMENT_SCOPE)
        )
    }
    override fun onDetach() {
        // Удаление скоупа для данного фрагмента
        showCalculatorKeyboardFragmentScope.close()
        super.onDetach()
    }
    //endregion

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Инициализация кнопок
        initNavigationButton()
        // Получение значения коэффициента экрана для адаптации радиуса кнопок калькулятора
        koeff_DP = resources.displayMetrics.density
        // Инициализация кнопок
        initCalculatorButtons()
        // Инициализация элементов для вывода входящей и результирующей информации
        initTextFields()
        // Инициализация ViewModel
        initViewModel()
        // Установка обновлённого значения радиуса окружности кнопок
        setNewRadiusButtons(calculatorRadiusButtons * koeff_DP.toInt());
    }

    // Инициализация кнопок
    private fun initNavigationButton() {
        binding.apply {
            navigationButtons.also {
                it[0] = this.previousButtonContainer
            }
        }
        navigationButtons.forEachIndexed { index, button ->
            button?.setOnClickListener {
                when (index) {
                    0 -> viewModel.router.exit()
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
    private fun initCalculatorButtons() {
        // Установка кнопок с числами
        button_0 = binding.zero
        button_0.setOnClickListener(this)
        button_1 = binding.one
        button_1.setOnClickListener(this)
        button_2 = binding.two
        button_2.setOnClickListener(this)
        button_3 = binding.three
        button_3.setOnClickListener(this)
        button_4 = binding.four
        button_4.setOnClickListener(this)
        button_5 = binding.five
        button_5.setOnClickListener(this)
        button_6 = binding.six
        button_6.setOnClickListener(this)
        button_7 = binding.seven
        button_7.setOnClickListener(this)
        button_8 = binding.eight
        button_8.setOnClickListener(this)
        button_9 = binding.nine
        button_9.setOnClickListener(this)

        // Установка кнопок с действиями
        button_equal = binding.equal
        button_equal.setOnClickListener(this)
        button_zapitay = binding.zapitay
        button_zapitay.setOnClickListener(this)
        button_zapitay_on_off = binding.zapitayOnOff
        button_zapitay_on_off.setOnClickListener(this)
        button_bracket_close = binding.bracketClose
        button_bracket_close.setOnClickListener(this)
        button_backspace = binding.backspace
        button_backspace.setOnClickListener(this)
        button_backspace_one = binding.backspaceOne
        button_backspace_one.setOnClickListener(this)
        button_backspace_two = binding.backspaceTwo
        button_backspace_two.setOnClickListener(this)
        button_bracket_open = binding.bracketOpen
        button_bracket_open.setOnClickListener(this)
        button_divide = binding.divide
        button_divide.setOnClickListener(this)
        button_minus = binding.minus
        button_minus.setOnClickListener(this)
        button_multiply = binding.multiply
        button_multiply.setOnClickListener(this)
        button_percent = binding.percent
        button_percent.setOnClickListener(this)
        button_plus = binding.plus
        button_plus.setOnClickListener(this)
        button_plus_minus = binding.plusMinus
        button_plus_minus.setOnClickListener(this)
        button_sqrt = binding.sqrt
        button_sqrt.setOnClickListener(this)
        button_stepen = binding.stepen
        button_stepen.setOnClickListener(this)
    }

    // Установщика оработчиков нажатий на кнопки калькулятора
    override fun onClick(p0: View?) {
        p0?.let {
            if (it.id == button_0.id) {
                viewModel.addNumeral(0)
            } else if (it.id == button_1.id) {
                viewModel.addNumeral(1)
            } else if (it.id == button_2.id) {
                viewModel.addNumeral(2)
            } else if (it.id == button_3.id) {
                viewModel.addNumeral(3)
            } else if (it.id == button_4.id) {
                viewModel.addNumeral(4)
            } else if (it.id == button_5.id) {
                viewModel.addNumeral(5)
            } else if (it.id == button_6.id) {
                viewModel.addNumeral(6)
            } else if (it.id == button_7.id) {
                viewModel.addNumeral(7)
            } else if (it.id == button_8.id) {
                viewModel.addNumeral(8)
            } else if (it.id == button_9.id) {
                viewModel.addNumeral(9)
            } else if (it.id == button_equal.id) {
                viewModel.setEqual()
            } else if (it.id == button_zapitay.id) {
                viewModel.setCurZapitay()
            } else if (it.id == button_zapitay_on_off.id) {
                viewModel.setCurZapitay()
            } else if (it.id == button_bracket_open.id) {
                viewModel.setBracketOpen()
            } else if (it.id == button_bracket_close.id) {
                viewModel.setBracketClose()
            } else if (it.id == button_backspace.id) {
                viewModel.clearAll()
            } else if (it.id == button_backspace_one.id) {
                viewModel.clearOne()
            } else if (it.id == button_backspace_two.id) {
                viewModel.clearTwo()
            } else if (it.id == button_divide.id) {
                viewModel.setNewAction(ACTIONS.ACT_DIV)
            } else if (it.id == button_minus.id) {
                viewModel.setNewAction(ACTIONS.ACT_MINUS)
            } else if (it.id == button_multiply.id) {
                viewModel.setNewAction(ACTIONS.ACT_MULTY)
            } else if (it.id == button_plus.id) {
                viewModel.setNewAction(ACTIONS.ACT_PLUS)
            } else if (it.id == button_percent.id) {
                // Задаётся универсальное значение ACT_PERS_MULTY
                // и оно уточняется в методе setNewAction
                viewModel.setNewAction(ACTIONS.ACT_PERS_MULTY)
            } else if (it.id == button_plus_minus.id) {
                viewModel.changeSign()
            } else if (it.id == button_stepen.id) {
                viewModel.setNewAction(ACTIONS.ACT_STEP)
            } else if (it.id == button_sqrt.id) {
                viewModel.setNewFunction(FUNCTIONS.FUNC_SQRT)
            }
            buttonZapitayChange()
        }
    }

    // Инициализация текстовых полей
    private fun initTextFields() {
        outputResultText = binding.result
        inputedHistoryText = binding.inputedHistoryText
    }

    // Отобразить индикатор ввода вещественного числа
    private fun buttonZapitayChange() {
        if (!viewModel.getPressedZapitay()) {
            button_zapitay.visibility = View.VISIBLE
            button_zapitay_on_off.visibility = View.INVISIBLE
        } else {
            button_zapitay.visibility = View.INVISIBLE
            button_zapitay_on_off.visibility = View.VISIBLE
        }
    }

    // Инициализация ViewModel
    private fun initViewModel() {
        val _viewModel: CalculatorKeyboardFragmentViewModel by
        showCalculatorKeyboardFragmentScope.inject()
        viewModel = _viewModel
        // Отображение текущих значений числовых поле и списков
        viewModel.subscribe().observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    // Отображение изменения LiveData у viewModel
    private fun renderData(appStateCalcKeyboard: AppStateCalcKeyboard) {
        when (appStateCalcKeyboard) {
            is AppStateCalcKeyboard.Success -> {
                if ((appStateCalcKeyboard.calculatorKeyboardData.isInputedCalcDates) &&
                    (appStateCalcKeyboard.calculatorKeyboardData.errorInCalculator ==
                    CalcConstants.ERRORS.NO)) {
                    Toast.makeText(requireContext(), appStateCalcKeyboard.calculatorKeyboardData.inputedCalcDates, Toast.LENGTH_SHORT).show()
                    inputedHistoryText.text =
                        appStateCalcKeyboard.calculatorKeyboardData.inputedCalcDates
                }
                if ((appStateCalcKeyboard.calculatorKeyboardData.isOutputedCalcDates) &&
                    (appStateCalcKeyboard.calculatorKeyboardData.errorInCalculator ==
                    CalcConstants.ERRORS.NO)) {
                    outputResultText.text =
                        appStateCalcKeyboard.calculatorKeyboardData.outputedCalcDates
                }
                if ((!appStateCalcKeyboard.calculatorKeyboardData.isOutputedCalcDates) &&
                    (!appStateCalcKeyboard.calculatorKeyboardData.isOutputedCalcDates)) {
                    when (appStateCalcKeyboard.calculatorKeyboardData.errorInCalculator) {
                        CalcConstants.ERRORS.SQRT_MINUS -> {
                            // Ошибка: Подкоренное значение меньше нуля
                            Toast.makeText(
                                requireContext(), resources.getString(
                                    R.string.error_undersquare_low_zero
                                ), Toast.LENGTH_SHORT
                            ).show()
                        }
                        CalcConstants.ERRORS.BRACKET_DISBALANCE -> {
                            // Ошибка: Количество открытых скобок
                            // и закрытых скобок не равно друг другу
                            Toast.makeText(
                                requireContext(), resources.getString(
                                    R.string.error_different_number_brackets
                                ), Toast.LENGTH_SHORT
                            ).show()
                        }
                        CalcConstants.ERRORS.ZERO_DIVIDE -> {
                            // Ошибка: Деление на ноль
                            Toast.makeText(
                                requireContext(), resources.getString(
                                    R.string.error_divide_on_zero
                                ), Toast.LENGTH_SHORT
                            ).show()
                        }
                        CalcConstants.ERRORS.BRACKETS_EMPTY -> {
                            // Ошибка: Внутри скобок отсутствует число
                            Toast.makeText(
                                requireContext(), resources.getString(
                                    R.string.error_inside_brackets_empty
                                ), Toast.LENGTH_SHORT
                            ).show()
                        }
                        else -> {/* Ошибок не обнаружено */}
                    }
                }
            }
            is AppStateCalcKeyboard.Loading -> {
                if (appStateCalcKeyboard.progress != null) {
                } else {
                }
            }
            is AppStateCalcKeyboard.Error -> {
                Toast.makeText(requireContext(), requireContext().getString(
                    R.string.error_appstate_not_loaded_for_fragment), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setNewRadiusButtons(newRadius: Int) {
        // Смена значения поля в constraintLayout
        val constraintLayout: ConstraintLayout = binding.calculatorContainer
        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)
        constraintSet.constrainCircle(R.id.zero, R.id.result, newRadius, 0f)
        constraintSet.constrainCircle(R.id.one, R.id.result, newRadius, 30f)
        constraintSet.constrainCircle(R.id.two, R.id.result, newRadius, 60f)
        constraintSet.constrainCircle(R.id.three, R.id.result, newRadius, 90f)
        constraintSet.constrainCircle(R.id.four, R.id.result, newRadius, 120f)
        constraintSet.constrainCircle(R.id.five, R.id.result, newRadius, 150f)
        constraintSet.constrainCircle(R.id.six, R.id.result, newRadius, 180f)
        constraintSet.constrainCircle(R.id.seven, R.id.result, newRadius, 210f)
        constraintSet.constrainCircle(R.id.eight, R.id.result, newRadius, 240f)
        constraintSet.constrainCircle(R.id.nine, R.id.result, newRadius, 270f)
        constraintSet.constrainCircle(R.id.divide, R.id.result, newRadius, 300f)
        constraintSet.constrainCircle(R.id.minus, R.id.result, newRadius, 330f)
        constraintSet.constrainCircle(R.id.minus, R.id.result, newRadius, 330f)
        constraintSet.applyTo(constraintLayout)
    }
}