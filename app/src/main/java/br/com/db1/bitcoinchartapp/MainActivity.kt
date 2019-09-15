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

                graphView.removeAllSeries()

                val series = LineGraphSeries(
                    it.chart.map {
                        DataPoint(it.date.toDouble(), it.bitcoinValue)
                    }.toTypedArray()
                )

                // custom label formatter to show currency "EUR"
                graphView.gridLabelRenderer.labelFormatter = object : DefaultLabelFormatter() {
                    override fun formatLabel(value: Double, isValueX: Boolean): String {
                        return if (isValueX) {
                            // show normal x values
                            getDate(value.toLong())
                        } else {
                            // show currency for y values
                            super.formatLabel(value, isValueX) + " $"
                        }
                    }
                }
                // set manual X bounds
                graphView.viewport.isXAxisBoundsManual = true
                graphView.viewport.setMinX(it.chart.first().date.toDouble())
                graphView.viewport.setMaxX(it.chart.last().date.toDouble())

                    series.title = "Preço médio de mercado em USD dos últimos 30 dias."
                    graphView.legendRenderer.isVisible = true
                graphView.legendRenderer.align = LegendRenderer.LegendAlign.TOP
                graphView.legendRenderer.backgroundColor = ContextCompat.getColor(this,R.color.gray)

                graphView.viewport.isScalable = true
// activate vertical scrolling
                graphView.viewport.setScalableY(true)


                graphView.addSeries(series)
                series.setAnimated(true)
            }
        )
        lifecycle.addObserver(viewModel)
    }

    private fun getDate(time: Long): String {
        val cal = Calendar.getInstance(Locale("pt", "BR"))
        cal.timeInMillis = time * 1000
        return DateFormat.format("dd/MM", cal).toString()
    }

}
