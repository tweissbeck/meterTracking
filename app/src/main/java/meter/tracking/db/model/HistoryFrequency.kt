package meter.tracking.db.model

import java.time.temporal.ChronoUnit

/**
 * @author tweissbeck
 * @since 1.0.0
 */
enum class HistoryFrequency(private val translateKey: String, val chronoUnit: ChronoUnit) {
    DAILY("frequency_daily", ChronoUnit.DAYS),
    WEEKLY("frequency_weekly", ChronoUnit.WEEKS),
    MONTHLY("frequency_monthly", ChronoUnit.MONTHS),
    ANNUAL("frequency_annual", ChronoUnit.YEARS);

    fun getKey(): String = translateKey
}