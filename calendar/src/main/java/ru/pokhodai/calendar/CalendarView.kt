package ru.pokhodai.calendar

import android.content.Context
import android.content.res.TypedArray
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import ru.pokhodai.calendar.adapter.CalendarAdapter
import ru.pokhodai.calendar.databinding.ViewCalendarBinding
import ru.pokhodai.calendar.generator.GeneratorCalendar
import ru.pokhodai.calendar.models.Month


class CalendarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): LinearLayout(context, attrs, defStyleAttr) {

    private val binding = ViewCalendarBinding
        .inflate(LayoutInflater.from(context), this)

    private val generatorCalendar by lazy { GeneratorCalendar() }

    private val adapterCalendar by lazy { CalendarAdapter() }

    init {
        this.orientation = VERTICAL
        val a =
            context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.CustomCalendarView,
                defStyleAttr,
                0
            )
        setAttrs(a)
        initAdapter()
        setListeners()
        generatorCalendar.primaryGeneratorCalendar()
    }

    private fun setAttrs(a: TypedArray) {

    }

    private fun initAdapter() {
        binding.rvCalendar.adapter = adapterCalendar
    }

    private fun setListeners() {
        generatorCalendar.onCalendarListListener {
            setAdapter(it)
        }
    }

    private fun setAdapter(list: List<Month>) {
        adapterCalendar.submitList(list)
    }

    override fun onSaveInstanceState(): Parcelable {
        super.onSaveInstanceState()
        return bundleOf()
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        var restoreState = state
        if (restoreState is Bundle) {

        }
        super.onRestoreInstanceState(restoreState)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        this.removeAllViews()
        generatorCalendar.removeCalendarListListener()
    }
}