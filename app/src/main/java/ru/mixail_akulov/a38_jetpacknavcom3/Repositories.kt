package ru.mixail_akulov.a38_jetpacknavcom3

import ru.mixail_akulov.a38_jetpacknavcom3.model.accounts.AccountsRepository
import ru.mixail_akulov.a38_jetpacknavcom3.model.accounts.InMemoryAccountsRepository
import ru.mixail_akulov.a38_jetpacknavcom3.model.boxes.BoxesRepository
import ru.mixail_akulov.a38_jetpacknavcom3.model.boxes.InMemoryBoxesRepository

object Repositories {

    val accountsRepository: AccountsRepository = InMemoryAccountsRepository()

    val boxesRepository: BoxesRepository = InMemoryBoxesRepository(accountsRepository)

}