package meter.tracking.metrics.detail

import meter.tracking.db.model.MetricRecord

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricWithRecordDto(val name: String, val value: Long, l: List<MetricRecord>) {

}