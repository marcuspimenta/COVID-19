/*
 * Copyright (C) 2020 Marcus Pimenta
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.pimenta.pandemicreports.totalcases.presentation.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.pimenta.pandemicreports.actions.EXTRA_COUNTRY
import com.pimenta.pandemicreports.model.presentation.model.CountryViewModel
import com.pimenta.pandemicreports.presentation.extension.getDrawableByName
import com.pimenta.pandemicreports.totalcases.R
import com.pimenta.pandemicreports.totalcases.di.TotalCasesActivityComponentProvider
import com.pimenta.pandemicreports.totalcases.presentation.presenter.TotalCasesContract
import kotlinx.android.synthetic.main.activity_total_cases.*
import javax.inject.Inject


/**
 * Created by marcus on 31-03-2020.
 */
class TotalCasesActivity : AppCompatActivity(), TotalCasesContract.View {

    private val countryViewModel by lazy { intent.getSerializableExtra(EXTRA_COUNTRY) as CountryViewModel }

    @Inject
    lateinit var presenter: TotalCasesContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as TotalCasesActivityComponentProvider)
            .totalCasesActivityComponentFactory()
            .create(this)
            .inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_cases)

        initGraph()

        presenter.initView(countryViewModel)
        presenter.loadCases(countryViewModel)
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showCountryFlag(countrySlug: String) {
        flagImageView.setImageDrawable(getDrawableByName(countrySlug))
    }

    override fun showCountryName(countryName: String) {
        nameTextView.text = countryName
    }

    override fun showConfirmedCases(confirmedCases: String) {
        confirmedCasesTextView.text =
            String.format(getString(R.string.confirmed_cases), confirmedCases)
    }

    override fun showNewCases(newCases: String) {
        newCasesTextView.text = String.format(getString(R.string.new_cases), newCases)
    }

    override fun showDeaths(deaths: String) {
        deathsTextView.text = String.format(getString(R.string.deaths_cases), deaths)
    }

    override fun showRecoveredCases(recoveredCases: String) {
        recoveredCasesTextView.text =
            String.format(getString(R.string.recovered_cases), recoveredCases)
    }

    override fun showGraph(cases: Pair<List<String>, List<List<Entry>>>) {
        val formatter: ValueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase): String =
                cases.first[value.toInt()]
        }
        casesLineChart.xAxis.valueFormatter = formatter

        val confirmedLineDataSet = LineDataSet(cases.second[0], getString(R.string.confirmed)).apply {
            axisDependency = AxisDependency.LEFT
            lineWidth = 2f
            setDrawValues(false)
            setDrawCircles(false)
            setColors(Color.argb(255, 255, 153, 0))
        }

        val deathsLineDataSet = LineDataSet(cases.second[1], getString(R.string.deaths)).apply {
            axisDependency = AxisDependency.LEFT
            lineWidth = 2f
            setDrawValues(false)
            setDrawCircles(false)
            setColors(Color.RED)
        }

        val dataSets = listOf(
            confirmedLineDataSet,
            deathsLineDataSet
        )

        with(casesLineChart) {
            data = LineData(dataSets)
            invalidate()
        }
    }

    override fun showErrorMessage(@StringRes resource: Int) {
        Toast.makeText(applicationContext, resource, Toast.LENGTH_SHORT).show()
    }

    private fun initGraph() {
        with(casesLineChart) {
            xAxis.apply {
                position = XAxisPosition.BOTTOM
                axisMinimum = 0f
                setDrawAxisLine(true)
                setDrawGridLines(true)
                enableGridDashedLine(3f, 3f, 0f)
            }

            axisLeft.apply {
                axisMinimum = 0f
                setDrawAxisLine(true)
                setDrawGridLines(true)
                enableGridDashedLine(3f, 3f, 0f)
            }


            axisRight.apply {
                axisMinimum = 0f
                setDrawAxisLine(false)
                setDrawGridLines(false)
                setDrawLabels(false)
            }

            casesLineChart.description = Description().apply {
                text = ""
            }
        }
    }
}