package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuan2101.ezimarket.adapter.ParentCategoryAdapter
import com.tuan2101.ezimarket.adapter.ParentCategoryItemViewHolder
import com.tuan2101.ezimarket.adapter.TopCategoryItemAdapter
import com.tuan2101.ezimarket.adapter.TopCategoryItemViewHolder
import com.tuan2101.ezimarket.databinding.FragmentCategoryBinding
import com.tuan2101.ezimarket.dataclasses.ParentCategory
import com.tuan2101.ezimarket.viewmodel.CategoryFragmentViewModel

class CategoryFragment : Fragment() {

    lateinit var binding: FragmentCategoryBinding
    lateinit var viewModel: CategoryFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(CategoryFragmentViewModel::class.java)

        val subAdapter =
            TopCategoryItemAdapter(TopCategoryItemViewHolder.TopCategoryItemClickListener { id ->
                viewModel.onSelectedSubItem(id)
            })
        val parentAdapter =
            ParentCategoryAdapter(ParentCategoryItemViewHolder.ParentCategoryItemClickListener { item: ParentCategory ->
                viewModel.onSelectParentCategoryItem(item)
            }, viewModel.selectedParentCategoryItem, viewLifecycleOwner)

        binding.listSuperCategory.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        parentAdapter.submitList(viewModel.dummyDataForListCategory())
        binding.listSuperCategory.adapter = parentAdapter
        binding.listSubCategory.layoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        binding.listSubCategory.adapter = subAdapter
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.selectedParentCategoryItem.observe(viewLifecycleOwner, {
            subAdapter.submitList(it.subCategoryList)
        })
        return binding.root
    }
}