package com.tuan2101.ezimarket.outsidefragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {

    lateinit var binding: FragmentNewsBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(NewsFragmentArgs.fromBundle(requireArguments()).url)
        return binding.root
    }
}