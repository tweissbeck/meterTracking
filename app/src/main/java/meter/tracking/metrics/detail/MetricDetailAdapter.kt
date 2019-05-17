package meter.tracking.metrics.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import meter.tracking.R
import meter.tracking.metrics.detail.history.RecordData

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricDetailAdapter : RecyclerView.Adapter<MetricDetailViewHolder>() {

    private val data: MutableList<RecordData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MetricDetailViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.metric_detail_list_view_item, parent,
                                                                 false) as LinearLayout
        return MetricDetailViewHolder(layout)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MetricDetailViewHolder, position: Int) {
        val current = data[position]
        holder.setValue(current.second, current.first)
    }

    fun setData(data: Collection<RecordData>) {
        this.data.clear()
        this.data.addAll(data)
        this.notifyDataSetChanged()
    }
}