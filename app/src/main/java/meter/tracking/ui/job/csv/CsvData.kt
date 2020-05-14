package meter.tracking.ui.job.csv

import meter.tracking.db.model.MetricWithRecord
import java.time.format.DateTimeFormatter

class CsvData(metrics: List<MetricWithRecord>) {

    private val rows: Iterable<String>

    init {
        rows = metrics.flatMap {
            val metricsBeginRow = "\"${it.metric.name}\";\"${it.metric.startDate.format(DateTimeFormatter.BASIC_ISO_DATE)}\";"
            it.records.map { record ->
                metricsBeginRow + "\"${record.date.format(DateTimeFormatter.BASIC_ISO_DATE)}\";\"${record.value}\""
            }
        }
    }

    fun getRows(): Iterable<String> {
        return rows
    }
}