package meter.tracking.db

import android.arch.persistence.room.Room
import android.content.Context
import meter.tracking.util.Singleton


/**
 * @author tweissbeck
 * @since 1.0.0
 */
object DataBase : Singleton<MetricsDataBase, Context>({ context ->
                                                          Room.databaseBuilder(context,
                                                                               MetricsDataBase::class.java,
                                                                               "metric_base").build()
                                                      })
