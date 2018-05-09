package meter.tracking.metrics

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import meter.tracking.R
import meter.tracking.db.DataBase
import meter.tracking.metrics.view.MetricAdapter

/**
 * The main activity of the application, shows all counters in a recycler view
 * @author tweissbeck
 * @since 1.0.0
 */
class MetersTrackingActivity : AppCompatActivity() {

    private lateinit var metricAdapter: MetricAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = DataBase.getInstance(applicationContext)
        val repository = MetricRepository(db.metricDao)
        val metrics = repository.getMetrics()
        this.metricAdapter = MetricAdapter(metrics)

        list.apply {

        }
    }
}