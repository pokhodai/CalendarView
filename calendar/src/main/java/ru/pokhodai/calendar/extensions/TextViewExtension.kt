package ru.pokhodai.calendar.extensions

import android.widget.TextView
import androidx.annotation.StyleRes
import androidx.core.widget.TextViewCompat

fun TextView.getTextAppearance(@StyleRes styleResId: Int) = TextViewCompat.setTextAppearance(this, styleResId)