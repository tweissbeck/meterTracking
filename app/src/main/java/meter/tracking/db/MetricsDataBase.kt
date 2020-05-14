package meter.tracking.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import meter.tracking.db.converter.HistoryTypeConverter
import meter.tracking.db.converter.LocalDateConverter
import meter.tracking.db.dao.MeasuringTypeDao
import meter.tracking.db.dao.MetricDao
import meter.tracking.db.dao.RecordDao
import meter.tracking.db.model.HistoryFrequency
import meter.tracking.db.model.MeasuringTypeEntity
import meter.tracking.db.model.MetricEntity
import meter.tracking.db.model.MetricRecordEntity
import java.lang.RuntimeException
import java.time.LocalDate
import java.time.Month


/**
 * @author tweissbeck
 * @since 1.0.0
 */
@Database(entities = [(MetricEntity::class), (MetricRecordEntity::class), (MeasuringTypeEntity::class)], version = 1)
@TypeConverters(HistoryTypeConverter::class, LocalDateConverter::class)
abstract class MetricsDataBase : RoomDatabase() {
    abstract fun metricDao(): MetricDao
    abstract fun measuringType(): MeasuringTypeDao
    abstract fun recordDao(): RecordDao

    private class MetricsDatabaseCallback(
            private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {

                    val metricDao = database.metricDao()
                    val measuringTypeDao = database.measuringType()

                    // Delete all content here.
                    metricDao.deleteAll()
                    measuringTypeDao.deleteAll()

                    // Add measuring type
                    measuringTypeDao.insert(MeasuringTypeEntity("Kilométrage", 0, "Km"))
                    measuringTypeDao.insert(MeasuringTypeEntity("Watt", 0, "Watt"))

                    // Add counter
                    metricDao.insert(
                            MetricEntity("Car1", 0, "Kilométrage",
                                    HistoryFrequency.MONTHLY,
                                    LocalDate.of(2020, Month.JANUARY, 1)
                            ))
                    metricDao.insert(
                            MetricEntity("Elec", 0, "Watt", HistoryFrequency.MONTHLY,
                                    LocalDate.of(2020, Month.JANUARY, 1))
                    )


                    val recordDao = database.recordDao()
                    // Add record to counter 0
                    recordDao.insertRecord(MetricRecordEntity(1, 150, LocalDate.of(2020, Month.JANUARY, 1)))
                    recordDao.insertRecord(MetricRecordEntity(1, 160, LocalDate.of(2020, Month.FEBRUARY, 1)))
                    recordDao.insertRecord(MetricRecordEntity(1, 1550, LocalDate.of(2020, Month.MARCH, 1)))
                }

            }
        }
    }


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: MetricsDataBase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): MetricsDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MetricsDataBase::class.java,
                        "word_database"
                )
                        .addCallback(MetricsDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}