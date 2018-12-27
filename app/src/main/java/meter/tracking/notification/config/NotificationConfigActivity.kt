package meter.tracking.notification.config

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author tweissbeck
 * @since 1.0.0
 */
class NotificationConfigActivity : AppCompatActivity(), NotificationConfigContract.View{

    override lateinit var presenter: NotificationConfigContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = NotificationConfigPresenter(this)
    }
}