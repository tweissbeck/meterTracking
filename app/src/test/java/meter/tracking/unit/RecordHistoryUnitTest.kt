package meter.tracking.unit

import meter.tracking.metrics.detail.history.ReverseLocalDateRange
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import java.time.temporal.ChronoUnit

/**
 * Unit testing of [meter.tracking.metrics.detail.history.ReverseLocalDateRange]
 * @author tweissbeck
 * @since 1.0.0
 */
class RecordHistoryUnitTest {

    private var metricId: Long = 1
    private var recordId: Long = 1
    @Before
    fun init() {
        metricId = 1
        recordId = 1
    }

    @Test
    fun testFewDay() {
        val dateRange = ReverseLocalDateRange(ChronoUnit.DAYS, LocalDate.now().minusDays(1), LocalDate.now())

        val now = LocalDate.now()
        assertTrue(dateRange.contains(now))
        assertTrue(dateRange.contains(now.minusDays(1)))
        assertFalse(dateRange.contains(now.minusDays(2)))
        assertFalse(dateRange.contains(now.plusDays(1)))

        val iterator = dateRange.iterator()
        assertEquals(now, iterator.next())
        assertEquals(now.minusDays(1), iterator.next())
    }

}