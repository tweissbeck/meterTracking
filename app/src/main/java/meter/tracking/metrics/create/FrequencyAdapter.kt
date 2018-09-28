package meter.tracking.metrics.create

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import meter.tracking.db.model.HistoryFrequency

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class FrequencyAdapter(context: Context) :
        ArrayAdapter<HistoryFrequency>(context, android.R.layout.simple_spinner_item) {

    private val values = HistoryFrequency.values()
    private val count = values.size
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val value = values[p0]
        val localizedValue = context.resources.getString(
                context.resources.getIdentifier(value.getKey(), "string", context.packageName))
        val inflater = LayoutInflater.from(context)
        val textView = inflater.inflate(android.R.layout.simple_spinner_item, p2, false) as TextView
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