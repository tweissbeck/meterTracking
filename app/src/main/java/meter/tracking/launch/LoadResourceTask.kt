package meter.tracking.launch

import android.os.AsyncTask
import android.util.Log
import meter.tracking.metrics.MetricDataSource

/**
 * An async task that deal with first metrics loading
 * @param callback a function called when task is finished
 * @author tweissbeck
 * @since 1.0.0
 */
class LoadResourceTask(private val metricsRepo: MetricDataSource, private val callback: () -> Unit) :
        AsyncTask<Unit, Unit, Unit>() {

    companion object {
        const val TAG = "LoadResourceTask"
    }

    override fun doInBackground(vararg p0: Unit?) {
        // Just load the metrics in cache when implementation allow it
        metricsRepo.getMetrics()
        return
    }

    override fun onPostExecute(result: Unit?) {
        Log.i(TAG, "Metrics loaded")
        callback.invoke()
    }

}