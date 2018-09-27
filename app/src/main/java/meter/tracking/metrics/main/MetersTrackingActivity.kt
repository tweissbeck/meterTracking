package meter.tracking.metrics.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import meter.tracking.R
import meter.tracking.metrics.create.CreateNewMetricActivity
import meter.tracking.metrics.main.view.MetricAdapter
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

    /**
     * [MetricRepository] loaded from Koin di
     */
    private val metricRepository: MetricRepository by inject()


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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.metric_activity_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_new_metric -> {
            val intent = Intent(this, CreateNewMetricActivity::class.java)
            startActivity(intent)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}