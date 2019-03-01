package meter.tracking.metrics.detail

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import meter.tracking.datasource.MetricDataSource
import meter.tracking.db.model.MetricsWithRecord
import meter.tracking.metrics.detail.history.buildRecordHistory
import meter.tracking.rx.SchedulerProvider

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricDetailPresenter(private val view: MetricDetailContract.MetricDetailView,
                            private val metricDataSource: MetricDataSource,
                            private val schedulerProvider: SchedulerProvider) :
        MetricDetailContract.MetricDetailPresenter {

    companion object {
        private const val TAG = "MetricDetailP"
    }

    private val disposables = CompositeDisposable()

    override fun start() {

    }

    override fun init(deviceId: Long) {
        val successHandler: (MetricsWithRecord) -> Unit = { result ->
            Log.i(TAG, "Metric loaded: $result")
            this.view.updateActivityTitle(result.name)
            val history = buildRecordHistory(result)
            this.view.updateData(history, result.value.toString())
        }

        val errorHandler: (Throwable) -> Unit = {
            Log.w(TAG, "Metric loaded error", it)
            view.returnToMainWithError("error.get.metric.throwable", arrayOf(deviceId))
        }

        val onCompleteHandler: () -> Unit = {
            Log.i(TAG, "Metric completed")
            view.returnToMainWithError("error.get.metric.not.found", arrayOf(deviceId))
        }

        if (deviceId >= 0L) {
            val disposable = this.metricDataSource.getMetric(deviceId)
                    .observeOn(this.schedulerProvider.ui())
                    .subscribeOn(this.schedulerProvider.io())
                    .subscribe(successHandler, errorHandler, onCompleteHandler)
            this.disposables.add(disposable)
        } else {
            view.returnToMainWithError("")
        }
    }

    override fun onDestroy() {
        this.disposables.dispose()
    }
}
