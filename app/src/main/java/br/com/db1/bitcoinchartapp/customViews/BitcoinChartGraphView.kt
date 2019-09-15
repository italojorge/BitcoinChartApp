package br.com.db1.bitcoinchartapp.customViews

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import br.com.db1.bitcoinchartapp.R
import br.com.db1.bitcoinchartapp.extensions.convertTimestampToDate
import br.com.db1.presentation.model.BitcoinChartBinding
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.LegendRenderer
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_main.view.*

class BitcoinChartGraphView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) :  GraphView(context, attrs, defStyle) {

    private var series: LineGraphSeries<DataPoint>? = null

    init {
        setupGraphView()
    }

    private fun setupGraphView() {
        formatXandYLabelValues()
        graphView.legendRenderer.backgroundColor = ContextCompat.getColor(context, R.color.gray)

        graphView.viewport.isScalable = true
        graphView.viewport.setScalableY(true)
    }

    fun fillSeries(bitcoinChartBinding: BitcoinChartBinding){
        graphView.removeAllSeries()
        val dataPoints = bitcoinChartBinding.chart.map {
            DataPoint(it.date.toDouble(), it.bitcoinValue)
        }.toTypedArray()

        series = LineGraphSeries(dataPoints)
        series?.setAnimated(true)

        graphView.viewport.isXAxisBoundsManual = true
        graphView.viewport.setMinX(bitcoinChartBinding.chart.first().date.toDouble())
        graphView.viewport.setMaxX(bitcoinChartBinding.chart.last().date.toDouble())

        graphView.addSeries(series)
    }

    private fun formatXandYLabelValues() {
        graphView.gridLabelRenderer.labelFormatter = object : DefaultLabelFormatter() {
            override fun formatLabel(value: Double, isValueX: Boolean): String {
                return if (isValueX) {
                    convertTimestampToDate(value.toLong())
                } else {
                    super.formatLabel(value, isValueX) + " $"
                }
            }
        }
    }

    fun setGraphTitle(title: String){
        series?.let {
            it.title = title
            graphView.apply {
                legendRenderer.isVisible = true
                legendRenderer.align = LegendRenderer.LegendAlign.TOP
            }
        }
    }

}