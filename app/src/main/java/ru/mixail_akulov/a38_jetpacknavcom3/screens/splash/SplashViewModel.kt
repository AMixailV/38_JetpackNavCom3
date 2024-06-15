package ru.mixail_akulov.a38_jetpacknavcom3.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mixail_akulov.a38_jetpacknavcom3.model.accounts.AccountsRepository
import ru.mixail_akulov.a38_jetpacknavcom3.utils.MutableLiveEvent
import ru.mixail_akulov.a38_jetpacknavcom3.utils.publishEvent
import ru.mixail_akulov.a38_jetpacknavcom3.utils.share

/**
 * SplashViewModel проверяет, вошел ли пользователь в систему или нет.
 */
class SplashViewModel(
    private val accountsRepository: AccountsRepository
) : ViewModel() {

    private val _launchMainScreenEvent = MutableLiveEvent<Boolean>()
    val launchMainScreenEvent = _launchMainScreenEvent.share()

    init {
        viewModelScope.launch {
            _launchMainScreenEvent.publishEvent(accountsRepository.isSignedIn())
        }
    }
}