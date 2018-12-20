package meter.tracking.util

import android.content.Context

/**
 * Utility class to find the string id from the message key
 * @author tweissbeck
 * @since 1.0.0
 */
object LocalizedStringIdHelper {

    fun getLocalized(errorKey: String, args: Array<Any>? = null, context: Context): String {
        val identifier = context.resources.getIdentifier(errorKey, "string", context.packageName)
        return if (args != null) {
            context.resources.getString(identifier, errorKey)
        } else {
            context.resources.getString(identifier)
        }
    }
}