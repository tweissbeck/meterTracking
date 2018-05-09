package meter.tracking.launch

import meter.tracking.metrics.MetricDataSource


/**
 *
 * @author tweissbeck
 * @since 1.0.0
 */
class LaunchPresenter(private val view: LaunchContract.View,
                      private val metricsRepo: MetricDataSource) : LaunchContract.Presenter,
        LaunchPresenterLoadingContract {
    private val TAG = "LaunchPresenter"


    init {
        view.presenter = this
    }

    override fun onLoadFinished() {
        view.goToMainActivity()
    }

    override fun init() {
        // Load the task, put them in cache if the repository handle it.
        metricsRepo.getMetrics()
    }

    override fun load() {
        LoadResourceTask(metricsRepo, { onLoadFinished() }).execute(Unit)
    }


}