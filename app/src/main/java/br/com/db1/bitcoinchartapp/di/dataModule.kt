package br.com.db1.bitcoinchartapp.di

import br.com.db1.data.datastore.BitcoinChartRepositoryImpl
import br.com.db1.data.repository.remote.BitcoinChartRemoteDataSource
import br.com.db1.data_remote.datasource.BitcoinChartRemoteDataSourceImpl
import br.com.db1.data_remote.service.BitcoinChartsWebService
import br.com.db1.data_remote.utils.BITCOIN_CHARTS_URL
import br.com.db1.data_remote.utils.WebServiceFactory
import br.com.db1.domain.repository.BitcoinChartRepository
import org.koin.dsl.module

val dataModule = module {
    single {
        WebServiceFactory.provideOkHttpClient()
    }

    single { WebServiceFactory.createWebService(get(), BITCOIN_CHARTS_URL) as BitcoinChartsWebService }

    single { BitcoinChartRemoteDataSourceImpl(get()) as BitcoinChartRemoteDataSource }

    single { BitcoinChartRepositoryImpl(get()) as BitcoinChartRepository }
}

