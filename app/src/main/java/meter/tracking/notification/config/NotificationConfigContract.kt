package meter.tracking.notification.config

import meter.tracking.BaseView

/**
 * @author tweissbeck
 * @since 1.0.0
 */
interface NotificationConfigContract {

    interface View: BaseView<Presenter>

    interface Presenter
}