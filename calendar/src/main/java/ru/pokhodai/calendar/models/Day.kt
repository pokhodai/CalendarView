package ru.pokhodai.calendar.models

import java.time.LocalDateTime

data class Day(
    val dayOfMonth: LocalDateTime,
    val isFocus: Boolean
) {
}