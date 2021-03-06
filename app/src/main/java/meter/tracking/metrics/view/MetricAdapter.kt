package meter.tracking.metrics.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import meter.tracking.R
import meter.tracking.db.model.Metric


/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricAdapter(private val data: List<Metric>) : RecyclerView.Adapter<MetricViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MetricViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.metric_list_view_item, parent,
                                                                 false) as LinearLayout


        return MetricViewHolder(layout)
    }


    override fun getItemCount(): Int = data.size

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MetricViewHolder, position: Int) {

        val currentMetric = data[position]
        val name = holder.layout.findViewById(R.id.metric_list_view_item_name) as TextView
        val value = holder.layout.findViewById(R.id.metric_list_view_item_value) as TextView
        val unit = holder.layout.findViewById(R.id.metric_list_view_item_unit) as TextView

        name.text = currentMetric.name
        value.text = currentMetric.value.toString()
        unit.text = currentMetric.measureLabel


    }
}