package br.com.db1.domain.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

abstract class UseCase<T, in Params>(private val scope: CoroutineScope) {

    abstract suspend fun run(params: Params?): Result<T>

    fun execute(
        params: Params? = null,
        onError: ((Throwable) -> Unit) = {},
        onSuccess: (T) -> Unit
    ) {
        scope.launch(Dispatchers.IO) {
            run(params).either(
                { onSuccess(it) },
                { onError(it) }
            )
        }
    }

    fun cancel() = scope.cancel()
}
