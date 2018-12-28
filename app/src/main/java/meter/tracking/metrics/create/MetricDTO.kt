package meter.tracking.metrics.create

import meter.tracking.db.model.HistoryFrequency

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class MetricDTO(val name: String, val type: HistoryFrequency, val unit: String)
