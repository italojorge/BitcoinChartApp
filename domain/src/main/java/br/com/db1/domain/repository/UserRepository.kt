package br.com.db1.domain.repository

import br.com.db1.domain.core.Either
import br.com.db1.domain.model.User

interface UserRepository {

    suspend fun login(phone: String, password: String): Either<User, Throwable>
}