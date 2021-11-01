package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.databinding.FragmentNewFeedBinding
import com.tuan2101.ezimarket.viewmodel.NewsFeedFragmentViewModel

class NewFeedFragment : Fragment() {

    lateinit var binding: FragmentNewFeedBinding
    private val exploreFragment = ExploreFragment()
    private val followingFragment = NewsFeedItemFragment("following")
    lateinit var viewModel: NewsFeedFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewFeedBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[NewsFeedFragmentViewModel::class.java]
        binding.model = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.currentSubject.observe(viewLifecycleOwner, {
            if (it.equals("2")) {
                transaction(followingFragment)
            }
            if (it.equals("1")) {
                transaction(exploreFragment)
            }
        })
        return binding.root
    }

    private fun transaction(fragment: Fragment) {
        childFragmentManager.beginTransaction().replace(R.id.transaction_layout, fragment).commit()
    }

}