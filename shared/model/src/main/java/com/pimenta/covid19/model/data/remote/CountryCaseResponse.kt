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
 * Created by marcus on 13-04-2020.
 */
data class CountryCaseResponse(
    @SerializedName("Country") val country: String?,
    @SerializedName("CountryCode") val countryCode: String?,
    @SerializedName("Lat") val lat: String?,
    @SerializedName("Lon") val lon: String?,
    @SerializedName("Cases") val cases: Int?,
    @SerializedName("Status") val status: String?,
    @SerializedName("Date") val date: String?
)