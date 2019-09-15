package br.com.db1.bitcoinchartapp.core

import android.app.Application
import br.com.db1.bitcoinchartapp.di.dataModule
import br.com.db1.bitcoinchartapp.di.domainModule
import br.com.db1.bitcoinchartapp.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()


        startKoin {
            modules(
                listOf(
                    presentationModule,
                    domainModule,
                    dataModule
                )
            ).androidContext(applicationContext)
        }
    }
}