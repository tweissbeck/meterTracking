package meter.tracking.launch

import android.util.Log
import meter.tracking.metrics.MetersTrackingActivity
import meter.tracking.metrics.MetricDataSource


/**
 * Launch activity presenter. Its main stuff is to load some data from base then redirect to main activity: [MetersTrackingActivity]
 * @author tweissbeck
 * @since 1.0.0
 */
class LaunchPresenter(private val view: LaunchContract.View,
                      private val metricsRepo: MetricDataSource) : LaunchContract.Presenter {

    companion object {
        internal val TAG: String = "LaunchPresenter"
    }


    init {
        view.presenter = this
    }

    /**
     * A callback triggered when async task [LoadResourceTask] ends
     */
    private fun onLoadFinished() {
        view.goToMainActivity()
    }

    override fun init() {
        Log.i(TAG, "Loading metrics")
        LoadResourceTask(metricsRepo) { onLoadFinished() }.execute(Unit)
    }
}