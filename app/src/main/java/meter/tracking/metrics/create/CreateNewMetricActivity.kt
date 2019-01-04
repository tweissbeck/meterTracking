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
import io.reactivex.disposables.CompositeDisposable
import meter.tracking.R
import meter.tracking.datasource.MeasuringTypeDataSource
import meter.tracking.datasource.MetricDataSource
import meter.tracking.db.model.HistoryFrequency
import meter.tracking.db.model.MeasuringType
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
    private val measuringTypeSource: MeasuringTypeDataSource by inject()
    private val schedulerProvider: SchedulerProvider by inject()
    private val compositeDisposable = CompositeDisposable()

    private lateinit var nameEditText: EditText
    private lateinit var unitSpinner: Spinner
    private lateinit var frequencySpinner: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_metric)

        this.presenter = CreateMetricPresenter(this, metricDataSource, schedulerProvider)

        // Get form fields
        this.nameEditText = findViewById(R.id.metric_name)
        this.frequencySpinner = findViewById(R.id.metric_type)
        this.unitSpinner = findViewById(R.id.metric_unit)

        // Menu
        val toolbar = findViewById<Toolbar>(R.id.create_metric_activity_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_clear_white)
            title = getString(R.string.action_new)
        }

        // Setup adapter for frequency spinner
        FrequencyAdapter(this).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            frequencySpinner.adapter = this
        }

        // Setup adapter for measuring type spinner
        val metricAdapter = MetricUnitAdapter(this).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            unitSpinner.adapter = this
        }
        val loadMetricUnitHandler = { result: List<MeasuringType> ->
            metricAdapter.clear()
            metricAdapter.addAll(result.map { measuringType -> measuringType.label })
            metricAdapter.notifyDataSetChanged()
        }
        // Launch async jobs to fill the measuring type spinner from data base
        val disposable = this.measuringTypeSource.getAll()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(loadMetricUnitHandler)
        this.compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.compositeDisposable.dispose()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.create_metric_activity_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_new_metric_save -> {
            val selectedFrequency: HistoryFrequency? = if (this.frequencySpinner.selectedItem != null) {
                this.frequencySpinner.selectedItem as HistoryFrequency
            } else {
                null
            }
            val selectedUnit: String? = if (this.unitSpinner.selectedItem != null) {
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
