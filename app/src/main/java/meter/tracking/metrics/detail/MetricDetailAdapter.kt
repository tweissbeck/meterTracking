package meter.tracking.metrics.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import meter.tracking.db.model.MetricRecord

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricDetailAdapter: RecyclerView.Adapter<MetricDetailViewHolder>() {

    val data: MutableList<MetricRecord> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MetricDetailViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MetricDetailViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun setData(records: List<MetricRecord>) {
        this.data.clear()
        this.data.addAll(records)
        this.notifyDataSetChanged()
    }
}