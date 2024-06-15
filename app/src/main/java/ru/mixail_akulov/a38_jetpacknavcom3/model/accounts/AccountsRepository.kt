package ru.mixail_akulov.a38_jetpacknavcom3.model.accounts

import kotlinx.coroutines.flow.Flow
import ru.mixail_akulov.a38_jetpacknavcom3.model.accounts.entities.Account
import ru.mixail_akulov.a38_jetpacknavcom3.model.accounts.entities.SignUpData

/**
 * Репозиторий с действиями, связанными с учетной записью, например. вход, регистрация, редактирование учетной записи и т. д.
 */
interface AccountsRepository {

    /**
     * Вошел ли пользователь в систему или нет.
     */
    suspend fun isSignedIn(): Boolean

    /**
     * Попробуйте войти с помощью электронной почты и пароля.
     * @throws [EmptyFieldException], [AuthException]
     */
    suspend fun signIn(email: String, password: String)

    /**
     * Создать новый аккаунт.
     * @throws [EmptyFieldException], [PasswordMismatchException], [AccountAlreadyExistsException]
     */
    suspend fun signUp(signUpData: SignUpData)

    /**
     * Выход из приложения.
     */
    fun logout()

    /**
     * Получить информацию об учетной записи текущего вошедшего пользователя.
     */
    fun getAccount(): Flow<Account?>

    /**
     * Измените имя пользователя текущего вошедшего пользователя.
     * @throws [EmptyFieldException], [AuthException]
     */
    suspend fun updateAccountUsername(newUsername: String)

}