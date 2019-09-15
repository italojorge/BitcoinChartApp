package br.com.db1.presentation.utils.extensions

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.db1.domain.core.UseCase
import br.com.db1.presentation.ViewState
import br.com.db1.presentation.utils.EventLiveData
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

fun <T> viewState() = lazy {
    EventLiveData<ViewState<T>>()
}

fun <T : EventLiveData<T>> T.asLiveData1(event: () -> Unit) = lazy {
    this.asLiveData().also { event() }
}

inline fun <V, reified U> V.useCase() where U : UseCase<*, *>, V : ViewModel, V : KoinComponent = inject<U> {
    parametersOf(viewModelScope)
}


fun AndroidViewModel.getString(resId: Int): String = getApplication<Application>().getString(resId)

