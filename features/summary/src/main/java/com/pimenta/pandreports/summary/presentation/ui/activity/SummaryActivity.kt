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
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.pimenta.pandreports.actions.Actions
import com.pimenta.pandreports.summary.R
import com.pimenta.pandreports.summary.di.SummaryActivityComponentProvider
import com.pimenta.pandreports.summary.presentation.model.SummaryViewEvent
import com.pimenta.pandreports.summary.presentation.model.SummaryViewState
import com.pimenta.pandreports.summary.presentation.presenter.SummaryViewModel
import com.pimenta.pandreports.summary.presentation.ui.adapter.CountryAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Created by marcus on 29-03-2020.
 */
class SummaryActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<SummaryViewModel> { viewModelFactory }

    private val compositeDisposable = CompositeDisposable()
    private val countryAdapter = CountryAdapter { viewModel.onCountryClicked(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as SummaryActivityComponentProvider)
            .summaryActivityComponentFactory()
            .create()
            .inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countriesRecyclerView.apply {
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(context)
            adapter = countryAdapter
        }

        swipeContainer.setOnRefreshListener { viewModel.onRefreshed() }

        viewModel.run {
            state.subscribe {
                handleViewState(it)
            }.also { compositeDisposable.add(it) }
            events.subscribe {
                handleViewEvent(it)
            }.also { compositeDisposable.add(it) }

            onInit()
        }
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_summary, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_about -> {
            viewModel.onAboutClicked()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun handleViewState(summaryViewState: SummaryViewState) {
        countryAdapter.submitList(summaryViewState.countries)

        swipeContainer.isRefreshing = summaryViewState.isLoading
    }

    private fun handleViewEvent(summaryViewEvent: SummaryViewEvent) = when (summaryViewEvent) {
        SummaryViewEvent.OpenAbout -> {
            val intent = Actions.openAbout(this)
            startActivity(intent)
        }
        is SummaryViewEvent.OpenCountryDetail -> {
            val intent = Actions.openTotalCases(this, summaryViewEvent.countryViewModel)
            startActivity(intent)
        }
        is SummaryViewEvent.ShowErrorMessage -> {
            Toast.makeText(applicationContext, summaryViewEvent.resource, Toast.LENGTH_SHORT)
                .show()
        }
    }
}
