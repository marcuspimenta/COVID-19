package com.pimenta.pandreports.model.presentation.mapper

import com.github.mikephil.charting.data.Entry
import com.pimenta.pandreports.model.domain.CountryCaseDomainModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by marcus on 13-04-2020.
 */
fun List<CountryCaseDomainModel>?.toViewModel(): Pair<List<String>, List<List<Entry>>>? =
    this?.groupBy { it.date }
        ?.mapValues { entry ->
            entry.value.flatMap {
                val confirmed = entry.value.sumBy { it.confirmed }
                val deaths = entry.value.sumBy { it.deaths }

                listOf(
                    confirmed,
                    deaths
                )
            }
        }?.let {
            val keys = it.keys.map { date ->
                SimpleDateFormat("MMM", Locale.ENGLISH).format(
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH).parse(date) as Date
                )
            }.toList()
            val confirmed = mutableListOf<Entry>()
            val deaths = mutableListOf<Entry>()

            it.values.forEachIndexed { index, list ->
                list.forEachIndexed { totalIndex, totalValue ->
                    when (totalIndex) {
                        0 -> confirmed
                        1 -> deaths
                        else -> null
                    }?.add(Entry(index.toFloat(), totalValue.toFloat()))
                }
            }

            val entries = listOf(
                confirmed,
                deaths
            )

            keys to entries
        }