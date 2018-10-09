package meter.tracking.launch

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import meter.tracking.R
import meter.tracking.metrics.main.MetersTrackingActivity
import meter.tracking.metrics.main.MetricRepository
import org.koin.android.ext.android.inject

/**
 * A standard launch activity with splash screen.
 * Using MVP pattern, init stuff is done into [LaunchPresenter]
 *
 * @author tweissbeck
 * @since 1.0.0
 */
class LaunchActivity : AppCompatActivity(), LaunchContract.View {

    override lateinit var presenter: LaunchContract.Presenter
    private val repository: MetricRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        presenter = LaunchPresenter(this, repository)
        presenter.init()
    }

    override fun goToMainActivity() {
        val intent = Intent(this@LaunchActivity, MetersTrackingActivity::class.java)
        startActivity(intent)
    }
}
