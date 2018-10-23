package meter.tracking.metrics.create

import meter.tracking.datasource.MetricDataSource

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class CreateMetricPresenter(private val view: CreateMetricContract.View, private val repo: MetricDataSource) :
        CreateMetricContract.Presenter {

    override fun createMetric(m: MetricDTO) {
        // TODO
    }

}