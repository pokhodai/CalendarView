package ru.pokhodai.calendar

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes
import androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes
import androidx.constraintlayout.solver.state.Dimension
import androidx.core.os.bundleOf
import ru.pokhodai.calendar.adapter.CalendarAdapter
import ru.pokhodai.calendar.databinding.ViewCalendarBinding
import ru.pokhodai.calendar.extensions.getTextAppearance
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

    private var monthSettings = MonthSettings()

    private var settings: Settings = Settings()
        set(value) {
            field = value
            binding.run {
                txtTitleCalendar.getTextAppearance(value.textAppearanceMonthTitleResId)
            }
        }

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
        settings = Settings(
            textAppearanceMonthTitleResId = a.getResourceId(
                R.styleable.CustomCalendarView_textAppearanceTitleMonth,
                R.style.MonthTitleTextAppearance
            ),
            textAppearanceDayOfMonthResId = a.getResourceId(
                R.styleable.CustomCalendarView_textAppearanceDayOfMonth,
                R.style.DayOfMonthTextAppearance
            ),
            textAppearanceDayNonMonthResId = a.getResourceId(
                R.styleable.CustomCalendarView_textAppearanceDayNonMonth,
                R.style.DayNonMonthTextAppearance
            ),
            colorPreviousIconResId = a.getResourceId(
                R.styleable.CustomCalendarView_colorPreviousIcon,
                R.color.blue
            ),
            colorNextIconResId = a.getResourceId(
                R.styleable.CustomCalendarView_colorNextIcon,
                R.color.blue
            ),
            drawablePreviousIconResId = a.getResourceId(
                R.styleable.CustomCalendarView_drawablePreviousIcon,
                R.drawable.ic_previous
            ),
            drawableNextIconResId = a.getResourceId(
                R.styleable.CustomCalendarView_drawableNextIcon,
                R.drawable.ic_next
            ),
            colorFocusDayResId = a.getResourceId(
                R.styleable.CustomCalendarView_colorFocusDay,
                R.color.blue
            ),
            colorTodayResId = a.getResourceId(
                R.styleable.CustomCalendarView_colorToday,
                R.color.blue
            ),
            drawableFocusResId = a.getResourceId(R.styleable.CustomCalendarView_drawableFocus, -1),
            drawableTodayResId = a.getResourceId(R.styleable.CustomCalendarView_drawableToday, -1),
            isVisiblePreviousAndNextIcon = a.getBoolean(
                R.styleable.CustomCalendarView_isVisiblePreviousAndNextIcon,
                true
            ),
            isVisibleDayNonMonth = a.getBoolean(
                R.styleable.CustomCalendarView_isVisibleDayNonMonth,
                true
            )
        )
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
        generatorCalendar.removeCalendarListListener()
        this.removeAllViews()
        super.onDetachedFromWindow()
    }

    data class Settings(
        //Header
        @StyleRes val textAppearanceMonthTitleResId: Int = R.style.MonthTitleTextAppearance,
        @ColorRes val colorPreviousIconResId: Int = R.color.blue,
        @ColorRes val colorNextIconResId: Int = R.color.blue,
        @DrawableRes val drawablePreviousIconResId: Int = R.drawable.ic_previous,
        @DrawableRes val drawableNextIconResId: Int = R.drawable.ic_next,

        //Calendar
        @StyleRes val textAppearanceDayOfMonthResId: Int = R.style.DayOfMonthTextAppearance,
        @StyleRes val textAppearanceDayNonMonthResId: Int = R.style.DayNonMonthTextAppearance,
        @ColorRes val colorFocusDayResId: Int = R.color.blue,
        @ColorRes val colorTodayResId: Int = R.color.blue,
        @DrawableRes val drawableFocusResId: Int? = null,
        @DrawableRes val drawableTodayResId: Int? = null,
        val isVisiblePreviousAndNextIcon: Boolean = true,
        val isVisibleDayNonMonth: Boolean = true
    )

    internal class MonthSettings(
        val dayOfMonthSettings: DaySettings? = null,
        val dayNonMonthSettings: DaySettings? = null,
        val isVisibleDayNonMonth: Boolean = false,
        val isVisiblePreviousAndNextIcon: Boolean = false
    ) {

        class DaySettings(
            val font: Typeface? = null,
            val textColor: Color? = null,
            val textSize: Dimension? = null
        )
    }

    fun setTextAppearanceMonthTitleResId(@StyleRes textAppearance: Int) {
        settings = settings.copy(textAppearanceMonthTitleResId = textAppearance)
    }

    fun setTextAppearanceDayOfMonthResId(@StyleRes textAppearance: Int) {
        settings = settings.copy(textAppearanceDayOfMonthResId = textAppearance)
    }

    fun setTextAppearanceDayNonMonthResId(@StyleRes textAppearance: Int) {
        settings = settings.copy(textAppearanceDayNonMonthResId = textAppearance)
    }

    fun setColorPreviousIconResId(@ColorRes colorResId: Int) {
        settings = settings.copy(colorPreviousIconResId = colorResId)
    }

    fun setColorNextIconResId(@ColorRes colorResId: Int) {
        settings = settings.copy(colorNextIconResId = colorResId)
    }

    fun setDrawablePreviousIconResId(@DrawableRes drawableResId: Int) {
        settings = settings.copy(drawablePreviousIconResId = drawableResId)
    }

    fun setDrawableNextIconResId(@DrawableRes drawableResId: Int) {
        settings = settings.copy(drawableNextIconResId = drawableResId)
    }

    fun setColorFocusDayResId(@ColorRes colorResId: Int) {
        settings = settings.copy(colorFocusDayResId = colorResId)
    }

    fun setColorTodayResId(@ColorRes colorResId: Int) {
        settings = settings.copy(colorTodayResId = colorResId)
    }

    fun setDrawableFocusResId(@DrawableRes drawableResId: Int) {
        settings = settings.copy(drawableFocusResId = drawableResId)
    }

    fun setDrawableTodayResId(@DrawableRes drawableResId: Int) {
        settings = settings.copy(drawableTodayResId = drawableResId)
    }

    fun setVisiblePreviousAndNextIcon(isVisible: Boolean) {
        settings = settings.copy(isVisiblePreviousAndNextIcon = isVisible)
    }

    fun setVisibleDayNonMonth(isVisible: Boolean) {
        settings = settings.copy(isVisibleDayNonMonth = isVisible)
    }

    fun setSettings(settings: Settings) {
        this.settings = settings
    }
}

//class CustomCalendarView(context: Context): SurfaceView(context), SurfaceHolder.Callback {
//
//
//
//
//    override fun surfaceCreated(p0: SurfaceHolder) {
//
//    }
//
//    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
//
//    }
//
//    override fun surfaceDestroyed(p0: SurfaceHolder) {
//
//    }
//
//}
//
//class ThreadCalendar(
//    private val mSurfaceHolder: SurfaceHolder
//): Thread() {
//
//}