package meter.tracking.metrics

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_metrics_tracker_list.*
import meter.tracking.R
import meter.tracking.R.id.list
import meter.tracking.db.DataBase
import meter.tracking.metrics.view.MetricAdapter
import org.koin.android.ext.android.inject

/**
 * The main activity of the application, shows all counters in a recycler view
 * @author tweissbeck
 * @since 1.0.0
 */
class MetersTrackingActivity : AppCompatActivity() {

    private lateinit var metricAdapter: MetricAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private val metricRepository:MetricRepository by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metrics_tracker_list)

        this.layoutManager = LinearLayoutManager(this)
        this.metricAdapter = MetricAdapter(metricRepository.getMetrics())

    }
}