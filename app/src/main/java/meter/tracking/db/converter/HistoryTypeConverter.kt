package meter.tracking.db.converter

import androidx.room.TypeConverter
import meter.tracking.db.model.HistoryFrequency

/**
 * @author tweissbeck
 * @since 1.0.0
 */
object HistoryTypeConverter {

    @JvmStatic
    @TypeConverter
    fun convert(value: String): HistoryFrequency = HistoryFrequency.valueOf(value)

    @JvmStatic
    @TypeConverter
    fun convert(value: HistoryFrequency): String = value.name
}