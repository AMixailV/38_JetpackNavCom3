package ru.mixail_akulov.a38_jetpacknavcom3.screens.main.tabs.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.mixail_akulov.a38_jetpacknavcom3.model.boxes.BoxesRepository
import ru.mixail_akulov.a38_jetpacknavcom3.utils.MutableLiveEvent
import ru.mixail_akulov.a38_jetpacknavcom3.utils.publishEvent
import ru.mixail_akulov.a38_jetpacknavcom3.utils.share

class BoxViewModel(
    private val boxId: Int,
    private val boxesRepository: BoxesRepository
) : ViewModel() {

    private val _shouldExitEvent = MutableLiveEvent<Boolean>()
    val shouldExitEvent = _shouldExitEvent.share()

    init {
        viewModelScope.launch {
            boxesRepository.getBoxes(onlyActive = true)
                .map { boxes -> boxes.firstOrNull { it.id == boxId } }
                .collect { currentBox ->
                    _shouldExitEvent.publishEvent(currentBox == null)
                }
        }
    }
}