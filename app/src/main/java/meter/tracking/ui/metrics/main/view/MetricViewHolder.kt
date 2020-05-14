package meter.tracking.ui.metrics.main.view

import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import meter.tracking.R
import meter.tracking.db.model.MetricEntity
import meter.tracking.ui.common.OnSelectRow

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricViewHolder(layout: LinearLayout, private val listener: OnSelectRow<MetricEntity>, private val datas: List<MetricEntity>) :
        RecyclerView.ViewHolder(layout) {

    private val name: TextView
    private val value: TextView
    private val unit: TextView

    init {
        layout.isClickable = true
        layout.setOnClickListener { listener.selectRow(datas[adapterPosition]) }
        this.name = layout.findViewById(R.id.metric_list_view_item_name) as TextView
        this.value = layout.findViewById(R.id.metric_list_view_item_value) as TextView
        this.unit = layout.findViewById(R.id.metric_list_view_item_unit) as TextView
    }



    fun update(metricEntity: MetricEntity){
        this.name.text = metricEntity.name
        this.value.text = metricEntity.value.toString()
        this.unit.text = metricEntity.measureLabel
    }

}