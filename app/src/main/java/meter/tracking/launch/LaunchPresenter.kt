package meter.tracking.launch

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import meter.tracking.db.model.Metric
import meter.tracking.metrics.main.MetersTrackingActivity
import meter.tracking.metrics.main.MetricDataSource
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


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

    override fun init() {
        fun onSuccess(): (List<Metric>) -> Unit = { _ ->
            view.goToMainActivity()
        }

        fun onError(): (Throwable) -> Unit = { _ ->
            TODO()
        }

        Log.i(TAG, "Loading metrics")

        val loadMetricObservable = metricsRepo.getMetrics().delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccess(), onError())

    }
}