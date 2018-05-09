package meter.tracking.db.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Relation

/**
 * @author tweissbeck
 * @since 1.0.0
 */
@Entity(tableName = "metric")
class MetricsWithRecord(id: Long, value: Long, measureLabel: String, historyFrequency: HistoryFrequency,
                        @Relation(parentColumn = "id", entityColumn = "metric") val records: List<MetricRecord>) :
        Metric(id, value, measureLabel, historyFrequency)