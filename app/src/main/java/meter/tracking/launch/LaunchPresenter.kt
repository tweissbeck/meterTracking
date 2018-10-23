package meter.tracking.launch

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.internal.operators.completable.CompletableEmpty
import io.reactivex.internal.operators.single.SingleJust
import io.reactivex.schedulers.Schedulers
import meter.tracking.datasource.MeasuringTypeDataSource
import meter.tracking.db.model.Metric
import meter.tracking.metrics.main.MetersTrackingActivity
import meter.tracking.datasource.MetricDataSource
import meter.tracking.db.model.HistoryFrequency
import meter.tracking.db.model.MeasuringType
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * Launch activity presenter. Its main stuff is to load some data from base then redirect to main activity: [MetersTrackingActivity]
 * @author tweissbeck
 * @since 1.0.0
 */
class LaunchPresenter(private val view: LaunchContract.View,
                      private val metricsDataSource: MetricDataSource,
                      private val measuringDataSource: MeasuringTypeDataSource) : LaunchContract.Presenter {

    companion object {
        internal val TAG: String = "LaunchPresenter"
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    init {
        view.presenter = this
    }

    override fun init() {
        val initTestDataObservable: Completable = this.initTestData()
        fun onComplete(): () -> Unit = { view.goToMainActivity() }

        fun onError(): (Throwable) -> Unit = { e ->
            Log.e(TAG, "Failed to insert test data", e)
        }

        // Add a deplay to allow the user to see our wonder full splash screen ;)
        val disposable = initTestDataObservable.delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onComplete(), onError())
        this.compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        this.compositeDisposable.dispose()
    }

    private fun initTestData(): Completable {

        // An observable that create MeasuringType if needed (aka table is empty)
        val measuringInitObservable = measuringDataSource.getAll().map { result ->
            if (result.isEmpty()) {
                measuringDataSource.insertAll(arrayOf(
                        MeasuringType("Kilometreage", 0, "Km"),
                        MeasuringType("Watt", 0, "Watt"),
                        MeasuringType("Litre", 0, "L")
                ))
                Unit
            } else {
                Unit
            }
        }.ignoreElement()

        // An observable that create  Metric if needed (aka table is empty)
        val createTestMetricsObservable = metricsDataSource.getMetrics().subscribeOn(
                Schedulers.io()).flatMap { t: List<Metric> ->
            if (t.isEmpty()) {
                var id = 0L
                metricsDataSource.saveMetrics(arrayOf(
                        Metric("CAR 1", 1420, "Kilometreage", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                        Metric("CAR 2", 15874, "Kilometreage", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                        Metric("Water", 1420, "Litre", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                        Metric("Elec", 142078, "Watt", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                        Metric("CAR 1", 1420, "Kilometreage", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                        Metric("CAR 1", 1420, "Kilometreage", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                        Metric("CAR 2", 15874, "Kilometreage", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                        Metric("Water", 1420, "Litre", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                        Metric("Elec", 142078, "Watt", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                        Metric("CAR 1", 1420, "Kilometreage", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                        Metric("CAR 1", 1420, "Kilometreage", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                        Metric("CAR 2", 15874, "Kilometreage", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                        Metric("Water", 1420, "Litre", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                        Metric("Elec", 142078, "Watt", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                        Metric("CAR 1", 1420, "Kilometreage", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                        Metric("CAR 1", 1420, "Kilometreage", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                        Metric("CAR 2", 15874, "Kilometreage", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                        Metric("Water", 1420, "Litre", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                        Metric("Elec", 142078, "Watt", HistoryFrequency.MONTHLY).apply { this.id = id++ },
                        Metric("CAR 1", 1420, "Kilometreage", HistoryFrequency.MONTHLY).apply { this.id = id++ }
                )).flatMap { _ -> SingleJust(Unit) }
            } else {
                SingleJust(Unit)
            }
        }.ignoreElement()

        return measuringInitObservable.andThen(createTestMetricsObservable)
    }
}