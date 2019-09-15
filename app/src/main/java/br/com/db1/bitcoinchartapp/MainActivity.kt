package br.com.db1.bitcoinchartapp

import android.os.Bundle
import android.text.format.DateFormat
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.db1.presentation.ViewStateListener
import br.com.db1.presentation.bitcoin.BitcoinViewModel
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.LegendRenderer
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class MainActivity : AppCompatActivity(), ViewStateListener {
    override fun onStateError(error: Throwable) {

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

        viewModel.getBitcoinLastValueViewState().onPostValue(
            this,
            onSuccess = {
                bitcoinValueTextView.text = String.format(
                    getString(R.string.bitcoin_price),
                    it
                )
                swipeRefreshLayout.isRefreshing = false
            },
            onError = {}
        )


        viewModel.getBitcoinChartViewState().onPostValue(this,
            onSuccess = {
                swipeRefreshLayout.isRefreshing = false
                graphView.fillSeries(it)
                graphView.setGraphTitle(getString(R.string.graph_title))
            }
        )
        lifecycle.addObserver(viewModel)
    }

}
