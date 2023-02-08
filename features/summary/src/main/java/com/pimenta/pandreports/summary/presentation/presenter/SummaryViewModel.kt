package com.pimenta.pandreports.summary.presentation.presenter

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pimenta.pandreports.model.presentation.mapper.toViewModel
import com.pimenta.pandreports.model.presentation.model.CountryViewModel
import com.pimenta.pandreports.presentation.presenter.BaseViewModel
import com.pimenta.pandreports.presentation.scheduler.RxScheduler
import com.pimenta.pandreports.summary.R
import com.pimenta.pandreports.summary.domain.GetSummaryUseCaseInterface
import com.pimenta.pandreports.summary.presentation.model.SummaryViewEvent
import com.pimenta.pandreports.summary.presentation.model.SummaryViewState
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

private const val TAG = "SummaryPresenter"

class SummaryViewModel @Inject constructor(
    private val getSummaryUseCase: GetSummaryUseCaseInterface,
    private val rxScheduler: RxScheduler
) : BaseViewModel<SummaryViewState, SummaryViewEvent>() {

    init {
        emitState(SummaryViewState.INITIAL)
    }

    fun onInit() {
        loadSummary()
    }

    fun onRefreshed() {
        loadSummary()
    }

    fun onCountryClicked(countryViewModel: CountryViewModel) {
        emitEvent(SummaryViewEvent.OpenCountryDetail(countryViewModel))
    }

    fun onAboutClicked() {
        emitEvent(SummaryViewEvent.OpenAbout)
    }

    private fun loadSummary() {
        getSummaryUseCase()
            .subscribeOn(rxScheduler.ioScheduler)
            .observeOn(rxScheduler.computationScheduler)
            .map { it.map { country -> country.toViewModel() } }
            .observeOn(rxScheduler.mainScheduler)
            .doOnSubscribe {
                emitState(currentState.copy(state = SummaryViewState.State.Loading))
            }
            .doFinally {
                emitState(currentState.copy(state = SummaryViewState.State.None))
            }
            .subscribe({ result ->
                emitState(currentState.copy(countries = result))
            }, { throwable ->
                Log.e(TAG, "Error while loading the summary" + throwable.message)
                emitEvent(SummaryViewEvent.ShowErrorMessage(R.string.error_message))
            }).also { compositeDisposable.add(it) }
    }
}
