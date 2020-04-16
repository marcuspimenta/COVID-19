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

package com.pimenta.pandemicreports.totalcases.di.module

import com.pimenta.pandemicreports.presentation.di.annotation.ActivityScope
import com.pimenta.pandemicreports.totalcases.data.datasource.remote.datasource.TotalCasesRemoteDataSource
import com.pimenta.pandemicreports.totalcases.data.datasource.remote.datasource.TotalCasesRemoteDataSourceInterface
import dagger.Binds
import dagger.Module

/**
 * Created by marcus on 13-04-2020.
 */
@Module
interface TotalCasesRemoteDataSourceModule {

    @Binds
    @ActivityScope
    fun providesTotalCasesRemoteDataSource(
        remoteDateSource: TotalCasesRemoteDataSource
    ): TotalCasesRemoteDataSourceInterface
}