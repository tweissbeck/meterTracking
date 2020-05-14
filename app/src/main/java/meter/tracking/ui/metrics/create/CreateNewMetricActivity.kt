package meter.tracking.ui.metrics.create

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import meter.tracking.R
import meter.tracking.db.model.HistoryFrequency
import meter.tracking.util.StringUtil
import meter.tracking.viewmodel.MetricUnitViewModel
import java.io.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.stream.Collectors

/**
 * This activity allows to create a new metric. This activity return a response to the calling activity.
 * The response contains the created metrics fields
 */
class CreateNewMetricActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    companion object {
        const val EXTRA_METRIC_INITIAL_VALUE = "metric_initial_value"
        const val EXTRA_METRIC_NAME = "metric_name"
        const val EXTRA_METRIC_UNIT = "metric_unit"
        const val EXTRA_METRIC_FREQUENCY = "metric_frequency"
        const val EXTRA_METRIC_DATE = "metric_date"
    }

    private lateinit var nameInputLayout: TextInputLayout
    private lateinit var unitExposedDropDown: AutoCompleteTextView
    private lateinit var frequencyInputLayout: TextInputLayout
    private lateinit var frequencyExposedDropDown: AutoCompleteTextView
    private lateinit var startDateInputLayout: TextInputLayout
    private lateinit var initialValueInputLayout: TextInputLayout

    private lateinit var metricUnitViewModel: MetricUnitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_create_new_metric)

        // Get form fields
        this.nameInputLayout = findViewById(R.id.metric_name)
        this.frequencyExposedDropDown = findViewById(R.id.metric_type_filled_exposed_dropdown)
        this.frequencyInputLayout = findViewById(R.id.metric_type)
        this.unitExposedDropDown = findViewById(R.id.metric_unit_filled_exposed_dropdown)
        this.startDateInputLayout = findViewById(R.id.metric_start_date)
        this.initialValueInputLayout = findViewById(R.id.metric_initial_value)

        // Menu
        val toolbar = findViewById<Toolbar>(R.id.create_metric_activity_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_clear_white)
            title = getString(R.string.action_new)
        }

        this.metricUnitViewModel = ViewModelProvider(this).get(MetricUnitViewModel::class.java)

        // Setup adapter for frequency spinner
        FrequencyAdapter(this).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            frequencyExposedDropDown.setAdapter(this)
        }

        // Setup adapter for measuring type spinner
        val metricUnitAdapter = MetricUnitAdapter(this).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            unitExposedDropDown.setAdapter(this)
        }
        this.metricUnitViewModel.datas.observe(this, Observer { metricsUnit ->
            val units = metricsUnit.stream().map { it.label }.collect(Collectors.toList())
            metricUnitAdapter.addAll(units)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.create_metric_activity_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_new_metric_save -> {
                onSave()
            }
            else -> {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }

        return true
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        // Update the date text view with value obtain from the picker
        val dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
        this@CreateNewMetricActivity.startDateInputLayout.editText?.setText(LocalDate.of(year, month, dayOfMonth).format(
                dateFormatter))
    }

    private fun onSave() {
        val selectedFrequency: String = this.frequencyExposedDropDown.text.toString()
        val selectedUnit: String = this.unitExposedDropDown.text.toString()
        var error = false
        if (StringUtil.isEmpty(selectedFrequency)) {
            this.frequencyInputLayout.error = getString(R.string.field_required)
            error = true
        }

        if (StringUtil.isEmpty(selectedUnit)) {
            this.unitExposedDropDown.error = getString(R.string.field_required)
            error = true
        }

        if (StringUtil.isEmpty(this.nameInputLayout.editText?.text.toString())) {
            this.nameInputLayout.error = getString(R.string.field_required)
            error = true
        }

        if(StringUtil.isEmpty(this.startDateInputLayout.editText?.text.toString())){
            this.startDateInputLayout.error = getString(R.string.field_required)
            error = true
        }

        if(StringUtil.isEmpty(this.initialValueInputLayout.editText?.text.toString())){
            this.initialValueInputLayout.error = getString(R.string.field_required)
            error = true
        }

        if (!error) {
            val dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
            val selectedDate: LocalDate = LocalDate.parse(this.startDateInputLayout.editText?.text, dateFormatter)
            val replyIntent = Intent()
            replyIntent.putExtra(EXTRA_METRIC_FREQUENCY, selectedFrequency)
            replyIntent.putExtra(EXTRA_METRIC_UNIT, selectedUnit)
            replyIntent.putExtra(EXTRA_METRIC_NAME, this.nameInputLayout.editText?.text.toString())
            replyIntent.putExtra(EXTRA_METRIC_DATE, selectedDate as Serializable)
            replyIntent.putExtra(EXTRA_METRIC_INITIAL_VALUE, this.initialValueInputLayout.editText?.text.toString().toLong())
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }
    }

    fun onDateClickListener(view: View) {
        val d = LocalDate.now()
        val dialog = DatePickerDialog(this@CreateNewMetricActivity, this, d.year, d.monthValue, d.dayOfMonth)
        dialog.show()
    }

}
