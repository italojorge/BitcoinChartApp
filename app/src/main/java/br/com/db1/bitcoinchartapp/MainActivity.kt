package br.com.db1.bitcoinchartapp

import android.os.Bundle
import android.text.format.DateFormat
import androidx.appcompat.app.AppCompatActivity
import br.com.db1.presentation.ViewStateListener
import br.com.db1.presentation.bitcoin.BitcoinViewModel
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.LegendRenderer
import java.util.*


class MainActivity : AppCompatActivity(),ViewStateListener {
    override fun onStateError(error: Throwable) {

    }

    override fun onStateLoading() {

    }

    private val viewModel: BitcoinViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addObservers()
    }

    private fun addObservers(){
        viewModel.getBitcoinChartViewState().onPostValue(this,
            onSuccess = {



                val series = LineGraphSeries(
                    it.chart.map{
                        DataPoint(it.date.toDouble(),it.bitcoinValue)
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

                graphView.viewport.isScalable = true
// activate vertical scrolling
                graphView.viewport.setScalableY(true)

                graphView.addSeries(series)
                series.setAnimated(true)
                series.title = "Preço médio de mercado em USDResponse dos últimos 30 dias."
                graphView.legendRenderer.align = LegendRenderer.LegendAlign.TOP
                graphView.legendRenderer.isVisible = true
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
