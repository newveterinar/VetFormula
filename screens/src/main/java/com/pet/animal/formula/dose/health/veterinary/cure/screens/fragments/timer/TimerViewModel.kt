package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.timer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.CoroutineContext
import kotlin.time.Duration

class TimerViewModel() : ViewModel(), CoroutineScope {

    private var ticks: MutableList<Long> = mutableListOf()
    private var mTickInMinutes:MutableLiveData<Double> =MutableLiveData()

    private val mOnTimer = MutableLiveData<Boolean>()
    private val mSeconds = MutableLiveData<Int>()


    lateinit var job: Job


    var tickInMinutes: LiveData<Double> = mTickInMinutes
    var onTimer:LiveData<Boolean> = mOnTimer
    var second : LiveData<Int> = mSeconds

    fun startTimer(){
        if (mOnTimer.value == true) return


        ticks.clear()

        mOnTimer.postValue(true)
        countTimer()
    }

    fun addTick(){
        ticks.add(System.currentTimeMillis())
        updateTicks()
    }


    private fun updateTicks(){
        if (ticks.size>1){
            val totalTimeOverTicks = ticks[ticks.size-1]-ticks[0]
            val totalTimeMinutes:Double = totalTimeOverTicks.toDouble()/100/60
            mTickInMinutes.postValue(totalTimeMinutes)
        }
    }

    fun stopTimer(){

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
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }
}