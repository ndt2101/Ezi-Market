package com.tuan2101.ezimarket.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.tuan2101.ezimarket.databinding.LocationItemBinding
import com.tuan2101.ezimarket.databinding.SelectedLocationItemBinding
import com.tuan2101.ezimarket.dataclasses.LocationParent

/**
 * Created by ndt2101 on 11/21/2021.
 */
class LocationAdapter(context: Context, val resource: Int,val list: List<LocationParent>)
    : ArrayAdapter<LocationParent>(context, resource, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = SelectedLocationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.locationName.text = list[position].name
        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = LocationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.locationName.text = list[position].name
        return binding.root
    }
}