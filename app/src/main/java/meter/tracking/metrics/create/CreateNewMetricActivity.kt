package meter.tracking.metrics.create

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import meter.tracking.R
import meter.tracking.datasource.MetricDataSource
import meter.tracking.db.model.HistoryFrequency
import meter.tracking.metrics.main.MetersTrackingActivity
import meter.tracking.metrics.main.MetersTrackingActivity.Companion.TOAST_MESSAGE
import meter.tracking.rx.SchedulerProvider
import org.koin.android.ext.android.inject

/**
 * This activity allows to create a new metric
 */
class CreateNewMetricActivity : AppCompatActivity(), CreateMetricContract.View {

    override lateinit var presenter: CreateMetricContract.Presenter

    private val metricDataSource: MetricDataSource by inject()
    private val schedulerProvider: SchedulerProvider by inject()

    private lateinit var nameEditText: EditText
    private lateinit var unitSpinner: Spinner
    private lateinit var frequencySpinner: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.presenter = CreateMetricPresenter(this, metricDataSource, schedulerProvider)

        setContentView(R.layout.activity_create_new_metric)

        this.nameEditText = findViewById(R.id.metric_name)
        this.frequencySpinner = findViewById(R.id.metric_type)
        this.unitSpinner = findViewById(R.id.metric_unit)

        val toolbar = findViewById<Toolbar>(R.id.create_metric_activity_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_clear_white)
            title = getString(R.string.action_new)
        }

        val spinner: Spinner = findViewById(R.id.metric_type)
        FrequencyAdapter(this).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = this
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.create_metric_activity_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_new_metric_save -> {
            val selectedFrequency: HistoryFrequency? = if (this.frequencySpinner.isSelected) {
                this.frequencySpinner.selectedItem as HistoryFrequency
            } else {
                null
            }
            val selectedUnit: String? = if (this.unitSpinner.isSelected) {
                this.unitSpinner.selectedItem as String
            } else {
                null
            }
            this.presenter.createMetric(this.nameEditText.text.toString(), selectedFrequency, selectedUnit)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    /* FROM CreateMetricContract.View */

    override fun navigateToMetricsListWithNewMetricAdded(metricName: String) {
        this.navigateToMetricList { intent ->
            intent.putExtra(TOAST_MESSAGE, getString(R.string.toast_new_metric_added, metricName))
        }
    }

    override fun navigateToMetricsListWithError(error: String) {
        this.navigateToMetricList { intent ->
            intent.putExtra(TOAST_MESSAGE, getString(R.string.toast_new_metric_error, error))
        }
    }

    override fun navigateToMetricsList() {
        this.navigateToMetricList()
    }

    override fun displayEmptyNameError() {
        this.nameEditText.error = getString(R.string.error_empty_edit_text)
    }

    override fun displayEmptyFrequency() {
        setErrorOnSpinner(this.frequencySpinner, getString(R.string.error_empty_edit_text))
    }

    override fun displayEmptyUnit() {
        setErrorOnSpinner(this.unitSpinner, getString(R.string.error_empty_edit_text))
    }

    /* End CreateMetricContract.View impl*/

    private fun setErrorOnSpinner(s: Spinner, error: String) {
        val textView: TextView = s.selectedView as TextView
        textView.error = ""
        textView.text = error
        textView.setTextColor(Color.RED)
    }

    private fun navigateToMetricList(intentConsumer: ((Intent) -> Unit)? = null) {
        val intent = Intent(this@CreateNewMetricActivity, MetersTrackingActivity::class.java)
        intentConsumer?.invoke(intent)
        startActivity(intent)
    }
}
