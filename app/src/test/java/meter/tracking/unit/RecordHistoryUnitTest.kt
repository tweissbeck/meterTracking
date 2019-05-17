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
        assertFalse(iterator.hasNext())
    }

    @Test
    fun testEmpty() {
        val dateRange = ReverseLocalDateRange(ChronoUnit.DAYS, LocalDate.now(), LocalDate.now())
        val now = LocalDate.now()
        assertTrue(dateRange.contains(now))

        val iterator = dateRange.iterator()
        assertEquals(now, iterator.next())
        assertFalse(iterator.hasNext())
    }

    @Test
    fun testAMonth() {
        val firstMonthDay = LocalDate.of(2019, 1, 1)
        val lastMonthDay = LocalDate.of(2019, 1, 31)
        val dateRange = ReverseLocalDateRange(ChronoUnit.DAYS, firstMonthDay, lastMonthDay)
        var current = lastMonthDay
        dateRange.forEach {
            assertEquals(current, it)
            current = current.minusDays(1)
        }
        assertEquals(firstMonthDay, current.plusDays(1))
    }

    @Test
    fun testMonthly() {
        val start = LocalDate.of(2018, 4, 2)
        val stop = LocalDate.of(2019, 11, 4)
        val dateRange = ReverseLocalDateRange(ChronoUnit.MONTHS, start, stop)

        var current = stop.withDayOfMonth(1)
        dateRange.forEach {
            assertEquals(current, it)
            current = current.minusMonths(1)
        }
        assertEquals(start.withDayOfMonth(1), current.plusMonths(1))
    }

}