package com.tuan2101.ezimarket.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuan2101.ezimarket.dataclasses.PostVoucher
import com.tuan2101.ezimarket.dataclasses.Voucher
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by ndt2101 on 1/26/2022.
 */
class CouponFragmentViewModel(): ViewModel() {
    val isLoading = MutableLiveData(false)
    val selectedVoucher = MutableLiveData<String>("")
    var listVoucher = ArrayList<PostVoucher>()

    init {
        listVoucher = dummyData()
    }

    fun onClickVoucher(voucher: Voucher) {
        selectedVoucher.value = voucher.voucherId
    }

    private fun dummyData(): java.util.ArrayList<PostVoucher> {
        val list = ArrayList<PostVoucher>()

        val cal = Calendar.getInstance()
        cal[Calendar.YEAR] = 2021
        cal[Calendar.MONTH] = Calendar.NOVEMBER
        cal[Calendar.DAY_OF_MONTH] = 20
        val date = cal.time

        val voucher1 = PostVoucher(
            "v1",
            0.1,
            "Giảm 30% khi mua đơn hàng từ 20 triệu VND",
            date,
            5,
            false,
            "https://i.pinimg.com/564x/26/ab/79/26ab7951865d85e9077ef173aac36583.jpg",
        )

        val voucher2 = PostVoucher(
            "v2",
            0.5,
            "Giảm 30% khi mua đơn hàng từ 20 triệu VND",
            date,
            5,
            false,
            "https://i.pinimg.com/564x/26/ab/79/26ab7951865d85e9077ef173aac36583.jpg",
        )

        val voucher3 = PostVoucher(
            "v3",
            0.1,
            "Giảm 30% khi mua đơn hàng từ 20 triệu VND",
            date,
            5,
            false,
            "https://i.pinimg.com/564x/26/ab/79/26ab7951865d85e9077ef173aac36583.jpg",
        )

        list.add(voucher1)
        list.add(voucher2)
        list.add(voucher3)
        return list
    }
}