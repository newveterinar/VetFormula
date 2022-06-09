package com.pet.animal.formula.dose.health.veterinary.cure.screens.fragments.timer

import android.media.AudioManager
import android.media.ToneGenerator
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pet.animal.formula.dose.health.veterinary.cure.core.base.BaseViewModelForNavigation
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TimerViewModel() : BaseViewModelForNavigation(), CoroutineScope {

    private var ticks: MutableList<Long> = mutableListOf()

    private var mTickInMinutes: MutableLiveData<Double> = MutableLiveData()
    private var mTimerHeartRate:MutableLiveData<Double> = MutableLiveData()
    private var mManualRate: MutableLiveData<Double> = MutableLiveData()

    private val mOnTimer = MutableLiveData<Boolean>()
    private val mSeconds = MutableLiveData<Int>()

    private val toneGenerator = ToneGenerator(AudioManager.STREAM_ALARM, 100)
    private val mMute = MutableLiveData(false)

    private var tapCount: Int = 0


    lateinit var job: Job




    var timerHeartRate:LiveData<Double> = mTimerHeartRate
    var tickInMinutes: LiveData<Double> = mTickInMinutes
    var tickManual : LiveData<Double> = mManualRate

    var onTimer: LiveData<Boolean> = mOnTimer
    var second: LiveData<Int> = mSeconds
    val mute: LiveData<Boolean> = mMute

    var timerTo = 60

    fun startTimer() {
        if (mOnTimer.value == true) return
        mOnTimer.postValue(true)
        countTimer()
    }

    fun resetTimer() {
        ticks.clear()
        mSeconds.postValue(0)
        stopTimer()
        timerTo = 60
        tapCount = 0
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
            val totalTimeMinutes: Double = totalTimeOverTicks.toDouble() / 1000 / 60
            mTickInMinutes.postValue(ticks.size.toDouble() / totalTimeMinutes)
        }
    }

    fun resetTickCounter() {
        ticks.clear()
        mTickInMinutes.postValue(0.00)
        tapCount = 0
    }

    fun stopTimer() {
        if (mOnTimer.value != true) return
        job.cancel()
        mOnTimer.postValue(false)
        updateTicks()

        mSeconds.value?.let {
            mTimerHeartRate.postValue(60.0 * (tapCount.toDouble()/it.toDouble()))
        }

    }

    fun plusOneMinutes() {
        timerTo += 60
        val sec = mSeconds.value?:0
        mSeconds.postValue(sec)

    }

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Main


    private fun countTimer() {
        job = launch {
            val timer = mOnTimer.value ?: false
            while (timer) {
                delay(1000)
                val time = mSeconds.value ?: 0
                val newtime = time+1
                mSeconds.postValue(newtime)
                if ((time + 1) % 15 == 0) {
                    if (mMute.value != true) {
                        toneGenerator.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200)
                    }
                }

                if (tapCount!=0 && newtime>0){
                    mTimerHeartRate.postValue(60.0*(tapCount.toDouble()/newtime.toDouble()))
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
                    mManualRate.postValue(tickInt.toDouble() / timerMinutes)
                }
            }

        } catch (e: NumberFormatException) {
            mTickInMinutes.postValue(0.00)
        }
    }

    fun changeMuteSound() {
        mMute.postValue(!(mMute.value ?: false))
    }
}