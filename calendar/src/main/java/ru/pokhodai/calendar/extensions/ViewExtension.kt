package ru.pokhodai.calendar.extensions

import android.content.res.ColorStateList
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import ru.pokhodai.calendar.R

fun View.getColorInt(@ColorRes colorResId: Int) = this.context.getColor(colorResId)
fun View.getDrawable(@DrawableRes drawableResId: Int): Drawable? = AppCompatResources.getDrawable(this.context, drawableResId)
fun View.getColorListState(@ColorRes colorListId: Int): ColorStateList = this.context.getColorStateList(colorListId)
fun View.getDimen(@DimenRes dimenResId: Int): Float = resources.getDimension(dimenResId)
fun View.getFont(@FontRes fontResId: Int): Typeface? = ResourcesCompat.getFont(this.context, fontResId)
fun View.getTypedArray(textAppearanceResId: Int) = context.obtainStyledAttributes(textAppearanceResId, androidx.appcompat.R.styleable.TextAppearance)