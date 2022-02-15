package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.adapter.PostAdapter
import com.tuan2101.ezimarket.databinding.FragmentPersonalPageBinding
import com.tuan2101.ezimarket.dataclasses.Post
import com.tuan2101.ezimarket.viewmodel.PersonalPageFragmentViewModel
import com.tuan2101.ezimarket.viewmodel.PersonalPageFragmentViewModelFactory

class PersonalPageFragment : Fragment() {

    lateinit var binding: FragmentPersonalPageBinding
    lateinit var viewModel: PersonalPageFragmentViewModel
    private val followingFragment = NewsFeedItemFragment("personal_page")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPersonalPageBinding.inflate(inflater, container, false)
        val userId = PersonalPageFragmentArgs.fromBundle(requireArguments()).userId
        val factory = PersonalPageFragmentViewModelFactory(userId)
        viewModel = ViewModelProvider(this, factory)[PersonalPageFragmentViewModel::class.java]

        binding.model = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        transaction(followingFragment)
        return binding.root
    }

    private fun transaction(fragment: Fragment) {
        childFragmentManager.beginTransaction().replace(R.id.transaction_layout, fragment).commit()
    }
}