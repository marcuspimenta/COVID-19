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

package com.pimenta.pandemicreports.model.data.mapper

import com.pimenta.pandemicreports.model.data.remote.CountryResponse
import com.pimenta.pandemicreports.model.domain.CountryDomainModel

/**
 * Created by marcus on 29-03-2020.
 */
private const val DEFAULT_VALUE = "-"

fun CountryResponse.toDomainModel() = CountryDomainModel(
    name = name ?: DEFAULT_VALUE,
    slug = slug ?: DEFAULT_VALUE,
    newConfirmed = newConfirmed,
    totalConfirmed = totalConfirmed,
    newDeaths = newDeaths,
    totalDeaths = totalDeaths,
    newRecovered = newRecovered,
    totalRecovered = totalRecovered
)