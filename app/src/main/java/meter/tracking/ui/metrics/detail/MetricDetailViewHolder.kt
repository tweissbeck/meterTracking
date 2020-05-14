package meter.tracking.ui.metrics.detail

import android.annotation.SuppressLint
import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import meter.tracking.R
import meter.tracking.db.model.HistoryFrequency
import java.time.LocalDate
import java.time.format.TextStyle

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricDetailViewHolder(val context: Context, val layout: LinearLayout, private val type: HistoryFrequency) : RecyclerView.ViewHolder(layout) {

    private val value: TextView
    private val date: TextView

    init {
        this.layout.isClickable = true
        this.value = layout.findViewById(R.id.metric_detail_list_view_item_value)
        this.date = layout.findViewById(R.id.metric_detail_list_view_item_date)
    }

    @Override
    fun setValue(value: Long?, date: LocalDate) {
        this.value.text = value?.toString() ?: ""
        if (value == null) {
            this.value.setBackgroundResource(android.R.color.holo_red_light)
        }
        when (type) {
            HistoryFrequency.DAILY -> {
                @SuppressLint("SetTextI18n")
                this.date.text = date.dayOfWeek.getDisplayName(TextStyle.SHORT, context.resources.configuration.locales.get(0)) + " (${date.dayOfMonth})"
            }
            HistoryFrequency.WEEKLY -> {
                TODO()
            }
            HistoryFrequency.MONTHLY -> {
                this.date.text = date.month.getDisplayName(TextStyle.SHORT, context.resources.configuration.locales.get(0))
            }
            HistoryFrequency.ANNUAL -> {
                this.date.text = date.year.toString()
            }
        }
    }

}
