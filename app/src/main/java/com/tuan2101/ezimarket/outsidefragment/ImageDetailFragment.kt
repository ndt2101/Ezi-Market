package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.tuan2101.ezimarket.databinding.FragmentImageDetailBinding


class ImageDetailFragment : Fragment() {

    lateinit var binding: FragmentImageDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageDetailBinding.inflate(inflater, container, false)

        val imageUrl: String = ImageDetailFragmentArgs.fromBundle(requireArguments()).imageUrl

        // load image
        Glide.with(requireContext()).load(imageUrl).into(binding.myZoomageView)
        return binding.root
    }

}