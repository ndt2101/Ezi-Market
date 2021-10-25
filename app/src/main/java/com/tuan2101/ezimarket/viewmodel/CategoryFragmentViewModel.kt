package com.tuan2101.ezimarket.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.dataclasses.CategoryItem
import com.tuan2101.ezimarket.dataclasses.ParentCategory

class CategoryFragmentViewModel() : ViewModel() {
    val selectedParentCategoryItem = MutableLiveData<ParentCategory>()
    val selectedSubCategoryItem = MutableLiveData<String>()

    init {
        selectedParentCategoryItem.value = dummyDataForListCategory()[0]
    }

    fun onSelectParentCategoryItem(item: ParentCategory) {
        selectedParentCategoryItem.value = item
    }

    fun onSelectedSubItem(id: String) {
        selectedSubCategoryItem.value = id
        Log.i("ttt", id)
    }

    fun dummyDataForListCategory(): ArrayList<ParentCategory> {
        val list = ArrayList<ParentCategory>()

        list.add(ParentCategory(0.toString(), R.drawable.logo, "aaa",
            listOf(CategoryItem(0.toString(), R.drawable.logo, "a1"),
            CategoryItem(1.toString(), R.drawable.logo, "a2"),
            CategoryItem(2.toString(), R.drawable.logo, "a3"),
            CategoryItem(3.toString(), R.drawable.logo, "a4")),"https://d1j8r0kxyu9tj8.cloudfront.net/images/1562663273OyCyS9raIe4XXr1.jpg"))

        list.add(ParentCategory(1.toString(), R.drawable.logo, "bbb",
            listOf(CategoryItem(0.toString(), R.drawable.logo, "b1"),
                CategoryItem(1.toString(), R.drawable.logo, "b2"),
                CategoryItem(2.toString(), R.drawable.logo, "b3"),
                CategoryItem(3.toString(), R.drawable.logo, "b4")), "https://d1j8r0kxyu9tj8.cloudfront.net/images/15626633735758OrO5uOAdabv.jpg"))

        list.add(ParentCategory(2.toString(), R.drawable.logo, "ccc",
            listOf(CategoryItem(0.toString(), R.drawable.logo, "c1"),
                CategoryItem(1.toString(), R.drawable.logo, "c2"),
                CategoryItem(2.toString(), R.drawable.logo, "c3"),
                CategoryItem(3.toString(), R.drawable.logo, "c4")), "https://d1j8r0kxyu9tj8.cloudfront.net/images/1562663273OyCyS9raIe4XXr1.jpg"))

        list.add(ParentCategory(3.toString(), R.drawable.logo, "ddd",
            listOf(CategoryItem(0.toString(), R.drawable.logo, "d1"),
                CategoryItem(1.toString(), R.drawable.logo, "d2"),
                CategoryItem(2.toString(), R.drawable.logo, "d3"),
                CategoryItem(3.toString(), R.drawable.logo, "d4")), "https://d1j8r0kxyu9tj8.cloudfront.net/images/1562663273OyCyS9raIe4XXr1.jpg"))

        list.add(ParentCategory(4.toString(), R.drawable.logo, "eee",
            listOf(CategoryItem(0.toString(), R.drawable.logo, "e1"),
                CategoryItem(1.toString(), R.drawable.logo, "e2"),
                CategoryItem(2.toString().toString(), R.drawable.logo, "e3"),
                CategoryItem(3.toString(), R.drawable.logo, "e4")), "https://d1j8r0kxyu9tj8.cloudfront.net/images/1562663273OyCyS9raIe4XXr1.jpg"))

        list.add(ParentCategory(5.toString(), R.drawable.logo, "fff",
            listOf(CategoryItem(0.toString(), R.drawable.logo, "f1"),
                CategoryItem(1.toString(), R.drawable.logo, "f2"),
                CategoryItem(2.toString().toString(), R.drawable.logo, "f3"),
                CategoryItem(3.toString(), R.drawable.logo, "f4")), "https://d1j8r0kxyu9tj8.cloudfront.net/images/1562663273OyCyS9raIe4XXr1.jpg"))

        list.add(ParentCategory(6.toString(), R.drawable.logo, "ggg",
            listOf(CategoryItem(0.toString(), R.drawable.logo, "g1"),
                CategoryItem(1.toString(), R.drawable.logo, "g2"),
                CategoryItem(2.toString(), R.drawable.logo, "g3"),
                CategoryItem(3.toString(), R.drawable.logo, "g4")), "https://d1j8r0kxyu9tj8.cloudfront.net/images/1562663273OyCyS9raIe4XXr1.jpg"))

        list.add(ParentCategory(7.toString(), R.drawable.logo, "hhh",
            listOf(CategoryItem(0.toString(), R.drawable.logo, "h1"),
                CategoryItem(1.toString(), R.drawable.logo, "h2"),
                CategoryItem(2.toString(), R.drawable.logo, "h3"),
                CategoryItem(3.toString(), R.drawable.logo, "h4")), "https://d1j8r0kxyu9tj8.cloudfront.net/images/1562663273OyCyS9raIe4XXr1.jpg"))

        list.add(ParentCategory(8.toString(), R.drawable.logo, "jjj",
            listOf(CategoryItem(0.toString(), R.drawable.logo, "j1"),
                CategoryItem(1.toString(), R.drawable.logo, "j2"),
                CategoryItem(2.toString(), R.drawable.logo, "j3"),
                CategoryItem(3.toString(), R.drawable.logo, "j4")), "https://d1j8r0kxyu9tj8.cloudfront.net/images/1562663273OyCyS9raIe4XXr1.jpg"))

        list.add(ParentCategory(9.toString(), R.drawable.logo, "kkk",
            listOf(CategoryItem(0.toString(), R.drawable.logo, "k1"),
                CategoryItem(1.toString(), R.drawable.logo, "k2"),
                CategoryItem(2.toString(), R.drawable.logo, "k3"),
                CategoryItem(3.toString(), R.drawable.logo, "k4")), "https://d1j8r0kxyu9tj8.cloudfront.net/images/1562663273OyCyS9raIe4XXr1.jpg"))

        return list
    }
}