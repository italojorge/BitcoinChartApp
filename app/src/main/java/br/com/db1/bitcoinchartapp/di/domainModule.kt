package br.com.db1.bitcoinchartapp.di

import br.com.db1.domain.interactor.bitcoinChart.GetBitcoinChartUseCase
import br.com.db1.domain.interactor.bitcoinLastValue.GetBitcoinLastValueUseCase
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val domainModule = module {
    factory { (scope: CoroutineScope) -> GetBitcoinChartUseCase(get(),scope) }

    factory { (scope: CoroutineScope) -> GetBitcoinLastValueUseCase(get(),scope) }
}