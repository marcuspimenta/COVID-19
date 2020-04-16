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

package com.pimenta.pandemicreports.summary.di.module

import com.pimenta.pandemicreports.presentation.di.annotation.ActivityScope
import com.pimenta.pandemicreports.summary.data.datasouce.remote.api.SummaryApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by marcus on 29-03-2020.
 */
@Module
class SummaryApiModule {

    @Provides
    @ActivityScope
    fun provideSummaryApi(retrofit: Retrofit) =
        retrofit.create(SummaryApi::class.java)
}