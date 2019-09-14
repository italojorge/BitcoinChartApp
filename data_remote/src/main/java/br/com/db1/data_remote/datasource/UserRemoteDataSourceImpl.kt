package br.com.db1.data_remote.datasource

import br.com.db1.data_remote.service.UserWebService
import br.com.db1.data_remote.utils.requestWrapper
import br.com.db1.data.repository.remote.UserRemoteDataSource
import br.com.db1.domain.core.Either
import br.com.db1.domain.model.User

class UserRemoteDataSourceImpl(
    private val userWebService: UserWebService
) : UserRemoteDataSource {

    override suspend fun login(phone: String, password: String): Either<User, Throwable> =
        requestWrapper {
            TODO()
        }
}