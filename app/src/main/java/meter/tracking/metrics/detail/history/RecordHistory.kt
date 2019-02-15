package meter.tracking.metrics.detail.history

import meter.tracking.db.model.HistoryFrequency
import meter.tracking.db.model.MetricRecord
import meter.tracking.db.model.MetricsWithRecord
import java.time.LocalDate
import java.util.*

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class RecordHistory(metric: MetricsWithRecord) {

    val history: Collection<DateValueHolder>

    /**
     * A pair binding a [LocalDate] and a record value
     */
    class DateValueHolder(val localDate: LocalDate, val value: Long?) : Comparable<DateValueHolder> {
        override fun compareTo(other: DateValueHolder): Int {
            // Date near today first
            return other.localDate.compareTo(this.localDate)
        }

        override fun hashCode(): Int = localDate.hashCode()
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            other as DateValueHolder
            return localDate == other.localDate
        }
    }

    init {
        fun MetricRecord.toDateValueHolder(): DateValueHolder {
            return DateValueHolder(this.date, this.value)
        }

        val values = TreeSet<DateValueHolder>()
        // Add all value from the metric
        values.addAll(metric.records.map { it.toDateValueHolder() })

        // Complete with date from range not already set
        val dateRange = ReverseLocalDateRange(metric.historyFrequency.chronoUnit, metric.startDate, LocalDate.now())
        for (date in dateRange) {
            values.add(DateValueHolder(date, null))
        }

        this.history = values
    }
}