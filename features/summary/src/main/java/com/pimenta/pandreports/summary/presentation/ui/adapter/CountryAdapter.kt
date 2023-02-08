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

package com.pimenta.pandreports.summary.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pimenta.pandreports.model.presentation.model.CountryViewModel
import com.pimenta.pandreports.presentation.extension.getDrawableByName
import com.pimenta.pandreports.summary.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_country.view.*

/**
 * Created by marcus on 30-03-2020.
 */
class CountryAdapter constructor(
    private val onItemViewClickedListener: (CountryViewModel) -> Unit
) : ListAdapter<CountryViewModel, CountryAdapter.CountryViewHolder>(CountryDiffCallback()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): CountryViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_country, viewGroup, false)
        return CountryViewHolder(view, onItemViewClickedListener)
    }

    override fun onBindViewHolder(viewHolder: CountryViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

    class CountryViewHolder(
        override val containerView: View,
        private val onItemViewClickedListener: (CountryViewModel) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(countryViewModel: CountryViewModel) {
            with(containerView) {
                flagImageView.setImageDrawable(context.getDrawableByName(countryViewModel.slug))
                nameTextView.text = countryViewModel.name
                totalConfirmedTextView.text = countryViewModel.totalConfirmed.toString()
                setOnClickListener {
                    onItemViewClickedListener(countryViewModel)
                }
            }
        }
    }
}
