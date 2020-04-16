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

package com.pimenta.pandemicreports.model.presentation.mapper

import com.pimenta.pandemicreports.model.domain.CountryDomainModel
import com.pimenta.pandemicreports.model.presentation.model.CountryViewModel
import java.text.DecimalFormat

/**
 * Created by marcus on 29-03-2020.
 */
private const val PATTERN = "###,###,###,###"
private const val DEFAULT_VALUE = "-"
private val FORMATTER = DecimalFormat(PATTERN)

fun CountryDomainModel.toViewModel() = CountryViewModel(
    name = name,
    slug = slug.replace("-", "_"),
    newConfirmed = newConfirmed?.let { FORMATTER.format(it) } ?: DEFAULT_VALUE,
    totalConfirmed = totalConfirmed?.let { FORMATTER.format(it) } ?: DEFAULT_VALUE,
    newDeaths = newDeaths?.let { FORMATTER.format(it) } ?: DEFAULT_VALUE,
    totalDeaths = totalDeaths?.let { FORMATTER.format(it) } ?: DEFAULT_VALUE,
    newRecovered = newRecovered?.let { FORMATTER.format(it) } ?: DEFAULT_VALUE,
    totalRecovered = totalRecovered?.let { FORMATTER.format(it) } ?: DEFAULT_VALUE
)

fun CountryViewModel.toDomainModel() = CountryDomainModel(
    name = name,
    slug = slug.replace("_", "-")
)