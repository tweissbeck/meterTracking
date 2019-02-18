package meter.tracking.metrics.create

import io.reactivex.disposables.CompositeDisposable
import meter.tracking.datasource.MetricDataSource
import meter.tracking.db.model.HistoryFrequency
import meter.tracking.rx.SchedulerProvider
import meter.tracking.util.StringUtil
import java.time.LocalDate


/**
 * @author tweissbeck
 * @since 1.0.0
 */
class CreateMetricPresenter(private val view: CreateMetricContract.View, private val repo: MetricDataSource,
                            private val schedulerProvider: SchedulerProvider) :
        CreateMetricContract.Presenter {

    private val mCompositeDisposable = CompositeDisposable()

    override fun createMetric(name: String?, historyFrequency: HistoryFrequency?, unit: String?, localDate: LocalDate) {
        // All field are empty, just return to the metrics list without error
        if (StringUtil.isEmpty(name) && historyFrequency == null && StringUtil.isEmpty(unit)) {
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
            val metric = MetricDTO(name!!, historyFrequency!!, unit!!, localDate)
            val successHandler: (Long) -> Unit = { view.navigateToMetricsListWithNewMetricAdded(name) }
            val errorHandler = { e: Throwable -> view.navigateToMetricsListWithError(e.message ?: "new.metric.error") }

            val closeable = repo.saveMetric(metric)
                    .observeOn(schedulerProvider.ui())
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(successHandler, errorHandler)
            mCompositeDisposable.add(closeable)
        } else {
            // TODO
        }
    }
}