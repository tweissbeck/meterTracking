package meter.tracking.db.model

import androidx.room.Embedded
import androidx.room.Relation

class MetricWithRecord(@Embedded val metric: MetricEntity,
                       @Relation(parentColumn = "id", entityColumn = "metricId")
                            val records: List<MetricRecordEntity>) {

}