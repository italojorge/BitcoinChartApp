package br.com.db1.domain.interactor.authentication

import br.com.db1.domain.core.Either
import br.com.db1.domain.core.UseCase
import br.com.db1.domain.exception.EmptyFieldException
import br.com.db1.domain.exception.MissingParamsException
import kotlinx.coroutines.CoroutineScope

class ValidatePassword(
    scope: CoroutineScope
) : UseCase<Unit, ValidatePassword.Params>(scope) {

    override suspend fun run(params: Params?): Either<Unit, Throwable> {
        return when {
            params == null -> throw MissingParamsException()
            params.password.isBlank() -> Either.Failure(EmptyFieldException())
            else -> Either.Success(Unit)
        }
    }

    data class Params(
        val password: String
    )

}