package ru.mixail_akulov.a38_jetpacknavcom3.model.accounts.entities

/**
 * Информация о пользователе.
 */
data class Account(
    val username: String,
    val email: String,
    val createdAt: Long = UNKNOWN_CREATED_AT
) {
    companion object {
        const val UNKNOWN_CREATED_AT = 0L
    }
}