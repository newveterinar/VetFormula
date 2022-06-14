package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.timer

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentTimer2Binding
import com.pet.animal.formula.dose.health.veterinary.cure.utils.NUMBER_NAVIGATION_BUTTONS_ON_INPUT_DATA_SCREENS
import com.pet.animal.formula.dose.health.veterinary.cure.utils.NUMBER_SECONDS_IN_MINUTE
import com.pet.animal.formula.dose.health.veterinary.cure.utils.START_LABEL_TIMER
import com.pet.animal.formula.dose.health.veterinary.cure.utils.screens.FabAndSliderControl

class TimerFragment: BaseFragment<FragmentTimer2Binding>(FragmentTimer2Binding::inflate) {
    /** Задание переменных */ //region
    // Навигационные кнопки (для перехода на другие экраны)
    private val navigationButtons =
        arrayOfNulls<View>(size = NUMBER_NAVIGATION_BUTTONS_ON_INPUT_DATA_SCREENS)
    // ViewModel
    private lateinit var viewModel: TimerViewModel
    // Переменные для текстовых полей
    lateinit var labelManualCount: TextInputLayout
    lateinit var inputManualCount: TextInputEditText
    lateinit var textViewHR: TextView
    // newInstance для данного класса
    companion object {
        fun newInstance(): TimerFragment = TimerFragment()
    }
    //endregion

    @SuppressLint("SetTextI18n")
    private fun initObservable(){
        viewModel.second.observe(viewLifecycleOwner) { sec->

            val secTo=viewModel.timerTo-sec

            val minutes: Int = secTo / NUMBER_SECONDS_IN_MINUTE.toInt()
            val second: Int = secTo % NUMBER_SECONDS_IN_MINUTE.toInt()
            val sSecond: String

            if (second < 10) {
                sSecond = "0$second"
            } else {
                sSecond = "$second"
            }

            binding.timerText.text = "$minutes:$sSecond"
            if ((minutes == 0) && (second == 0)) {
                // Обнуление значения поля ЧСС
                inputManualCount.setText("")
                // Отображение поля для ввода ЧСС
                labelManualCount.visibility = View.VISIBLE
                inputManualCount.visibility = View.VISIBLE
                textViewHR.visibility = View.VISIBLE
                inputManualCount.isCursorVisible = true
                inputManualCount.setSelection(0,0)
            } else if (inputManualCount.visibility == View.VISIBLE) {
                // Скрытие поля для ввода ЧСС
                labelManualCount.visibility = View.INVISIBLE
                inputManualCount.visibility = View.INVISIBLE
                textViewHR.visibility = View.INVISIBLE
                inputManualCount.isCursorVisible = false
            }
        }

        viewModel.mute.observe(viewLifecycleOwner){
            if (it){
                binding.buttonMuteUnMute.text = getString(R.string.buttonMuteUnMute_unmute_text)
            } else {
                binding.buttonMuteUnMute.text = getString(R.string.buttonMuteUnMute_mute_text)
            }
        }

        viewModel.timerBDD.observe(viewLifecycleOwner){
            val s:String = getString(R.string.tickCaptionBDD)
            val tickString = "$s.${it.format(1)}"
            binding.tickInMinutesTimer.text = tickString
        }

        viewModel.tickManual.observe(viewLifecycleOwner){
            val s:String = getString(R.string.tickCaptionHeartRate)
            val tickString = "$s.${it.format(1)}"
            binding.textViewHR.text = tickString
        }
    }

    private fun Double.format(digits: Int) = "%.${digits}f".format(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[TimerViewModel::class.java]

        initTextFields()
        initNavigationButtons()
        initButton()
        initObservable()
        viewModel.resetTimer()
        val ma = (activity as FabAndSliderControl)
        ma.hideFab()
        ma.hideSlider()
    }

    private fun initTextFields() {
        labelManualCount = binding.labelManualCount
        inputManualCount = binding.inputManualCount
        textViewHR = binding.textViewHR
    }

    private fun initNavigationButtons() {
        binding.apply {
            navigationButtons.also {
                it[0] = this.timerPreviousButtonContainer
                it[1] = this.timerAboutButton
            }
        }

        navigationButtons.forEachIndexed { index, button ->
            button?.setOnClickListener {
                when (index) {
                    0 -> {
                        viewModel.router.exit()
                    }
                    1 -> viewModel.router.navigateTo(viewModel.screens.aboutTimer())
                    else -> {
                        Toast.makeText(requireContext(),
                            requireActivity().resources.getString(
                            R.string.error_button_is_not_assigned), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun initButton() {
        binding.buttonStartTimer.also { button ->
            button.setOnClickListener {
                // Обнуление таймера (каждый новый старт - это новые измерения, старые нужно удалить)
                viewModel.resetTimer()
                // Запуск таймера
                viewModel.startTimer()
            }
        }

        binding.buttonStopTimer.setOnClickListener{
            viewModel.stopTimer()
            // Обнуление значения поля ЧСС
            inputManualCount.setText("")
            // Отображение поля для ввода ЧСС
            labelManualCount.visibility = View.VISIBLE
            inputManualCount.visibility = View.VISIBLE
            textViewHR.visibility = View.VISIBLE
            inputManualCount.isCursorVisible = true
            inputManualCount.setSelection(0,0)
        }

        binding.buttonResetTimer.setOnClickListener{
            viewModel.resetTimer()
        }

        binding.touchPlace.setOnClickListener {
            viewModel.addTick()
        }

        binding.inputManualCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (viewModel.onTimer.value == true) {
                    viewModel.stopTimer()
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(et: Editable?) {
                et?.let {
                    viewModel.setManualTickValue(it.toString())
                }
            }
        })

        binding.buttonMuteUnMute.setOnClickListener {
            viewModel.changeMuteSound()
        }

        binding.buttonPlusMinute.setOnClickListener{
            if (!binding.timerText.text.equals(START_LABEL_TIMER)) viewModel.plusOneMinutes()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val ma = (activity as FabAndSliderControl)
        ma.showFab()
        ma.showSlider()
    }
}
