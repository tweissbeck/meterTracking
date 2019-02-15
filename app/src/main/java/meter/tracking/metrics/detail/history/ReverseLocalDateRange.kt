package meter.tracking.metrics.detail.history

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoUnit

/**
 * A range that define all [LocalDate] between a start date and an end date with a [temporalUnit] interval. This range return date in reverse order:
 * @param temporalUnit the range interval. Only [ChronoUnit.DAYS], [ChronoUnit.MONTHS] or [ChronoUnit.YEARS] are possible values
 * @author tweissbeck
 * @since 1.0.0
 * @throws IllegalArgumentException if temporalUnit is not set to one of its possible values.
 */
class ReverseLocalDateRange(private val temporalUnit: ChronoUnit, override val start: LocalDate,
                            override val endInclusive: LocalDate) : ClosedRange<LocalDate>, Iterable<LocalDate> {

    private val _startDate: LocalDate
    private val _endDate: LocalDate

    init {
        when (temporalUnit) {
            ChronoUnit.DAYS -> {
                _startDate = start
                _endDate = endInclusive
            }
            ChronoUnit.WEEKS -> {
                _startDate = getMondayWeekDate(start)
                _endDate = getMondayWeekDate(endInclusive)
            }
            ChronoUnit.MONTHS -> {
                _startDate = start.withDayOfMonth(1)
                _endDate = endInclusive.withDayOfMonth(1)
            }
            ChronoUnit.YEARS -> {
                _startDate = start.withDayOfYear(1)
                _endDate = endInclusive.withDayOfYear(1)
            }
            else -> throw IllegalArgumentException(
                    "temporalUnit argument can only takes ChronoUnit.DAYS,ChronoUnit.MONTHS or ChronoUnit.YEARS as value")
        }
    }

    private fun getMondayWeekDate(date: LocalDate): LocalDate {
        val dayOfWeek: DayOfWeek = date.dayOfWeek
        return date.minusDays((dayOfWeek.value - 1).toLong())
    }

    override fun iterator(): Iterator<LocalDate> {
        return object : Iterator<LocalDate> {
            private val start: LocalDate = this@ReverseLocalDateRange._startDate
            private val end: LocalDate = this@ReverseLocalDateRange._endDate
            private val temporalUnit: ChronoUnit = this@ReverseLocalDateRange.temporalUnit
            private var current = end
            override fun hasNext(): Boolean = current > start

            override fun next(): LocalDate {
                val result = current
                current = when (temporalUnit) {
                    ChronoUnit.DAYS -> current.minusDays(1)
                    ChronoUnit.WEEKS -> current.minusWeeks(1)
                    ChronoUnit.MONTHS -> current.minusMonths(1)
                    ChronoUnit.YEARS -> current.minusYears(1)
                    else -> throw IllegalArgumentException(
                            "temporalUnit argument can only takes ChronoUnit.DAYS, ChronoUnit.MONTHS or ChronoUnit.YEARS as value")
                }
                return result
            }

        }
    }
}