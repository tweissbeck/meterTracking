package meter.tracking.ui.launch

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import meter.tracking.R
import meter.tracking.ui.metrics.main.MetersTrackingActivity
import meter.tracking.rx.SchedulerProvider
import org.koin.android.ext.android.inject

/**
 * A standard launch activity with splash screen.
 *
 * @author tweissbeck
 * @since 1.0.0
 */
class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        goToMainActivity()
    }

    private fun goToMainActivity() {
        val intent = Intent(this@LaunchActivity, MetersTrackingActivity::class.java)
        startActivity(intent)
    }

}
