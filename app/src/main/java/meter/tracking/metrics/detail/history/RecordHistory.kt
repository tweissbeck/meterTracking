package meter.tracking.metrics.detail.history

import meter.tracking.db.model.MetricRecord
import meter.tracking.db.model.MetricsWithRecord
import java.time.LocalDate

typealias RecordData = Pair<LocalDate, Long?>

fun MetricRecord.toDisplayForm(): RecordData {
    return Pair(this.date, this.value)
}

/**
 * @author tweissbeck
 * @since 1.0.0
 */
fun buildRecordHistory(metric: MetricsWithRecord): Collection<RecordData> {

    fun <T> getIteratorValueOrNull(iter: Iterator<T>): T? = if (iter.hasNext()) iter.next() else null

    val reverseLocalDateRange = ReverseLocalDateRange(metric.historyFrequency.chronoUnit, metric.startDate, LocalDate.now())
    val dateRangeInter = reverseLocalDateRange.iterator()
    val result = mutableListOf<RecordData>()
    val metricIter = metric.records.sortedByDescending { it.date }.iterator()
    var currentMetric: MetricRecord? = getIteratorValueOrNull(metricIter)
    while (dateRangeInter.hasNext()) {
        val date = dateRangeInter.next()
        result.add(if (currentMetric != null) {
            if (currentMetric.date == date) {
                val pair = Pair(date, currentMetric.value)
                currentMetric = getIteratorValueOrNull(metricIter)
                pair
            } else {
                Pair(date, null)
            }
        } else {
            Pair(date, null)
        })
    }
    return result

}