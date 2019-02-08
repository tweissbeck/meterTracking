package meter.tracking.db.converter

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * @author tweissbeck
 * @since 1.0.0
 */
object LocalDateConverter {

    @JvmStatic
    @TypeConverter
    fun convert(value: String): LocalDate = LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE)

    @JvmStatic
    @TypeConverter
    fun convert(value: LocalDate): String = value.format(DateTimeFormatter.ISO_LOCAL_DATE)
}