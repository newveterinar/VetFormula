package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.timer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseViewModelForNavigation
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TimerViewModel() : BaseViewModelForNavigation(), CoroutineScope {

    private var ticks: MutableList<Long> = mutableListOf()
    private var mTickInMinutes: MutableLiveData<Double> = MutableLiveData()

    private val mOnTimer = MutableLiveData<Boolean>()
    private val mSeconds = MutableLiveData<Int>()


    lateinit var job: Job


    var tickInMinutes: LiveData<Double> = mTickInMinutes
    var onTimer: LiveData<Boolean> = mOnTimer
    var second: LiveData<Int> = mSeconds

    fun startTimer() {
        if (mOnTimer.value == true) return
        ticks.clear()
        mSeconds.postValue(0)
        mOnTimer.postValue(true)
        countTimer()
    }

    fun addTick() {
        ticks.add(System.currentTimeMillis())
        updateTicks()
    }

    private fun updateTicks() {
        if (ticks.size > 1) {
            val totalTimeOverTicks = ticks[ticks.size - 1] - ticks[0]
            val totalTimeMinutes: Double = totalTimeOverTicks.toDouble() / 1000 / 60
            mTickInMinutes.postValue(ticks.size.toDouble() / totalTimeMinutes)
        }
    }

    fun resetTickCounter() {
        ticks.clear()
        mTickInMinutes.postValue(0.00)
    }

    fun stopTimer() {

        if (mOnTimer.value != true) return
        job.cancel()
        mOnTimer.postValue(false)
        updateTicks()
    }

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Main


    private fun countTimer() {
        job = launch {
            val timer = mOnTimer.value ?: false
            while (timer) {
                delay(1000)
                val time = mSeconds.value ?: 0
                mSeconds.postValue(time + 1)
                if (time + 1 == 60) {
                    stopTimer()
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }

    fun setManualTickValue(stringValue: String) {
        try {
            mTickInMinutes.postValue(0.00)
            val tickInt = stringValue.toInt()
            mSeconds.value?.let {
                val timerMinutes = it.toDouble() / 60
                if (it != 0) {
                    mTickInMinutes.postValue(tickInt.toDouble() / timerMinutes)
                }
            }

        } catch (e: NumberFormatException) {
            mTickInMinutes.postValue(0.00)
        }
    }
}