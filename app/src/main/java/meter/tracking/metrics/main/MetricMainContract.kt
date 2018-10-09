package meter.tracking.metrics.main

import meter.tracking.BaseView

/**
 * @author tweissbeck
 * @since 1.0.0
 */
interface MetricMainContract {

    interface View : BaseView<Presenter>

    interface Presenter : MetricRecyclerViewPresenter
}