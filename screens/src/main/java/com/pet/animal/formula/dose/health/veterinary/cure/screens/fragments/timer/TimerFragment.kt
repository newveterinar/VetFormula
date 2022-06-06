package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.timer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentTimer2Binding


class TimerFragment : BaseFragment<FragmentTimer2Binding>(FragmentTimer2Binding::inflate) {

    private lateinit var startTimerButton: View
    private val navigationButtons = arrayOfNulls<View>(size = 2)

    companion object {
        fun newInstance(): TimerFragment = TimerFragment()
    }

    private lateinit var viewModel: TimerViewModel


    private fun initObservable(){
        viewModel.second.observe(viewLifecycleOwner) {sec->

            val secTo=viewModel.timerTo-sec

            val minutes: Int = secTo / 60
            val second: Int = secTo % 60
            val sSecond: String

            if (second < 10) {
                sSecond = "0$second"
            } else {
                sSecond = "$second"
            }

            binding.timerText.text = "$minutes:$sSecond"
        }

        viewModel.mute.observe(viewLifecycleOwner){
            if (it){
                binding.buttonMuteUnMute.text = "Unmute"
            } else {
                binding.buttonMuteUnMute.text = "Mute"
            }
        }

        viewModel.tickInMinutes.observe(viewLifecycleOwner) {
            val s: String = getString(R.string.tickCaption)
            val tickString = "$s.${it.format(2)}"
            binding.tickInMinutes.text = tickString
        }

        viewModel.timerHeartRate.observe(viewLifecycleOwner){
            val s:String = getString(R.string.tickCaption)
            val tickString = "$s.${it.format(2)}"
            binding.tickInMinutesTimer.text = tickString
        }

        viewModel.tickManual.observe(viewLifecycleOwner){
            val s:String = getString(R.string.tickCaption)
            val tickString = "$s.${it.format(2)}"
            binding.textViewHR.text = tickString
        }
    }

    private fun Double.format(digits: Int) = "%.${digits}f".format(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[TimerViewModel::class.java]

        initNavigationButtons()
        initButton()
        initObservable()
        viewModel.resetTimer()




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
                    0 -> viewModel.router.exit()
                    1 -> viewModel.router.navigateTo(viewModel.screens.aboutScreen())
                    else -> {
                        Toast.makeText(requireContext(), requireActivity().resources.getString(
                            R.string.error_button_is_not_assigned), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


    private fun initButton() {
        binding.buttonStartTimer.also { button ->
            button.setOnClickListener {
                    viewModel.startTimer()
            }
        }

        binding.buttonStopTimer.setOnClickListener{
            viewModel.stopTimer()
        }

        binding.buttonResetTimer.setOnClickListener{
            viewModel.resetTimer()
        }


        binding.touchPlace.setOnClickListener {
            viewModel.addTick()
        }

        binding.buttonResetTickCounter.setOnClickListener {
            viewModel.resetTickCounter()
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
            viewModel.plusOneMinutes()
        }
    }
}
