package br.com.db1.presentation.bitcoin

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import br.com.db1.domain.interactor.bitcoinChart.GetBitcoinChartUseCase
import br.com.db1.domain.interactor.bitcoinLastValue.GetBitcoinLastValueUseCase
import br.com.db1.presentation.mapper.bitcoin.BitcoinChartMapper
import br.com.db1.presentation.model.BitcoinChartBinding
import br.com.db1.presentation.model.emptyBitcoinChartBinding
import br.com.db1.presentation.utils.BITCOIN_LAST_VALUE_EMPTY
import br.com.db1.presentation.utils.extensions.*
import org.koin.core.KoinComponent

class BitcoinViewModel : ViewModel(),
    LifecycleObserver, KoinComponent {

    private val bitcoinChartViewState by viewState<BitcoinChartBinding>()
    private val bitcoinLastValueViewState by viewState<String>()

    private val getBitcoinChartUseCase: GetBitcoinChartUseCase by useCase()
    private val getBitcoinLastValueUseCase: GetBitcoinLastValueUseCase by useCase()

    private var bitcoinChartBinding = emptyBitcoinChartBinding()
    private var bitcoinLastValueBinding = BITCOIN_LAST_VALUE_EMPTY

    fun getBitcoinChartViewState() = bitcoinChartViewState.asLiveData()
    fun getBitcoinLastValueViewState() = bitcoinLastValueViewState.asLiveData()

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

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun getBitcoinLastValue() {
        bitcoinLastValueViewState.postLoading()

        getBitcoinLastValueUseCase.execute(
            onSuccess = {
                bitcoinLastValueBinding = it.lastValue.toString().replaceDotWithComma()
                bitcoinLastValueViewState.postSuccess(bitcoinLastValueBinding)
            },
            onError = {
                bitcoinLastValueViewState.postError(it)
            }
        )
    }

}