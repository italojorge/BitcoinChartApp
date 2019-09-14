package br.com.db1.data.datastore

import br.com.db1.data.repository.remote.UserRemoteDataSource
import br.com.db1.domain.core.Either
import br.com.db1.domain.model.User
import br.com.db1.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun login(phone: String, password: String): Either<User, Throwable> {
        return userDataSource.login(phone, password)
    }
}