package meter.tracking.launch

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import meter.tracking.R
import meter.tracking.di.ApplicationModule.appModule
import meter.tracking.metrics.MetersTrackingActivity
import meter.tracking.metrics.MetricRepository
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin

/**
 * A standard launch activity with splash screen.
 * Using MVP pattern, init stuff is done into [LaunchPresenter]
 * @author tweissbeck
 * @since 1.0.0
 */
class LaunchActivity : AppCompatActivity(), LaunchContract.View {

    override lateinit var presenter: LaunchContract.Presenter
    private val repository: MetricRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        // Start Koin DI. Modules definition are in ApplicationModule object
        startKoin(this, listOf(appModule))

        presenter = LaunchPresenter(this, repository)
        presenter.init()
    }

    override fun goToMainActivity() {
        val intent = Intent(this@LaunchActivity, MetersTrackingActivity::class.java)
        startActivity(intent)
    }

}
