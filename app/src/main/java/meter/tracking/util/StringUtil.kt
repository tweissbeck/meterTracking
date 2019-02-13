package meter.tracking.util

/**
 * A string utility object to deal with String? checks
 * @author tweissbeck
 * @since 1.0.0
 */
object StringUtil {

    fun isEmpty(value: String?) = value == null || value.isEmpty()
}