package meter.tracking.launch

import meter.tracking.BaseView

/**
 * This specifies the contract between the view and the presenter in launcher
 *
 * @author tweissbeck
 * @since 1.0.0
 */
interface LaunchContract {

    interface View : BaseView<Presenter> {
        fun goToMainActivity()
    }

    interface Presenter {
        fun init()
    }
}