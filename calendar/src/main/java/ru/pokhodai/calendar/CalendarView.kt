package ru.pokhodai.calendar

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import ru.pokhodai.calendar.adapter.CalendarAdapter

class CalendarView @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    private val defStyleAttr: Int = 0
): LinearLayout(context, attrs, defStyleAttr)  {

    private var recyclerCalendarView: RecyclerView? = null
    private var titleMonthTextView: MaterialTextView? = null
    private val adapterCalendar: CalendarAdapter = CalendarAdapter()

    init {
        setSettingsCalendarView()

        initRecyclerView()
        initTitleMonthTextView()

        addViewInCalendarView()

        setAttrs()

        setAdapter()
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

    private fun setAttrs() {
        val a =
            context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.CustomCalendarView,
                defStyleAttr,
                0
            )

    }

    private fun setSettingsCalendarView() {
        this.orientation = VERTICAL
    }

    private fun initRecyclerView() {
        recyclerCalendarView = RecyclerView(context).apply {
            orientation = HORIZONTAL
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun initTitleMonthTextView() {
        titleMonthTextView = MaterialTextView(context)
    }

    private fun addViewInCalendarView() {
        this.addView(titleMonthTextView)
        this.addView(recyclerCalendarView)
    }

    private fun setAdapter() {
        recyclerCalendarView?.adapter = adapterCalendar
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        recyclerCalendarView = null
        titleMonthTextView = null
    }
}