package meter.tracking

/**
 * Base view interface in MVP pattern
 * @author tweissbeck
 * @since 1.0.0
 */
interface BaseView<T> {
    var presenter: T
}