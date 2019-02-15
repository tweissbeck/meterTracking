package meter.tracking.metrics.detail

import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import meter.tracking.R
import java.time.LocalDate

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricDetailViewHolder(layout: LinearLayout) : RecyclerView.ViewHolder(layout) {

    private val value: TextView

    init {
        layout.isClickable = true
        this.value = layout.findViewById(R.id.metric_detail_list_view_item_value)
    }

    fun setValue(value: Long, date: LocalDate) {
        this.value.text = value.toString()
    }

}
