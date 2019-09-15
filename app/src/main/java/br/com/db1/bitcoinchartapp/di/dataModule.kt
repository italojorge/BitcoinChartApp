package br.com.db1.bitcoinchartapp.di

import br.com.db1.data.datastore.BitcoinRepositoryImpl
import br.com.db1.data.repository.remote.BitcoinRemoteDataSource
import br.com.db1.data_remote.datasource.BitcoinRemoteDataSourceImpl
import br.com.db1.data_remote.service.BitcoinLastValueWebService
import br.com.db1.data_remote.service.BitcoinChartsWebService
import br.com.db1.data_remote.utils.BITCOIN_CHARTS_URL
import br.com.db1.data_remote.utils.BITCOIN_LAST_VALUE_URL
import br.com.db1.data_remote.utils.WebServiceFactory
import br.com.db1.domain.repository.BitcoinRepository
import org.koin.dsl.module

val dataModule = module {
    single {
        WebServiceFactory.provideOkHttpClient()
    }

    single { WebServiceFactory.createWebService(get(), BITCOIN_CHARTS_URL) as BitcoinChartsWebService }

    single { WebServiceFactory.createWebService(get(), BITCOIN_LAST_VALUE_URL) as BitcoinLastValueWebService }

    single { BitcoinRemoteDataSourceImpl(get(),get()) as BitcoinRemoteDataSource }

    single { BitcoinRepositoryImpl(get()) as BitcoinRepository }
}

