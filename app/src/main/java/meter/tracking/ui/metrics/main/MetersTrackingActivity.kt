package meter.tracking.ui.metrics.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import meter.tracking.R
import meter.tracking.db.model.HistoryFrequency
import meter.tracking.db.model.MetricEntity
import meter.tracking.ui.metrics.create.CreateNewMetricActivity
import meter.tracking.ui.metrics.detail.MetricDetailActivity
import meter.tracking.ui.metrics.main.view.MetricAdapter
import meter.tracking.ui.common.OnSelectRow
import meter.tracking.ui.settings.SettingsActivity
import meter.tracking.util.StringUtil
import meter.tracking.viewmodel.MetricViewModel
import java.time.LocalDate

/**
 * The main activity of the application, shows all counters in a recycler view
 *
 * @author tweissbeck
 * @since 1.0.0
 */
class MetersTrackingActivity : AppCompatActivity(), OnSelectRow<MetricEntity> {

    private val NEW_METRIC_ACTIVITY_REQUEST_CODE = 1
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MetricAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var metricViewModel: MetricViewModel

    companion object {
        const val TAG = "MetersTrackingActivity"
        const val DISPLAY_ERROR: String = "display_error_message"
        const val TOAST_MESSAGE: String = "display_toast"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metrics_tracker_list)
        setSupportActionBar(findViewById(R.id.metric_activity_toolbar))

        this.viewManager = LinearLayoutManager(this)
        this.viewAdapter = MetricAdapter(this, this)
        metricViewModel = ViewModelProvider(this).get(MetricViewModel::class.java)
        metricViewModel.metrics.observe(this, Observer { metrics ->
            viewAdapter.setMetrics(metrics)
        })

        recyclerView = findViewById<RecyclerView>(R.id.metrics_list_recycler_view).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        /**
         * From 'new metric' activity
         */
        if (requestCode == NEW_METRIC_ACTIVITY_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    data?.let {
                        val name: String? = it.getStringExtra(CreateNewMetricActivity.EXTRA_METRIC_NAME)
                        val unit: String? = it.getStringExtra(CreateNewMetricActivity.EXTRA_METRIC_UNIT)
                        val frequency: String? = it.getStringExtra(CreateNewMetricActivity.EXTRA_METRIC_FREQUENCY)
                        val date: LocalDate? = it.getSerializableExtra(CreateNewMetricActivity.EXTRA_METRIC_DATE) as LocalDate
                        val initialValue = it.getLongExtra(CreateNewMetricActivity.EXTRA_METRIC_INITIAL_VALUE, 0)

                        if (StringUtil.isNotEmpty(name) && StringUtil.isNotEmpty(unit) && StringUtil.isNotEmpty(frequency) && date != null) {
                            metricViewModel.insert(MetricEntity(name!!,
                                    initialValue,
                                    unit!!,
                                    HistoryFrequency.valueOf(frequency!!),
                                    date))
                        } else {
                            Log.i(TAG, "Error some arg empty!")
                        }
                    }
                }
                Activity.RESULT_CANCELED -> {
                    Log.i(TAG, "User cancelled metric creation")
                }
                else -> {
                    Log.i(TAG, "Dunno")
                }
            }
        } else {
            Log.i(TAG, "onActivityResult => request code $requestCode not handled ")
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
            startActivityForResult(intent, NEW_METRIC_ACTIVITY_REQUEST_CODE)
            true
        }
        R.id.action_settings -> {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            true
        }
        R.id.action_export_counters -> {
//            val dialog = ExportCountersDialog(null, presenter::onExportMetric)
//            dialog.show(supportFragmentManager, ExportCountersDialog.TAG)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    fun navigateToEditMetricActivity(metric: MetricEntity) {
        val intent = Intent(this, MetricDetailActivity::class.java)
        intent.putExtra(MetricDetailActivity.INTENT_EXTRA_METRIC_ID, metric.id)
        startActivity(intent)
    }

    override fun selectRow(row: MetricEntity) {
        val intent = Intent(this, MetricDetailActivity::class.java)
        intent.putExtra(MetricDetailActivity.INTENT_EXTRA_METRIC_ID, row.id)
        startActivity(intent)
    }
}