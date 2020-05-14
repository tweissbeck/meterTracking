package meter.tracking.ui.metrics.main.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import meter.tracking.R
import meter.tracking.db.model.MetricEntity
import meter.tracking.ui.common.OnSelectRow


/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricAdapter(context: Context, private val onSelectRow: OnSelectRow<MetricEntity>) : RecyclerView.Adapter<MetricViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var metrics = emptyList<MetricEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MetricViewHolder {
        val layout = inflater.inflate(R.layout.metric_list_view_item, parent,
                false) as LinearLayout
        return MetricViewHolder(layout, onSelectRow, metrics)
    }

    internal fun setMetrics(data: List<MetricEntity>) {
        this.metrics = data
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = metrics.size

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MetricViewHolder, position: Int) {
        val currentMetric = metrics[position]
        holder.update(currentMetric)
    }

}