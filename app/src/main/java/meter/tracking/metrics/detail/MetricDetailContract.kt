package meter.tracking.metrics.detail

import meter.tracking.BasePresenter
import meter.tracking.BaseView
import meter.tracking.db.model.MetricsWithRecord

/**
 * @author tweissbeck
 * @since 1.0.0
 */
interface MetricDetailContract {

    interface MetricDetailView : BaseView<MetricDetailPresenter> {
        /**
         * Update the activity title
         */
        fun updateActivityTitle(value: String)

        /**
         * Update metric fields
         */
        fun updateData(value: MetricsWithRecord)

        /**
         * Return to main activity and display a toast with error message
         */
        fun returnToMainWithError(errorId: String)
    }

    interface MetricDetailPresenter : BasePresenter<MetricDetailView> {
        fun init(deviceId: Long)

        fun onDestroy()
    }
}