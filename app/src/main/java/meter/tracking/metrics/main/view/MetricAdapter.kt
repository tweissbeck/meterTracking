package meter.tracking.metrics.main.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import meter.tracking.R
import meter.tracking.db.model.Metric
import meter.tracking.metrics.main.MetricRecyclerViewPresenter


/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricAdapter(private val presenter: MetricRecyclerViewPresenter) : RecyclerView.Adapter<MetricViewHolder>() {


    val data: MutableList<Metric> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MetricViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.metric_list_view_item, parent,
                                                                 false) as LinearLayout

        return MetricViewHolder(layout) { pos ->
            presenter.handleSelect(data[pos])
        }
    }

    fun setUpData(data: List<Metric>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun update(data: List<Metric>) {
        this.data.addAll(data)
        notifyDataSetChanged()
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