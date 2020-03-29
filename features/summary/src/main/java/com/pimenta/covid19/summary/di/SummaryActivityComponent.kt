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

package com.pimenta.covid19.summary.di

import com.pimenta.covid19.presentation.di.annotation.ActivityScope
import com.pimenta.covid19.summary.di.module.*
import com.pimenta.covid19.summary.presentation.presenter.SummaryContract
import com.pimenta.covid19.summary.presentation.ui.activity.SummaryActivity
import dagger.BindsInstance
import dagger.Subcomponent

/**
 * Created by marcus on 29-03-2020.
 */
@ActivityScope
@Subcomponent(
    modules = [
        SummaryApiModule::class,
        SummaryRemoteDataSourceModule::class,
        SummaryRepositoryModule::class,
        SummaryUseCaseModule::class,
        SummaryPresenterModule::class
    ]
)
interface SummaryActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance view: SummaryContract.View): SummaryActivityComponent
    }

    fun inject(activity: SummaryActivity)
}