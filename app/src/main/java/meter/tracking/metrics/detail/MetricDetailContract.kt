package meter.tracking.metrics.detail

import meter.tracking.BasePresenter
import meter.tracking.BaseView

/**
 * @author tweissbeck
 * @since 1.0.0
 */
interface MetricDetailContract {

    interface MetricDetailView : BaseView<MetricDetailPresenter>

    interface MetricDetailPresenter : BasePresenter<MetricDetailView>
}