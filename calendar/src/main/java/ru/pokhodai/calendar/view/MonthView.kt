package ru.pokhodai.calendar.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import ru.pokhodai.calendar.models.Day
import ru.pokhodai.calendar.models.Month

class MonthView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): View(context, attrs, defStyleAttr) {

    var month: Month? = null
        set(value) {
            field = value
        }





    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }




}