package meter.tracking.metrics.main

import android.util.Log
import meter.tracking.db.model.Metric

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricMainPresenter(view: MetricMainContract.View): MetricMainContract.Presenter {
    override fun handleSelect(metric: Metric) {
        Log.i("MetricMainPresenter", "Metric ($metric) select" )
    }
}