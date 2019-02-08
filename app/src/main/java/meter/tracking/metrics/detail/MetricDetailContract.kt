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
         * @param value the diplayed title
         */
        fun updateActivityTitle(value: String)

        /**
         * Update metric fields
         */
        fun updateData(data: MetricsWithRecord)

        /**
         * Return to main activity and display a toast with error message
         * @param errorId the message key
         * @param args message arguments, can be null
         */
        fun returnToMainWithError(errorId: String, args: Array<Any>? = null)
    }

    interface MetricDetailPresenter : BasePresenter<MetricDetailView> {
        fun init(deviceId: Long)

        fun onDestroy()
    }
}