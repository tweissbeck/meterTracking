package meter.tracking.util

/**
 * @author tweissbeck
 * @since 1.0.0
 */
open class Singleton<out T, in A>(private val create: (A) -> T) {

    @Volatile
    private var instance: T? = null

    open fun getInstance(args: A): T {
        return instance ?: return synchronized(this) {
            instance ?: create(args)
        }
    }
}