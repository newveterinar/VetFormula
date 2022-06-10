package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.timer

import android.media.AudioManager
import android.media.ToneGenerator
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseViewModelForNavigation
import com.pet.animal.formula.dose.health.veterinary.cure.utils.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TimerViewModel() : BaseViewModelForNavigation(), CoroutineScope {

    private var ticks: MutableList<Long> = mutableListOf()

    private var mTickInMinutes: MutableLiveData<Double> = MutableLiveData()
    private var mTimerBDD:MutableLiveData<Double> = MutableLiveData()
    private var mManualRate: MutableLiveData<Double> = MutableLiveData()

    private val mOnTimer = MutableLiveData<Boolean>()
    private val mSeconds = MutableLiveData<Int>()

    private val toneGenerator = ToneGenerator(AudioManager.STREAM_ALARM, SHORT_CONSTANT)
    private val mMute = MutableLiveData(false)

    private var tapCount: Int = 0


    lateinit var job: Job




    var timerBDD:LiveData<Double> = mTimerBDD
    var tickInMinutes: LiveData<Double> = mTickInMinutes
    var tickManual : LiveData<Double> = mManualRate

    var onTimer: LiveData<Boolean> = mOnTimer
    var second: LiveData<Int> = mSeconds
    val mute: LiveData<Boolean> = mMute

    var timerTo = NUMBER_SECONDS_IN_MINUTE.toInt()

    fun startTimer() {
        if (mOnTimer.value == true) return
        mOnTimer.postValue(true)
        countTimer()
    }

    fun resetTimer() {
        ticks.clear()
        mSeconds.postValue(0)
        stopTimer()
        timerTo = NUMBER_SECONDS_IN_MINUTE.toInt()

        mManualRate.postValue(START_VALUE)
        mTimerBDD.postValue(START_VALUE)
        resetTickCounter()
    }

    fun addTick() {
        ticks.add(System.currentTimeMillis())
        updateTicks()
        if (mOnTimer.value == true) {
            tapCount++
        }
    }

    private fun updateTicks() {
        if (ticks.size > 1) {
            val totalTimeOverTicks = ticks[ticks.size - 1] - ticks[0]
            val totalTimeMinutes: Double =
                totalTimeOverTicks.toDouble() / DELAY_TIME.toDouble() / NUMBER_SECONDS_IN_MINUTE
            mTickInMinutes.postValue(ticks.size.toDouble() / totalTimeMinutes)
        }
    }

    fun resetTickCounter() {
        ticks.clear()
        mTickInMinutes.postValue(START_VALUE)
        tapCount = 0
    }

    fun stopTimer() {
        if (mOnTimer.value != true) return
        job.cancel()
        mOnTimer.postValue(false)
        updateTicks()

        mSeconds.value?.let {
            mTimerBDD.postValue(NUMBER_SECONDS_IN_MINUTE * (tapCount.toDouble()/it.toDouble()))
        }

    }

    fun plusOneMinutes() {
        timerTo += NUMBER_SECONDS_IN_MINUTE.toInt()
        val sec = mSeconds.value?:0
        mSeconds.postValue(sec)

    }

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Main


    private fun countTimer() {
        job = launch {
            val timer = mOnTimer.value ?: false
            while (timer) {
                delay(DELAY_TIME)
                val time = mSeconds.value ?: 0
                val newtime = time+1

                mSeconds.postValue(newtime)

                if (tapCount!=0 && newtime>0){
                    mTimerBDD.postValue(
                        NUMBER_SECONDS_IN_MINUTE*(tapCount.toDouble()/newtime.toDouble()))
                }

                if (newtime==timerTo){
                    stopTimer()
                    if (mute.value != true)
                        toneGenerator.startTone(
                            ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, LONG_CONSTANT)
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
            mTickInMinutes.postValue(START_VALUE)
            val tickInt = stringValue.toInt()
            mSeconds.value?.let {
                val timerMinutes = it.toDouble() / NUMBER_SECONDS_IN_MINUTE
                if (it != 0) {
                    mManualRate.postValue(tickInt.toDouble() / timerMinutes)
                }
            }

        } catch (e: NumberFormatException) {
            mTickInMinutes.postValue(START_VALUE)
        }
    }

    fun changeMuteSound() {
        mMute.postValue(!(mMute.value ?: false))
    }
}