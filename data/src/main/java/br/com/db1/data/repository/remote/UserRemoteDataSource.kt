package br.com.db1.data.repository.remote

import br.com.db1.domain.core.Either
import br.com.db1.domain.model.User

interface UserRemoteDataSource {

    suspend fun login(phone: String, password: String): Either<User, Throwable>

}