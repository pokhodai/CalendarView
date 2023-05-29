package ru.pokhodai.calendar.generator

import ru.pokhodai.calendar.models.Month
import ru.pokhodai.calendar.utils.linkedListOf
import java.time.LocalDateTime
import java.util.LinkedList

class GeneratorCalendar {

    private val todayTime = LocalDateTime.now()
    private val list: LinkedList<Month> = linkedListOf()

    private var generateTime = todayTime
        set(value) {
            field = value

        }

    init {
        list.clear()

    }

    private fun primaryGeneratorCalendar() {

    }
}