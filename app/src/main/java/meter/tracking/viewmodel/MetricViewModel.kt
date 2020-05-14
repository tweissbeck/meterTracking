package meter.tracking.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import meter.tracking.db.MetricsDataBase
import meter.tracking.db.model.MetricEntity
import meter.tracking.repository.MetricRepository

class MetricViewModel(application: Application) : AndroidViewModel(application) {

    private val metricRepository: MetricRepository
    val metrics: LiveData<List<MetricEntity>>
    init {
        val metricDao = MetricsDataBase.getDatabase(application, viewModelScope).metricDao()
        metricRepository = MetricRepository(metricDao)
        metrics = metricDao.getAll()
    }

    fun insert(metricEntity: MetricEntity) = viewModelScope.launch(Dispatchers.IO) {
        metricRepository.saveMetric(metricEntity)
    }
}