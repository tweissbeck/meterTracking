package meter.tracking.metrics.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import meter.tracking.R
import meter.tracking.datasource.MetricDataSource
import meter.tracking.db.model.HistoryFrequency
import meter.tracking.metrics.detail.history.DateValueHolder
import meter.tracking.metrics.main.MetersTrackingActivity
import meter.tracking.rx.SchedulerProvider
import meter.tracking.util.LocalizedStringIdHelper
import org.koin.android.ext.android.inject

/**
 * Display metric detail
 */
class MetricDetailActivity : AppCompatActivity(), MetricDetailContract.MetricDetailView {

    companion object {
        const val INTENT_EXTRA_METRIC_ID = "id"
    }

    override lateinit var presenter: MetricDetailContract.MetricDetailPresenter
    private val metricDataSource: MetricDataSource by inject()
    private val schedulerProvider: SchedulerProvider by inject()


    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MetricDetailAdapter

    private lateinit var totalTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metric_detail)
        this.viewManager = LinearLayoutManager(this)
        val metricId = intent.getLongExtra(INTENT_EXTRA_METRIC_ID, -1L)

        this.presenter = MetricDetailPresenter(this, this.metricDataSource, this.schedulerProvider)
        this.presenter.init(metricId)

        val toolbar = findViewById<Toolbar>(R.id.detail_metric_activity_tool_bar_xml)

        setSupportActionBar(toolbar)
        this.supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_black)
        }

        // Bind recycler view
        this.viewAdapter = MetricDetailAdapter()
        recyclerView = findViewById<RecyclerView>(R.id.metrics_detail_record_list).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        // Bind texts views
        this.totalTextView = findViewById(R.id.detail_total_value)
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
        supportActionBar?.apply {
            title = getString(R.string.metric_detail_title, value)
        }
    }

    override fun updateData(data: Collection<DateValueHolder>, total: String) {
        /**
         * Return true if the metric is up to date.
         * A metric is update when its last record date is in time with its [HistoryFrequency]
         */
//        fun isMetricUpdateToDate(): Boolean {
//            val lastRecord: MetricRecord? = data.records.firstOrNull()
//            val recordDate: LocalDate? = lastRecord?.date
//            val now: LocalDate = LocalDate.now()
//            return recordDate != null && when (data.historyFrequency) {
//                HistoryFrequency.DAILY -> now.isEqual(recordDate)
//                HistoryFrequency.WEEKLY -> DAYS.between(recordDate, now) < 8
//                HistoryFrequency.MONTHLY -> DAYS.between(recordDate, now) < 31
//                HistoryFrequency.ANNUAL -> DAYS.between(recordDate, now) < 366
//            }
//        }
        this.viewAdapter.setData(data)
        // Now update the view
        this.totalTextView.text = total

    }

    override fun returnToMainWithError(errorId: String, args: Array<Any>?) {
        val intent = Intent(this, MetersTrackingActivity::class.java)
        intent.putExtra(MetersTrackingActivity.DISPLAY_ERROR,
                        LocalizedStringIdHelper.getLocalized(errorId, args, this.applicationContext))
        startActivity(intent)
    }
}
