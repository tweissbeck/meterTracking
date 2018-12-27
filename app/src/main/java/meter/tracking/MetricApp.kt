package meter.tracking

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import meter.tracking.di.ApplicationModule
import meter.tracking.notification.ChannelID
import org.koin.android.ext.android.startKoin


class MetricApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin DI. Modules definition are in ApplicationModule object
        startKoin(this, listOf(ApplicationModule.appModule))

        createNotificationChannel()
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = getString(R.string.default_channel_label)
            val descriptionText = getString(R.string.default_channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(ChannelID.DEFAULT_CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

}