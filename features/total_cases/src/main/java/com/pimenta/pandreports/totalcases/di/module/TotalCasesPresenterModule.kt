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

package com.pimenta.pandreports.totalcases.di.module

import com.pimenta.pandreports.presentation.di.annotation.ActivityScope
import com.pimenta.pandreports.totalcases.presentation.presenter.TotalCasesContract
import com.pimenta.pandreports.totalcases.presentation.presenter.TotalCasesPresenter
import dagger.Binds
import dagger.Module

/**
 * Created by marcus on 05-04-2020.
 */
@Module
interface TotalCasesPresenterModule {

    @Binds
    @ActivityScope
    fun providesTotalCasesPresenter(
        totalCasesPresenter: TotalCasesPresenter
    ): TotalCasesContract.Presenter
}