package meter.tracking.metrics.create

import android.content.Context
import android.widget.ArrayAdapter

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricUnitAdapter(context: Context) :
        ArrayAdapter<String>(context, android.R.layout.simple_spinner_item)