package meter.tracking.metrics.main.view

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import meter.tracking.R
import meter.tracking.db.model.Metric

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricViewHolder(layout: LinearLayout, private val handler: (Int) -> Unit) :
        RecyclerView.ViewHolder(layout), View.OnClickListener {

    private val name: TextView
    private val value: TextView
    private val unit: TextView

    init {
        layout.isClickable = true
        layout.setOnClickListener(this)
        this.name = layout.findViewById(R.id.metric_list_view_item_name) as TextView
        this.value = layout.findViewById(R.id.metric_list_view_item_value) as TextView
        this.unit = layout.findViewById(R.id.metric_list_view_item_unit) as TextView
    }

    override fun onClick(v: View?) {
        this.handler.invoke(adapterPosition)
    }

    fun update(metric: Metric){
        name.text = metric.name
        value.text = metric.value.toString()
        unit.text = metric.measureLabel
    }
}