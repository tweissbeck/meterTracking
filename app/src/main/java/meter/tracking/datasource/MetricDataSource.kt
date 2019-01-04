package meter.tracking.datasource

import io.reactivex.Maybe
import io.reactivex.Single
import meter.tracking.db.model.MeasuringType
import meter.tracking.db.model.Metric
import meter.tracking.db.model.MetricsWithRecord
import meter.tracking.metrics.create.MetricDTO

/**
 * @author tweissbeck
 * @since 1.0.0
 */
interface MetricDataSource {

    /**
     * A hook if some stuff has to be done when application is launching. Eg: put in cache some data
     */
    fun init()

    fun getMetrics(): Single<List<Metric>>

    fun getMetric(id: Long): Maybe<MetricsWithRecord>

    fun saveMetric(dto: MetricDTO): Single<Long>

    fun saveMetrics(metrics: Array<Metric>): Single<List<Long>>
}