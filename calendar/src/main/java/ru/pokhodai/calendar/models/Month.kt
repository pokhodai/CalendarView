package ru.pokhodai.calendar.models

import java.time.LocalDateTime

data class Month(
    val title: LocalDateTime? = null,
    val days: List<Day>? = null
) {
}