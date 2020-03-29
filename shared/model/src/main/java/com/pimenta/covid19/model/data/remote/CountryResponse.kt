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

package com.pimenta.covid19.model.data.remote

import com.google.gson.annotations.SerializedName

/**
 * Created by marcus on 29-03-2020.
 */
data class CountryResponse(
    @SerializedName("Country") val name: String?,
    @SerializedName("CountrySlug") val slug: String?,
    @SerializedName("NewConfirmed") val newConfirmed: Int?,
    @SerializedName("TotalConfirmed") val totalConfirmed: Int?,
    @SerializedName("NewDeaths") val newDeaths: Int?,
    @SerializedName("TotalDeaths") val totalDeaths: Int?,
    @SerializedName("NewRecovered") val newRecovered: Int?,
    @SerializedName("TotalRecovered") val totalRecovered: Int?
)