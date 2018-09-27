package meter.tracking.db.model

/**
 * @author tweissbeck
 * @since 1.0.0
 */
enum class HistoryFrequency(private val translateKey: String) {
    DAILY("frequency.daily"),
    WEEKLY("frequency.weekly"),
    MONTHLY("frequency.monthly"),
    ANNUAL("frequency.annual");

    fun getKey(): String = translateKey
}