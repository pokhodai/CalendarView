package ru.pokhodai.calendar.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.pokhodai.calendar.models.Month
import ru.pokhodai.calendar.view.MonthView

class CalendarAdapter : ListAdapter<Month, CalendarAdapter.MonthViewHolder>(MonthItemDiffCallback()) {

    private var onClickDayAction: (() -> Unit)? = null

    fun onClickDayActionListener(action: (() -> Unit)) {
        onClickDayAction = action
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthViewHolder {
        return MonthViewHolder(MonthView(context = parent.context))
    }

    override fun onBindViewHolder(holder: MonthViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class MonthViewHolder(
        private val view: MonthView
    ) : RecyclerView.ViewHolder(view) {

        fun bindTo(month: Month) {
            view.month = month
        }
    }

    class MonthItemDiffCallback : DiffUtil.ItemCallback<Month>() {
        override fun areItemsTheSame(oldItem: Month, newItem: Month): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: Month, newItem: Month): Boolean =
            oldItem == newItem
    }
}