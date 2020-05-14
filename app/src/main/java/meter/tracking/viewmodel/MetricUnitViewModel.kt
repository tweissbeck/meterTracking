package meter.tracking.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import meter.tracking.db.MetricsDataBase
import meter.tracking.db.model.MeasuringTypeEntity
import meter.tracking.repository.MeasuringTypeRepository

class MetricUnitViewModel(application: Application) : AndroidViewModel(application) {

    val measuringTypeRepository: MeasuringTypeRepository
    val datas: LiveData<List<MeasuringTypeEntity>>

    init {
        val measuringTypeDao = MetricsDataBase.getDatabase(application, viewModelScope).measuringType()
        this.measuringTypeRepository = MeasuringTypeRepository(measuringTypeDao)
        this.datas = this.measuringTypeRepository.getAll()
    }

}