package ru.mixail_akulov.a38_jetpacknavcom3.screens.main.tabs.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mixail_akulov.a38_jetpacknavcom3.model.boxes.BoxesRepository
import ru.mixail_akulov.a38_jetpacknavcom3.model.boxes.entities.Box
import ru.mixail_akulov.a38_jetpacknavcom3.utils.share

class DashboardViewModel(
    private val boxesRepository: BoxesRepository
) : ViewModel() {

    private val _boxes = MutableLiveData<List<Box>>()
    val boxes = _boxes.share()

    init {
        viewModelScope.launch {
            boxesRepository.getBoxes(onlyActive = true).collect {
                _boxes.value = it
            }
        }
    }

}