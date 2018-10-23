package meter.tracking.metrics.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import meter.tracking.R
import meter.tracking.datasource.MetricDataSource
import meter.tracking.db.model.MetricsWithRecord
import org.koin.android.ext.android.inject

class MetricDetailActivity : AppCompatActivity(), MetricDetailContract.MetricDetailView {

    companion object {
        const val INTENT_EXTRA_METRIC_ID = "id"
    }

    override lateinit var presenter: MetricDetailContract.MetricDetailPresenter
    private val metricDataSource: MetricDataSource by inject()
    private var metricId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metric_detail)
        presenter = MetricDetailPresenter(this, metricDataSource)

        val metricId = intent.getLongExtra(INTENT_EXTRA_METRIC_ID, -1L)

        this.metricId = metricId
        presenter.init(metricId)

        val toolbar = findViewById<Toolbar>(R.id.detail_metric_activity_tool_bar_xml)

        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_black)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.detail_metric_activity_tool_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_edit_metric_save -> true
        else -> super.onOptionsItemSelected(item)
    }

    // -- View interface

    override fun updateActivityTitle(value: String) {
        runOnUiThread {
            supportActionBar?.apply {
                title = getString(R.string.metric_detail_title, metricId.toString())

            }
        }
    }

    override fun updateData(value: MetricsWithRecord) {

    }

    override fun returnToMainWithError(errorId: String) {

    }
}
