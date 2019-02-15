package meter.tracking.unit

import junit.framework.Assert.assertEquals
import meter.tracking.db.model.HistoryFrequency
import meter.tracking.db.model.MetricRecord
import meter.tracking.db.model.MetricsWithRecord
import meter.tracking.metrics.detail.history.RecordHistory
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

/**
 * Unit testing of [meter.tracking.metrics.detail.history.RecordHistory]
 * @author tweissbeck
 * @since 1.0.0
 */
class RecordHistoryUnitTest {

    var metricId: Long = 1
    var recordId: Long = 1
    @Before
    fun init() {
        metricId = 1
        recordId = 1
    }

    @Test
    fun testEmpty() {
        val metricWithRecord = buildMetric(HistoryFrequency.DAILY, LocalDate.now())
        metricWithRecord.records = listOf(buildRecord(LocalDate.now(), 12))
        val history = RecordHistory(metricWithRecord).history
        assertEquals(1, history.size)
    }

    @Test
    fun testDays() {
        val metricWithRecord = buildMetric(HistoryFrequency.DAILY, LocalDate.now().minusDays(11))
        metricWithRecord.records = listOf(
                buildRecord(LocalDate.now(), 1),
                buildRecord(LocalDate.now().minusDays(2), 2),
                buildRecord(LocalDate.now().minusDays(4), 4),
                buildRecord(LocalDate.now().minusDays(5), 5),
                buildRecord(LocalDate.now().minusDays(10), 10),
                buildRecord(LocalDate.now().minusDays(11), 11)
        )
        val history = RecordHistory(metricWithRecord).history
        assertEquals(12, history.size)
        val iterator = history.iterator()
        var historyDate = LocalDate.now()

        // Check date & order
        iterator.forEach {
            assertEquals(historyDate, it.localDate)
            historyDate = historyDate.minusDays(1)
        }
    }

    @Test
    fun testMonth() {
        val metricsWithRecord = buildMetric(HistoryFrequency.MONTHLY, LocalDate.now().minusMonths(2))
        metricsWithRecord.records = listOf(
                buildRecord(LocalDate.now().minusMonths(2).withDayOfMonth(1), 30)
        )
        val history = RecordHistory(metricsWithRecord).history
        assertEquals(3, history.size)
        val iterator = history.iterator()
        var historyDate = LocalDate.now().withDayOfMonth(1)
        iterator.forEach {
            assertEquals(historyDate, it.localDate)
            historyDate = historyDate.minusMonths(1)
        }
    }

    private fun buildMetric(frequency: HistoryFrequency, startDate: LocalDate): MetricsWithRecord {
        return MetricsWithRecord("abc", metricId++, "MS", frequency, startDate)
    }

    private fun buildRecord(date: LocalDate, value: Long, metricId: Long? = null): MetricRecord {
        return MetricRecord(recordId++, metricId ?: this.metricId - 1, value, date)
    }
}