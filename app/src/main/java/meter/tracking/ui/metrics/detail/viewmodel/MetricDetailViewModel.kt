package meter.tracking.ui.metrics.detail.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import meter.tracking.db.MetricsDataBase
import meter.tracking.db.model.MetricWithRecord
import meter.tracking.repository.MetricRepository

class MetricDetailViewModel(context: Application): AndroidViewModel(context) {

    private val metricRepository: MetricRepository = MetricRepository(MetricsDataBase.getDatabase(context, viewModelScope).metricDao())
    val metricDetail: MutableLiveData<MetricWithRecord> = MutableLiveData()

    fun getMetric(id: Long) = viewModelScope.launch(Dispatchers.IO) {
        metricDetail.postValue(metricRepository.getMetric(id).value)
    }


}