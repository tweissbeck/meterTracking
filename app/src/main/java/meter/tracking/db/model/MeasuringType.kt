package meter.tracking.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A MeasuringType is an kind of measure defined by a label and a precision. For instance "mileage counter" with precision 0, symbol miles
 * @param label the label of this measuring unit
 * @param decimal precision of the measuring unit
 * @param unitSymbol
 * @author tweissbeck
 * @since 1.0.0
 */
@Entity
class MeasuringType(@PrimaryKey val label: String, val decimal: Int = 0, val unitSymbol: String?)