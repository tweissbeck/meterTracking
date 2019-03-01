package meter.tracking.metrics.detail

import android.graphics.drawable.Drawable
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import meter.tracking.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricDetailViewHolder(layout: LinearLayout) : RecyclerView.ViewHolder(layout) {

    private val value: TextView
    private val date: TextView

    init {
        layout.isClickable = true
        this.value = layout.findViewById(R.id.metric_detail_list_view_item_value)
        this.date = layout.findViewById(R.id.metric_detail_list_view_item_date)
    }

    fun setValue(value: Long?, date: LocalDate) {
        this.value.text = value?.toString() ?: ""
        if (value == null) {
            this.value.setBackgroundResource(android.R.color.holo_red_light)
        }
        this.date.text = date.format(DateTimeFormatter.ISO_LOCAL_DATE)
    }

}
