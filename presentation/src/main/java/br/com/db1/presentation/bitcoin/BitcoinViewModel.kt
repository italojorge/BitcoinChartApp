package br.com.db1.presentation.bitcoin

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import br.com.db1.domain.interactor.bitcoinChart.GetBitcoinChartUseCase
import br.com.db1.presentation.mapper.bitcoin.BitcoinChartMapper
import br.com.db1.presentation.model.BitcoinChartBinding
import br.com.db1.presentation.model.emptyBitcoinChartBinding
import br.com.db1.presentation.utils.extensions.*
import org.koin.core.KoinComponent

class BitcoinViewModel : ViewModel(),
    LifecycleObserver, KoinComponent {

    private val bitcoinChartViewState by viewState<BitcoinChartBinding>()

    private val getBitcoinChartUseCase: GetBitcoinChartUseCase by useCase()

    private var bitcoinChartBinding = emptyBitcoinChartBinding()

    fun getBitcoinChartViewState() = bitcoinChartViewState.asLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun getBitcoinChart() {
        bitcoinChartViewState.postLoading()

        getBitcoinChartUseCase.execute(
            onSuccess = {
                bitcoinChartBinding = BitcoinChartMapper.fromDomain(it)
                bitcoinChartViewState.postSuccess(bitcoinChartBinding)
            },
            onError = {
                bitcoinChartViewState.postError(it)
            }
        )
    }

}