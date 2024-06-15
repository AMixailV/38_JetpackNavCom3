package ru.mixail_akulov.a38_jetpacknavcom3.screens.main.tabs.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mixail_akulov.a38_jetpacknavcom3.model.accounts.AccountsRepository
import ru.mixail_akulov.a38_jetpacknavcom3.model.accounts.entities.Account
import ru.mixail_akulov.a38_jetpacknavcom3.utils.MutableLiveEvent
import ru.mixail_akulov.a38_jetpacknavcom3.utils.publishEvent
import ru.mixail_akulov.a38_jetpacknavcom3.utils.share


class ProfileViewModel(
    private val accountsRepository: AccountsRepository
) : ViewModel() {

    private val _account = MutableLiveData<Account>()
    val account = _account.share()

    private val _restartFromLoginEvent = MutableLiveEvent<Unit>()
    val restartWithSignInEvent = _restartFromLoginEvent.share()

    init {
        viewModelScope.launch {
            accountsRepository.getAccount().collect {
                _account.value = it
            }
        }
    }

    fun logout() {
        // теперь выход из системы не является асинхронным, поэтому просто вызовите его
        // и перезапустите приложение с экрана входа в систему.
        accountsRepository.logout()
        restartAppFromLoginScreen()
    }

    private fun restartAppFromLoginScreen() {
        _restartFromLoginEvent.publishEvent()
    }

}