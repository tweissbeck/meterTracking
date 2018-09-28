package meter.tracking.db.model

/**
 * @author tweissbeck
 * @since 1.0.0
 */
enum class HistoryFrequency(private val translateKey: String) {
    DAILY("frequency_daily"),
    WEEKLY("frequency_weekly"),
    MONTHLY("frequency_monthly"),
    ANNUAL("frequency_annual");

    fun getKey(): String = translateKey
}