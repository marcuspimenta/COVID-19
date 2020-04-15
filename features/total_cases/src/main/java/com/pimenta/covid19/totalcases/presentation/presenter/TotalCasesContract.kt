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

package com.pimenta.covid19.totalcases.presentation.presenter

import androidx.annotation.StringRes
import com.github.mikephil.charting.data.Entry
import com.pimenta.covid19.model.presentation.model.CountryViewModel
import com.pimenta.covid19.presentation.presenter.BaseContract

/**
 * Created by marcus on 31-03-2020.
 */
interface TotalCasesContract {

    interface Presenter : BaseContract.Presenter {

        fun initView(countryViewModel: CountryViewModel)

        fun loadCases(countryViewModel: CountryViewModel)
    }

    interface View {

        fun showProgress()

        fun hideProgress()

        fun showCountryFlag(countrySlug: String)

        fun showCountryName(countryName: String)

        fun showConfirmedCases(confirmedCases: String)

        fun showNewCases(newCases: String)

        fun showRecoveredCases(recoveredCases: String)

        fun showDeaths(deaths: String)

        fun showGraph(cases: Pair<List<String>, List<List<Entry>>>)

        fun showErrorMessage(@StringRes resource: Int)
    }
}