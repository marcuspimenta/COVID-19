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

package com.pimenta.pandemicreports.application

import android.app.Application
import com.pimenta.pandemicreports.di.ApplicationComponent
import com.pimenta.pandemicreports.di.DaggerApplicationComponent
import com.pimenta.pandemicreports.di.module.ApplicationModule
import com.pimenta.pandemicreports.summary.di.SummaryActivityComponent
import com.pimenta.pandemicreports.summary.di.SummaryActivityComponentProvider
import com.pimenta.pandemicreports.totalcases.di.TotalCasesActivityComponent
import com.pimenta.pandemicreports.totalcases.di.TotalCasesActivityComponentProvider

/**
 * Created by marcus on 29-03-2020.
 */
class PandemicReportsApplication : Application(),
    SummaryActivityComponentProvider,
    TotalCasesActivityComponentProvider {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun summaryActivityComponentFactory(): SummaryActivityComponent.Factory =
        applicationComponent.summaryActivityComponent()

    override fun totalCasesActivityComponentFactory(): TotalCasesActivityComponent.Factory =
        applicationComponent.totalCasesActivityComponent()
}