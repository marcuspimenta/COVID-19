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

package com.pimenta.covid19.summary.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.ethanhua.skeleton.Skeleton
import com.pimenta.covid19.model.presentation.model.CountryViewModel
import com.pimenta.covid19.summary.R
import com.pimenta.covid19.summary.di.SummaryActivityComponentProvider
import com.pimenta.covid19.summary.presentation.presenter.SummaryContract
import com.pimenta.covid19.summary.presentation.ui.adapter.CountryAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * Created by marcus on 29-03-2020.
 */
class SummaryActivity : AppCompatActivity(), SummaryContract.View,
    CountryAdapter.OnItemViewClickedListener {

    private val countryAdapter: CountryAdapter by lazy {
        CountryAdapter(this)
    }

    private val skeletonScreen by lazy {
        Skeleton.bind(countriesRecyclerView)
            .adapter(countryAdapter)
            .load(R.layout.item_country)
            .shimmer(true)
            .angle(20)
            .show()
    }

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
    }

    override fun onResume() {
        super.onResume()
        presenter.loadSummary()
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }

    override fun showProgress() {
        skeletonScreen.show()
    }

    override fun hideProgress() {
        skeletonScreen.hide()
    }

    override fun showCountries(countries: List<CountryViewModel>) {
        countryAdapter.submitList(countries)
    }

    override fun onItemClicked(countryViewModel: CountryViewModel) {

    }
}