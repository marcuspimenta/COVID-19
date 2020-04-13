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

package com.pimenta.covid19.totalcases.presentation.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pimenta.covid19.actions.EXTRA_COUNTRY
import com.pimenta.covid19.model.presentation.model.CountryViewModel
import com.pimenta.covid19.presentation.extension.getDrawableByName
import com.pimenta.covid19.totalcases.R
import com.pimenta.covid19.totalcases.di.TotalCasesActivityComponentProvider
import com.pimenta.covid19.totalcases.presentation.presenter.TotalCasesContract
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

        presenter.initView(countryViewModel)
        presenter.loadCases(countryViewModel)
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }

    override fun showCountryFlag(countrySlug: String) {
        flagImageView.setImageDrawable(getDrawableByName(countrySlug))
    }

    override fun showCountryName(countryName: String) {
        nameTextView.text = countryName
    }

    override fun showConfirmedCases(confirmedCases: String) {
        confirmedCasesTextView.text = String.format(getString(R.string.confirmed_cases), confirmedCases)
    }

    override fun showNewCases(newCases: String) {
        newCasesTextView.text = String.format(getString(R.string.new_cases), newCases)
    }

    override fun showDeaths(deaths: String) {
        deathsTextView.text = String.format(getString(R.string.deaths), deaths)
    }

    override fun showRecoveredCases(recoveredCases: String) {
        recoveredCasesTextView.text = String.format(getString(R.string.recovered_cases), recoveredCases)
    }

}