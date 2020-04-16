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

package com.pimenta.pandemicreports.summary.presentation.presenter

import com.nhaarman.mockitokotlin2.*
import com.pimenta.pandemicreports.model.domain.CountryDomainModel
import com.pimenta.pandemicreports.model.presentation.model.CountryViewModel
import com.pimenta.pandemicreports.presentation.scheduler.RxScheduler
import com.pimenta.pandemicreports.summary.R
import com.pimenta.pandemicreports.summary.domain.GetSummaryUseCaseInterface
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Test

/**
 * Created by marcus on 10-04-2020.
 */
private val COUNTRY_DOMAIN_MODEL = CountryDomainModel(
    name = "Brazil",
    slug = "br",
    newConfirmed = 1000,
    totalConfirmed = 1000,
    newDeaths = 1000,
    totalDeaths = 1000,
    newRecovered = 1000,
    totalRecovered = 1000
)
private val COUNTRY_DOMAIN_MODEL_LIST = listOf(
    COUNTRY_DOMAIN_MODEL
)
private val COUNTRY_VIEW_MODEL = CountryViewModel(
    name = "Brazil",
    slug = "br",
    newConfirmed = "1,000",
    totalConfirmed = "1,000",
    newDeaths = "1,000",
    totalDeaths = "1,000",
    newRecovered = "1,000",
    totalRecovered = "1,000"
)
private val COUNTRY_VIEW_MODEL_LIST = listOf(
    COUNTRY_VIEW_MODEL
)

class SummaryPresenterTest {

    private val view: SummaryContract.View = mock()
    private val getSummaryUseCase: GetSummaryUseCaseInterface = mock()
    private val rxSchedulerTest = RxScheduler(
        Schedulers.trampoline(),
        Schedulers.trampoline(),
        Schedulers.trampoline()
    )

    private val presenter = SummaryPresenter(
        view,
        getSummaryUseCase,
        rxSchedulerTest
    )

    @Test
    fun `should load the summary view when the data is loaded without an error`() {
        whenever(getSummaryUseCase()).thenReturn(Single.just(COUNTRY_DOMAIN_MODEL_LIST))

        presenter.loadSummary()

        inOrder(view) {
            verify(view).showProgress()
            verify(view).showCountries(COUNTRY_VIEW_MODEL_LIST)
            verify(view).hideProgress()
            verifyNoMoreInteractions(view)
        }
    }

    @Test
    fun `should show an error message view when the summary data returns an error`() {
        whenever(getSummaryUseCase()).thenReturn(Single.error(Throwable()))

        presenter.loadSummary()

        inOrder(view) {
            verify(view).showProgress()
            verify(view).showErrorMessage(R.string.error_message)
            verify(view).hideProgress()
            verifyNoMoreInteractions(view)
        }
    }

    @Test
    fun `should open the total cases view when clicks in a country`() {
        presenter.countryClicked(COUNTRY_VIEW_MODEL)

        verify(view, only()).openTotalCases(COUNTRY_VIEW_MODEL)
    }

    @Test
    fun `should open the about view when clicks in about icon`() {
        presenter.aboutClicked()

        verify(view, only()).openAbout()
    }
}