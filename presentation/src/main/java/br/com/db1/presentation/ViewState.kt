package br.com.db1.presentation

class ViewState<T>(
    val status: Status = Status.NEUTRAL,
    val data: T? = null,
    private val error: Throwable? = null
) {

    fun stateHandler(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit,
        loading: () -> Unit
    ) {
        when (status) {
            Status.SUCCESS -> data?.let { onSuccess(it) } ?: throw RuntimeException()
            Status.ERROR -> error?.let { onError(it) } ?: throw RuntimeException()
            Status.LOADING -> loading()
            else -> {
            }
        }
    }

    enum class Status {
        SUCCESS, ERROR, LOADING, NEUTRAL
    }

}

fun <T> ViewState<T>?.isSuccess() = this?.status?.equals(ViewState.Status.SUCCESS) ?: false
fun <T> ViewState<T>?.isError() = this?.status?.equals(ViewState.Status.ERROR) ?: false

