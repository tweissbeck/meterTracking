package meter.tracking.metrics.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import meter.tracking.R
import meter.tracking.db.model.Metric
import meter.tracking.metrics.create.CreateNewMetricActivity
import meter.tracking.datasource.MetricDataSource
import meter.tracking.metrics.detail.MetricDetailActivity
import meter.tracking.metrics.main.view.MetricAdapter
import org.koin.android.ext.android.inject

/**
 * The main activity of the application, shows all counters in a recycler view
 *
 * @author tweissbeck
 * @since 1.0.0
 */
class MetersTrackingActivity : AppCompatActivity(), MetricMainContract.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MetricAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override lateinit var presenter: MetricMainContract.Presenter

    /**
     * [MetricRepository] loaded from Koin di
     */
    private val metricRepository: MetricDataSource by inject()
    private val compositeDisposable = CompositeDisposable()

    companion object {
        const val DISPLAY_ERROR: String = "display_error_message"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metrics_tracker_list)
        setSupportActionBar(findViewById(R.id.metric_activity_toolbar))

        this.presenter = MetricMainPresenter(this)
        this.viewManager = LinearLayoutManager(this)
        this.viewAdapter = MetricAdapter(this.presenter)

        recyclerView = findViewById<RecyclerView>(R.id.metrics_list_recycler_view).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
        val success: (List<Metric>) -> Unit = { result: List<Metric> ->
            viewAdapter.setUpData(result)
        }
        val disposable = metricRepository.getMetrics()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success)
        this.compositeDisposable.add(disposable)
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

    override fun onDestroy() {
        super.onDestroy()
        this.compositeDisposable.dispose()
    }

    override fun navigateToEditMetricActivity(metric: Metric) {
        val intent = Intent(this, MetricDetailActivity::class.java)
        intent.putExtra(MetricDetailActivity.INTENT_EXTRA_METRIC_ID, metric.id)
        startActivity(intent)
    }
}