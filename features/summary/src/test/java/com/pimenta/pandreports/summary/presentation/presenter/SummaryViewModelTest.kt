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

package com.pimenta.pandreports.summary.presentation.presenter

import com.nhaarman.mockitokotlin2.*
import com.pimenta.pandreports.model.domain.CountryDomainModel
import com.pimenta.pandreports.model.presentation.model.CountryViewModel
import com.pimenta.pandreports.presentation.scheduler.RxScheduler
import com.pimenta.pandreports.summary.R
import com.pimenta.pandreports.summary.domain.GetSummaryUseCaseInterface
import com.pimenta.pandreports.summary.presentation.model.SummaryViewEvent
import com.pimenta.pandreports.summary.presentation.model.SummaryViewState
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Before
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

class SummaryViewModelTest {

    private val stateObserver = TestObserver<SummaryViewState>()
    private val eventsObserver = TestObserver<SummaryViewEvent>()
    private val getSummaryUseCase: GetSummaryUseCaseInterface = mock()
    private val rxSchedulerTest = RxScheduler(
        Schedulers.trampoline(),
        Schedulers.trampoline(),
        Schedulers.trampoline()
    )

    private val viewModel = SummaryViewModel(
        getSummaryUseCase,
        rxSchedulerTest
    )

    @Before
    fun init() {
        viewModel.state.subscribe(stateObserver)
        viewModel.events.subscribe(eventsObserver)
    }

    @Test
    fun `should load the summary view when the data is loaded without an error`() {
        whenever(getSummaryUseCase()).thenReturn(Single.just(COUNTRY_DOMAIN_MODEL_LIST))

        viewModel.onInit()

        stateObserver.assertValues(
            SummaryViewState.INITIAL,
            SummaryViewState.INITIAL.copy(state = SummaryViewState.State.Loading),
            SummaryViewState.INITIAL.copy(
                state = SummaryViewState.State.Loading,
                countries = COUNTRY_VIEW_MODEL_LIST
            ),
            SummaryViewState.INITIAL.copy(
                state = SummaryViewState.State.None,
                countries = COUNTRY_VIEW_MODEL_LIST
            )
        )
        eventsObserver.assertNoValues()
    }

    @Test
    fun `should show an error message view when the summary data returns an error`() {
        whenever(getSummaryUseCase()).thenReturn(Single.error(Throwable()))

        viewModel.onInit()

        stateObserver.assertValues(
            SummaryViewState.INITIAL,
            SummaryViewState.INITIAL.copy(state = SummaryViewState.State.Loading),
            SummaryViewState.INITIAL.copy(state = SummaryViewState.State.None)
        )
        eventsObserver.assertValues(
            SummaryViewEvent.ShowErrorMessage(R.string.error_message)
        )
    }

    @Test
    fun `should open the total cases view when clicks in a country`() {
        viewModel.onCountryClicked(COUNTRY_VIEW_MODEL)

        stateObserver.assertValues(
            SummaryViewState.INITIAL
        )
        eventsObserver.assertValues(
            SummaryViewEvent.OpenCountryDetail(COUNTRY_VIEW_MODEL)
        )
    }

    @Test
    fun `should open the about view when clicks in about icon`() {
        viewModel.onAboutClicked()

        stateObserver.assertValues(
            SummaryViewState.INITIAL
        )
        eventsObserver.assertValues(
            SummaryViewEvent.OpenAbout
        )
    }
}
