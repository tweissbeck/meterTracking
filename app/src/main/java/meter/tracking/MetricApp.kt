package meter.tracking

import android.app.Application
import meter.tracking.di.ApplicationModule
import org.koin.android.ext.android.startKoin


class MetricApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin DI. Modules definition are in ApplicationModule object
        startKoin(this, listOf(ApplicationModule.appModule))
    }

}