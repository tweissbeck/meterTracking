package meter.tracking.metrics.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import meter.tracking.R
import meter.tracking.db.model.MetricRecord

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricDetailAdapter: RecyclerView.Adapter<MetricDetailViewHolder>() {

    val data: MutableList<MetricRecord> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MetricDetailViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.metric_detail_list_view_item, parent,
                                                                 false) as LinearLayout
        return MetricDetailViewHolder(layout)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MetricDetailViewHolder, position: Int) {
        val current = data[position]
        holder.setValue(current.value, current.date)
    }

    fun setData(records: List<MetricRecord>) {
        this.data.clear()
        this.data.addAll(records)
        this.notifyDataSetChanged()
    }
}