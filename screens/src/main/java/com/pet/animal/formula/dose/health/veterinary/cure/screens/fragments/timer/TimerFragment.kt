package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.timer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentTimerBinding

class TimerFragment : BaseFragment<FragmentTimerBinding>(FragmentTimerBinding::inflate) {

    private lateinit var startTimerButton:View
    private val navigationButtons = arrayOfNulls<View>(size = 2)

    companion object {
        fun newInstance():TimerFragment = TimerFragment()
    }

    private lateinit var viewModel: TimerViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[TimerViewModel::class.java]

        initNavigationButtons()
        initButton()
        viewModel.second.observe(viewLifecycleOwner) {
            val minutes: Int = it / 60
            val second: Int = it % 60
            var sSecond:String

            if (second<10){
                sSecond = "0$second"
            } else {
                sSecond = "$second"
            }

            binding.timerText.text = "$minutes:$sSecond"
        }

        viewModel.onTimer.observe(viewLifecycleOwner) {
            if (it) {
                (startTimerButton as Button).text = getString(R.string.captionStopTimer)
            } else {
                (startTimerButton as Button).text = getString(R.string.captionStartTimer)
            }
        }

        fun Double.format(digits: Int) = "%.${digits}f".format(this)

        viewModel.tickInMinutes.observe(viewLifecycleOwner) {
            val s:String = getString(R.string.tickCaption)
            val tickString = "$s.${it.format(2)}"
            binding.tickInMinutes.text = tickString
        }
    }

    private fun initNavigationButtons() {
        binding.apply {
            navigationButtons.also {
                it[0] = this.timerPreviousButton
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
        startTimerButton = binding.startTimerButton.also{ button ->
            button.setOnClickListener{
                if (viewModel.onTimer.value==true){
                    viewModel.stopTimer()
                } else {
                    viewModel.startTimer()
                }
            }
        }

        binding.touchPlace.setOnClickListener{
            viewModel.addTick()
        }

        binding.buttonResetTickCounter.setOnClickListener{
            viewModel.resetTickCounter()
        }

        binding.inputManualCount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){
                if (viewModel.onTimer.value==true){
                    viewModel.stopTimer()
                }
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(et: Editable?) {
                et?.let{
                    viewModel.setManualTickValue(it.toString())
                }
            }
        })
    }
}