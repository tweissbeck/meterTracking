package meter.tracking.metrics.create

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import meter.tracking.datasource.MetricDataSource
import meter.tracking.db.model.HistoryFrequency
import io.reactivex.disposables.CompositeDisposable


/**
 * @author tweissbeck
 * @since 1.0.0
 */
class CreateMetricPresenter(private val view: CreateMetricContract.View, private val repo: MetricDataSource) :
        CreateMetricContract.Presenter {

    private val mCompositeDisposable = CompositeDisposable()

    override fun createMetric(name: String?, historyFrequency: HistoryFrequency?, unit: String?) {
        // All field are empty, just return to the metrics list without error
        if (name == null && historyFrequency == null && unit == null) {
            view.navigateToMetricsList()
        }

        // Check if some fields are empty
        var error = false
        if (name == null) {
            error = true
            view.displayEmptyNameError()
        }
        if (historyFrequency == null) {
            error = true
            view.displayEmptyFrequency()
        }

        if (unit == null) {
            error = true
            view.displayEmptyUnit()
        }

        // Save the new metric
        if (!error) {
            val metric = MetricDTO(name!!, historyFrequency!!, unit!!)
            val successHandler = { id: Long -> view.navigateToMetricsListWithNewMetricAdded(name) }
            val errorHandler = { e: Throwable -> view.navigateToMetricsListWithError(e.message ?: "new.metric.error") }

            val closeable = repo.saveMetric(metric)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(successHandler, errorHandler)
            mCompositeDisposable.add(closeable)
        } else {

        }
    }
}