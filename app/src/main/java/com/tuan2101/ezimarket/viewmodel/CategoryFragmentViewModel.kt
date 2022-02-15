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

        list.add(
            ParentCategory(
                0.toString(), R.drawable.logo, "Mẹ và bé",
                listOf(
                    CategoryItem(0.toString(), R.drawable.logo, "Tã, Bỉm"),
                    CategoryItem(1.toString(), R.drawable.logo, "Dinh dưỡng"),
                    CategoryItem(2.toString(), R.drawable.logo, "Đồ dùng cho bé"),
                    CategoryItem(3.toString(), R.drawable.logo, "Đồ chơi"),
                    CategoryItem(3.toString(), R.drawable.logo, "Thời trang cho mẹ"),
                    CategoryItem(3.toString(), R.drawable.logo, "Thời trang cho bé")
                ),
                "https://d1j8r0kxyu9tj8.cloudfront.net/images/1562663273OyCyS9raIe4XXr1.jpg"
            )
        )

        list.add(
            ParentCategory(
                1.toString(), R.drawable.logo, "Thời trang nữ",
                listOf(
                    CategoryItem(0.toString(), R.drawable.logo, "Áo nữ"),
                    CategoryItem(1.toString(), R.drawable.logo, "Quần nữ"),
                    CategoryItem(2.toString(), R.drawable.logo, "Váy"),
                    CategoryItem(3.toString(), R.drawable.logo, "Giày nữ"),
                    CategoryItem(3.toString(), R.drawable.logo, "Đồ ngủ nữ")
                ), "https://d1j8r0kxyu9tj8.cloudfront.net/images/15626633735758OrO5uOAdabv.jpg"
            )
        )

        list.add(
            ParentCategory(
                2.toString(), R.drawable.logo, "Ezi Ngon",
                listOf(
                    CategoryItem(0.toString(), R.drawable.logo, "Ăn vặt"),
                    CategoryItem(1.toString(), R.drawable.logo, "Đồ tươi sống"),
                    CategoryItem(3.toString(), R.drawable.logo, "Rau củ quả")
                ), "https://d1j8r0kxyu9tj8.cloudfront.net/images/15626633735758OrO5uOAdabv.jpg"
            )
        )

        list.add(
            ParentCategory(
                3.toString(), R.drawable.logo, "Thời trang nam",
                listOf(
                    CategoryItem(0.toString(), R.drawable.logo, "Áo nam"),
                    CategoryItem(1.toString(), R.drawable.logo, "Quần nam"),
                    CategoryItem(2.toString(), R.drawable.logo, "Giày nam"),
                    CategoryItem(3.toString(), R.drawable.logo, "Đồ ngủ nam")
                ), "https://d1j8r0kxyu9tj8.cloudfront.net/images/1562663273OyCyS9raIe4XXr1.jpg"
            )
        )

        list.add(
            ParentCategory(
                4.toString(), R.drawable.logo, "Nhà cửa - Đời sống",
                listOf(
                    CategoryItem(0.toString(), R.drawable.logo, "Dụng cụ nhà bếp"),
                    CategoryItem(1.toString(), R.drawable.logo, "Nội thất"),
                    CategoryItem(2.toString(), R.drawable.logo, "Nhạc cụ"),
                    CategoryItem(2.toString(), R.drawable.logo, "Đồ gia dụng")
                ), "https://d1j8r0kxyu9tj8.cloudfront.net/images/1562663273OyCyS9raIe4XXr1.jpg"
            )
        )

        list.add(
            ParentCategory(
                5.toString(), R.drawable.logo, "Đồ điện tử",
                listOf(
                    CategoryItem(0.toString(), R.drawable.logo, "Điện thoại"),
                    CategoryItem(1.toString(), R.drawable.logo, "Máy tính"),
                ), "https://d1j8r0kxyu9tj8.cloudfront.net/images/1562663273OyCyS9raIe4XXr1.jpg"
            )
        )

        list.add(
            ParentCategory(
                6.toString(), R.drawable.logo, "Xe",
                listOf(
                    CategoryItem(0.toString(), R.drawable.logo, "Xe máy"),
                    CategoryItem(1.toString(), R.drawable.logo, "Xe đạp"),
                    CategoryItem(2.toString(), R.drawable.logo, "Xe đạp điện"),
                ), "https://d1j8r0kxyu9tj8.cloudfront.net/images/1562663273OyCyS9raIe4XXr1.jpg"
            )
        )

        list.add(
            ParentCategory(
                7.toString(), R.drawable.logo, "Quà tặng",
                listOf(
                    CategoryItem(0.toString(), R.drawable.logo, "Hoa"),
                    CategoryItem(1.toString(), R.drawable.logo, "Đồ thủ công"),
                    CategoryItem(2.toString(), R.drawable.logo, "Gấu bông"),
                ), "https://d1j8r0kxyu9tj8.cloudfront.net/images/1562663273OyCyS9raIe4XXr1.jpg"
            )
        )

        list.add(
            ParentCategory(
                8.toString(), R.drawable.logo, "Mỹ phẩm",
                listOf(
                    CategoryItem(0.toString(), R.drawable.logo, "Mỹ phẩm nữ"),
                    CategoryItem(1.toString(), R.drawable.logo, "Mỹ phẩm nam")
                ), "https://d1j8r0kxyu9tj8.cloudfront.net/images/1562663273OyCyS9raIe4XXr1.jpg"
            )
        )

        return list
    }
}