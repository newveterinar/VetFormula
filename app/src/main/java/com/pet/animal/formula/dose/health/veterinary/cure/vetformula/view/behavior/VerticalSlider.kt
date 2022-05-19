package com.pet.animal.formula.dose.health.veterinary.cure.vetformula.view.behavior

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import com.pet.animal.formula.dose.health.veterinary.cure.utils.SLIDER_START_ANGLE
import com.pet.animal.formula.dose.health.veterinary.cure.vetformula.view.MainActivity

class VerticalSlider @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): com.google.android.material.slider.Slider(context, attrs, defStyleAttr) {
    /** Задание переменных */ //region
    private var mainActivity: MainActivity = (context as MainActivity)
    //endregion

    init {
        rotation = SLIDER_START_ANGLE
    }

    // Необходимы метод для получени
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        mainActivity.changeUpAndBottomFramesSizes(value)
        super.onDraw(canvas)
    }
}