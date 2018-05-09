package meter.tracking.launch

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import meter.tracking.metrics.MetersTrackingActivity
import meter.tracking.R
import meter.tracking.db.DataBase
import meter.tracking.db.MetricsDataBase
import meter.tracking.metrics.MetricRepository

class LaunchActivity : AppCompatActivity(), LaunchContract.View {

    override lateinit var presenter: LaunchContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        val db: MetricsDataBase = DataBase.getInstance(applicationContext)
        val metricRepository = MetricRepository(db.metricDao)
        presenter = LaunchPresenter(this, metricRepository)
        presenter.init()
    }

    override fun goToMainActivity() {
        val intent = Intent(this@LaunchActivity, MetersTrackingActivity::class.java)
        startActivity(intent)
    }

}
