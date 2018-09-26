package meter.tracking.metrics.create

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Spinner
import meter.tracking.R

/**
 * This activity allows to create a new metric
 */
class CreateNewMetricActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_metric)

        val actionBar = findViewById<Toolbar>(R.id.create_metric_activity_toolbar)
        setSupportActionBar(actionBar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_clear_white)
            setTitle(R.string.action_new)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.create_metric_activity_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        R.id.action_new_metric_save -> {
            val name = findViewById<EditText>(R.id.metric_name)
            val type  =findViewById<Spinner>(R.id.metric_type)
            val unit = findViewById<Spinner>(R.id.metric_unit)

            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
