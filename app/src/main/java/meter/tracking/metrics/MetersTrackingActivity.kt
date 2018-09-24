package meter.tracking.metrics

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import meter.tracking.R
import meter.tracking.metrics.view.MetricAdapter
import org.koin.android.ext.android.inject

/**
 * The main activity of the application, shows all counters in a recycler view
 * @author tweissbeck
 * @since 1.0.0
 */
class MetersTrackingActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val metricRepository:MetricRepository by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metrics_tracker_list)
        setSupportActionBar(findViewById(R.id.metric_activity_toolbar))

        this.viewManager = LinearLayoutManager(this)
        this.viewAdapter = MetricAdapter(metricRepository.getMetrics())

        recyclerView = findViewById<RecyclerView>(R.id.metrics_list_recycler_view).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

    }
}