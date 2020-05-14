package meter.tracking.ui

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import meter.tracking.R
import meter.tracking.koin.ApplicationModule
import meter.tracking.ui.notification.ChannelID
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MetricApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin DI. Modules definition are in ApplicationModule object
        startKoin{
            androidContext(this@MetricApp)
            modules(ApplicationModule.appModule)
        }

        createNotificationChannel()
    }

    /**
     * 'Because you must create the notification channel before posting any notifications on
     * Android 8.0 and higher, you should execute this code as soon as your app starts.
     * It's safe to call this repeatedly because creating an existing notification channel
     * performs no operation.'
     * @see https://developer.android.com/training/notify-user/build-notification
     *
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = getString(R.string.default_channel_label)
            val descriptionText = getString(R.string.default_channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(ChannelID.DEFAULT_CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

}