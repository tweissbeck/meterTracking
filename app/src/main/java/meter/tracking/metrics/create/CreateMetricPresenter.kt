package meter.tracking.metrics.create

import meter.tracking.db.model.Metric
import meter.tracking.metrics.main.MetricDataSource

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class CreateMetricPresenter(private val view: CreateMetricContract.View, private val repo: MetricDataSource) :
        CreateMetricContract.Presenter,
        MetricDataSource.SaveMetricCallBack {

    override fun createMetric(m: MetricDTO) {

        repo.saveMetric(m, this)

    }

    private fun metricSaveHandler(metric: Metric?) {
        if (metric != null) {
            view.showMetricListWithResult("")
        } else {
            view.showMetricListWithError("")
        }

    }

    // ------ MetricDataSource.SaveMetricCallBack --------

    override fun onSucess(metric: Metric) {
        this.metricSaveHandler(metric)
    }

    override fun onDBError() {
        this.metricSaveHandler(null)
    }
}