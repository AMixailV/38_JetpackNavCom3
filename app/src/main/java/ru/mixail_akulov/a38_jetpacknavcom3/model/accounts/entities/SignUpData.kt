package ru.mixail_akulov.a38_jetpacknavcom3.model.accounts.entities

import ru.mixail_akulov.a38_jetpacknavcom3.model.EmptyFieldException
import ru.mixail_akulov.a38_jetpacknavcom3.model.Field
import ru.mixail_akulov.a38_jetpacknavcom3.model.PasswordMismatchException

/**
 * Поля, которые необходимо указать при создании новой учетной записи.
 */
data class SignUpData(
    val username: String,
    val email: String,
    val password: String,
    val repeatPassword: String
) {
    fun validate() {
        if (email.isBlank()) throw EmptyFieldException(Field.Email)
        if (username.isBlank()) throw EmptyFieldException(Field.Username)
        if (password.isBlank()) throw EmptyFieldException(Field.Password)
        if (password != repeatPassword) throw PasswordMismatchException()
    }
}