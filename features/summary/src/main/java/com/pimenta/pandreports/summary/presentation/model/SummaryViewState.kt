package com.pimenta.pandreports.summary.presentation.model

import com.pimenta.pandreports.model.presentation.model.CountryViewModel

data class SummaryViewState(
    val state: State,
    val countries: List<CountryViewModel>
) {

    val isLoading = state == State.Loading

    sealed class State {

        object Loading : State()
        object None : State()
    }

    companion object {
        val INITIAL = SummaryViewState(
            state = State.Loading,
            countries = emptyList()
        )
    }
}
