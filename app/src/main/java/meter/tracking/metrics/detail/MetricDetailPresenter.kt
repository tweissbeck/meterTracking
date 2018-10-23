package meter.tracking.metrics.detail

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import meter.tracking.datasource.MetricDataSource
import meter.tracking.db.model.MetricsWithRecord
import java.util.function.Consumer

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricDetailPresenter(private val view: MetricDetailContract.MetricDetailView,
                            private val metricDataSource: MetricDataSource) :
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
            this.view.updateData(result)
        }

        val errorHandler: (Throwable) -> Unit = {
            view.returnToMainWithError("") // TODO handle error message
        }

        val onCompleteHandler: () -> Unit = {
            view.returnToMainWithError("") // TODO handle not found error message
        }

        if (deviceId != -1L) {
            val disposable = metricDataSource.getMetric(deviceId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
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
