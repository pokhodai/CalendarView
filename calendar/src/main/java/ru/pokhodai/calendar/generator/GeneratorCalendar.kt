package ru.pokhodai.calendar.generator

import android.util.Log
import ru.pokhodai.calendar.models.Day
import ru.pokhodai.calendar.models.Month
import ru.pokhodai.calendar.utils.linkedListOf
import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalDateTime
import java.time.temporal.TemporalAdjusters
import java.util.LinkedList

class GeneratorCalendar() {

    private var focusTime: LocalDateTime = getTodayTime()

    private val list: LinkedList<Month> = linkedListOf()

    private var calendarListListener: ((list: List<Month>) -> Unit)? = null

    fun onCalendarListListener(listener: ((list: List<Month>) -> Unit)) {
        calendarListListener = listener
    }

    fun primaryGeneratorCalendar() {
        list.addAll(
            toGenerateMonthPrevious(focusTime)
                    + listOf(generateMonth(focusTime))
                    + toGenerateMonthsForward(focusTime)
        )
        calendarListListener?.invoke(list)
    }

    private fun toGenerateMonthsForward(time: LocalDateTime): List<Month> {
        var timeGenerate = time
        return List(6) {
            timeGenerate = timeGenerate.plusMonths(1)
            generateMonth(timeGenerate)
        }
    }

    private fun toGenerateMonthPrevious(time: LocalDateTime): List<Month> {
        var timeGenerate = time
        return List(6) {
            timeGenerate = timeGenerate.minusMonths(1)
            generateMonth(timeGenerate)
        }
    }

    private fun generateMonth(time: LocalDateTime): Month {
        val firstDayOfMonth = time.with(TemporalAdjusters.firstDayOfMonth())
        val lastDayOfMonth = time.with(TemporalAdjusters.lastDayOfMonth())
        val range = firstDayOfMonth..lastDayOfMonth
        val title = time.coerceIn(range)
        return Month(
            title = title,
            days = generateDays(firstDayOfMonth, lastDayOfMonth)
        )
    }

    private fun generateDays(
        firstDayOfMonth: LocalDateTime,
        lastDayOfMonth: LocalDateTime
    ): List<Day> {
        val firstDayOfCalendar = firstDayOfMonth.with(DayOfWeek.MONDAY)
        val lastDayOfCalendar = lastDayOfMonth.with(DayOfWeek.SUNDAY)
        val size = Duration.between(firstDayOfCalendar, lastDayOfCalendar).toDays().toInt()
        var generateDayOfMonth = firstDayOfMonth.minusDays(1)
        return List(size) { index ->
            generateDayOfMonth = generateDayOfMonth.plusDays(1)
            Day(
                dayOfMonth = generateDayOfMonth,
                isFocus = generateDayOfMonth == getTodayTime()
            )
        }
    }

    fun removeCalendarListListener() {
        calendarListListener = null
    }

    companion object {
        fun getTodayTime(): LocalDateTime {
            return LocalDateTime.now().toLocalDate().atStartOfDay()
        }
    }
}