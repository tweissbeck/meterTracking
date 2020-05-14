package meter.tracking.ui.metrics.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import meter.tracking.R
import meter.tracking.db.model.HistoryFrequency
import meter.tracking.ui.metrics.detail.history.RecordData

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricDetailAdapter(private val context: Context) : RecyclerView.Adapter<MetricDetailViewHolder>() {

    private val data: MutableList<RecordData> = mutableListOf()
    private lateinit var type: HistoryFrequency

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MetricDetailViewHolder {

        val resource: Int = when(type){
            HistoryFrequency.DAILY -> R.layout.metric_detail_list_view_item
            HistoryFrequency.WEEKLY -> R.layout.metric_detail_list_view_item
            HistoryFrequency.MONTHLY -> R.layout.metric_detail_list_view_item
            HistoryFrequency.ANNUAL -> R.layout.metric_detail_list_view_item
        }

        val layout = LayoutInflater.from(parent.context).inflate(resource, parent,
                                                                 false) as LinearLayout
        return MetricDetailViewHolder(context, layout, type)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MetricDetailViewHolder, position: Int) {
        val current = data[position]
        holder.setValue(current.second, current.first)
    }

    fun setData(data: Collection<RecordData>, type: HistoryFrequency) {
        this.data.clear()
        this.data.addAll(data)
        this.notifyDataSetChanged()
    }
}