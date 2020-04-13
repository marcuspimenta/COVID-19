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

package com.pimenta.covid19.totalcases.domain

import com.pimenta.covid19.model.domain.CountryCaseDomainModel
import com.pimenta.covid19.totalcases.data.repository.TotalCasesRepositoryInterface
import io.reactivex.Single
import io.reactivex.functions.Function3
import javax.inject.Inject

/**
 * Created by marcus on 13-04-2020.
 */
private const val CONFIRMED = "confirmed"
private const val RECOVERED = "recovered"
private const val DEATHS = "deaths"

class GetStatusCasesByCountryUseCase @Inject constructor(
    private val totalCasesRepository: TotalCasesRepositoryInterface
) : GetStatusCasesByCountryUseCaseInterface {

    override fun invoke(
        slug: String
    ): Single<Triple<List<CountryCaseDomainModel>?, List<CountryCaseDomainModel>?, List<CountryCaseDomainModel>?>> =
        Single.zip(
            totalCasesRepository.getTotalCasesByType(slug, CONFIRMED),
            totalCasesRepository.getTotalCasesByType(slug, RECOVERED),
            totalCasesRepository.getTotalCasesByType(slug, DEATHS),
            Function3 { confirmed, recovered, deaths ->
                Triple(confirmed, recovered, deaths)
            }
        )

}