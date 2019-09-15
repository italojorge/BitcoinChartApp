package br.com.db1.bitcoinchartapp.di

import br.com.db1.presentation.bitcoin.BitcoinViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { BitcoinViewModel() }
}
