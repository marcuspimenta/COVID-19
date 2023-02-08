package com.pimenta.pandreports.summary.di.module

import androidx.lifecycle.ViewModel
import com.pimenta.pandreports.presentation.di.annotation.ViewModelKey
import com.pimenta.pandreports.summary.presentation.presenter.SummaryViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SummaryViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SummaryViewModel::class)
    abstract fun bindsMainViewModel(viewModel: SummaryViewModel): ViewModel
}
