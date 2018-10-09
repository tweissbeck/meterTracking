package meter.tracking.metrics.main.view

import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricViewHolder(val layout: LinearLayout, private val handler: (Int) -> Unit) :
        RecyclerView.ViewHolder(layout), View.OnClickListener {
    init {
        layout.isClickable = true
        layout.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        this.handler.invoke(adapterPosition)
    }
}