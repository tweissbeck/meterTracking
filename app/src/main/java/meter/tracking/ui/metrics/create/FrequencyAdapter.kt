package meter.tracking.ui.metrics.create

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import meter.tracking.R
import meter.tracking.db.model.HistoryFrequency

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class FrequencyAdapter(context: Context) :
        ArrayAdapter<HistoryFrequency>(context, R.layout.list_item) {

    private val values = HistoryFrequency.values()
    private val count = values.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val value = values[position]
        val localizedValue = context.resources.getString(
                context.resources.getIdentifier(value.getKey(), "string", context.packageName))
        val inflater = LayoutInflater.from(context)
        val textView = inflater.inflate(R.layout.list_item, parent, false) as TextView
        textView.text = localizedValue
        return textView
    }

    override fun getItem(index: Int): HistoryFrequency? {
        return if (index < count)
            values[index]
        else
            null
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int = count
}