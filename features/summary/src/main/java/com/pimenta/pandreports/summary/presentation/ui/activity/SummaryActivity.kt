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

package com.pimenta.pandreports.summary.presentation.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.pimenta.pandreports.actions.Actions
import com.pimenta.pandreports.model.presentation.model.CountryViewModel
import com.pimenta.pandreports.summary.R
import com.pimenta.pandreports.summary.di.SummaryActivityComponentProvider
import com.pimenta.pandreports.summary.presentation.presenter.SummaryContract
import com.pimenta.pandreports.summary.presentation.ui.adapter.CountryAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Created by marcus on 29-03-2020.
 */
class SummaryActivity : AppCompatActivity(), SummaryContract.View,
    CountryAdapter.OnItemViewClickedListener {

    private val countryAdapter by lazy { CountryAdapter(this) }

    @Inject
    lateinit var presenter: SummaryContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as SummaryActivityComponentProvider)
            .summaryActivityComponentFactory()
            .create(this)
            .inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countriesRecyclerView.apply {
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(context)
            adapter = countryAdapter
        }

        swipeContainer.setOnRefreshListener { presenter.loadSummary() }

        presenter.loadSummary()
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_summary, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_about -> {
            presenter.aboutClicked()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun showProgress() {
        swipeContainer.isRefreshing = true
    }

    override fun hideProgress() {
        swipeContainer.isRefreshing = false
    }

    override fun showCountries(countries: List<CountryViewModel>) {
        countryAdapter.submitList(countries)
    }

    override fun openTotalCases(countryViewModel: CountryViewModel) {
        val intent = Actions.openTotalCases(this, countryViewModel)
        startActivity(intent)
    }

    override fun onItemClicked(countryViewModel: CountryViewModel) {
        presenter.countryClicked(countryViewModel)
    }

    override fun openAbout() {
        val intent = Actions.openAbout(this)
        startActivity(intent)
    }

    override fun showErrorMessage(@StringRes resource: Int) {
        Toast.makeText(applicationContext, resource, Toast.LENGTH_SHORT).show()
    }
}