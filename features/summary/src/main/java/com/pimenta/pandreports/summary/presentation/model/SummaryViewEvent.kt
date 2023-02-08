package com.pimenta.pandreports.summary.presentation.model

import androidx.annotation.StringRes
import com.pimenta.pandreports.model.presentation.model.CountryViewModel

sealed interface SummaryViewEvent {

    object OpenAbout : SummaryViewEvent
    data class ShowErrorMessage(@StringRes val resource: Int) : SummaryViewEvent
    data class OpenCountryDetail(val countryViewModel: CountryViewModel) : SummaryViewEvent
}
