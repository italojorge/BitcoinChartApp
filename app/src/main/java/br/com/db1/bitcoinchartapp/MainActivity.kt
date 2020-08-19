package br.com.db1.bitcoinchartapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import br.com.db1.presentation.ViewStateListener
import br.com.db1.presentation.bitcoin.BitcoinViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), ViewStateListener {
    override fun onStateError(error: Throwable) {
        swipeRefreshLayout.isRefreshing = false
        bitcoinValueTextView.text = getString(R.string.internet_error)
        toast(getString(R.string.internet_error))
    }

    override fun onStateLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    private val viewModel: BitcoinViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addObservers()
    }

    private fun addObservers() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getBitcoinLastValue()
            viewModel.getBitcoinChart()
        }
        handleBitcoinLastValue()
        handleBitcoinChart()
        lifecycle.addObserver(viewModel)
    }

    private fun handleBitcoinChart() {
        viewModel.getBitcoinChartViewState().onPostValue(this,
            onSuccess = {
                swipeRefreshLayout.isRefreshing = false
                graphView.apply {
                    isVisible = true
                    fillSeries(it)
                    setGraphTitle(getString(R.string.graph_title))
                }
            }
        )
    }

    private fun handleBitcoinLastValue() {
        viewModel.getBitcoinLastValueViewState().onPostValue(
            this,
            onSuccess = {
                bitcoinValueTextView.text = getString(R.string.bitcoin_price, it)
                swipeRefreshLayout.isRefreshing = false
            }
        )
    }
}
