package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.timer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseFragment
import com.pet.animal.formula.dose.health.veterinary.cure.screens.R
import com.pet.animal.formula.dose.health.veterinary.cure.screens.databinding.FragmentTimerBinding

class TimerFragment : BaseFragment<FragmentTimerBinding>(FragmentTimerBinding::inflate) {

    private lateinit var startTimerButton:View

    companion object {
        fun newInstance():TimerFragment = TimerFragment()
    }

    private lateinit var viewModel: TimerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[TimerViewModel::class.java]
        //initBuuton()
        viewModel.second.observe(viewLifecycleOwner) {
            val minutes: Int = it / 60
            val second: Int = it % 60
        }

        viewModel.onTimer.observe(viewLifecycleOwner) {
            if (it) {
                (startTimerButton as Button).text = "Stop timer"
            } else {
                (startTimerButton as Button).text = "Stop timer"
            }
        }
    }


    private fun initBuuton() {
        startTimerButton = binding.startTimerButton.also{ button ->
            button.setOnClickListener{
                if (viewModel.onTimer.value==true){
                    viewModel.stopTimer()
                } else {
                    viewModel.startTimer()
                }
            }
        }
    }
}