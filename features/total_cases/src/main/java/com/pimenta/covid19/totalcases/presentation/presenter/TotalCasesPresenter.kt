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

import com.pimenta.covid19.model.presentation.model.CountryViewModel
import com.pimenta.covid19.presentation.presenter.BasePresenter
import javax.inject.Inject

/**
 * Created by marcus on 31-03-2020.
 */
class TotalCasesPresenter @Inject constructor(
    private val view: TotalCasesContract.View
) : BasePresenter(), TotalCasesContract.Presenter {

    override fun initView(countryViewModel: CountryViewModel) {
        with(countryViewModel) {
            view.showCountryFlag(slug)
            view.showCountryName(name)
            view.showConfirmedCases(totalConfirmed)
            view.showNewCases(newConfirmed)
            view.showDeaths(totalDeaths)
            view.showRecoveredCases(totalRecovered)
        }
    }

}