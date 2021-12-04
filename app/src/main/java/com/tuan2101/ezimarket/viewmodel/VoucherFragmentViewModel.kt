package com.tuan2101.ezimarket.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.tuan2101.ezimarket.dataclasses.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by ndt2101 on 11/15/2021.
 */
class VoucherFragmentViewModel(val shop: ProductViaShopInCart): ViewModel() {
    var listVoucher = ArrayList<PostVoucher>()
    val shopName: String = shop.shopName
    init {
        listVoucher = fetchVoucher(shop.shopId)
        listVoucher.forEach {
            if (it.voucherId == shop.voucher?.voucherId) {
                it.voucherStatus = shop.voucher?.voucherStatus == true
            } else {
                it.voucherStatus = false
            }
        }
    }

    private fun fetchVoucher(shopId: String): ArrayList<PostVoucher> {
        return if (shopId == "") dummyData1() else dummyData()
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
            "https://i.pinimg.com/564x/86/33/ca/8633caf77d28fbd35cafb5ed37d7ad4b.jpg",
        )

        val voucher2 = PostVoucher(
            "v2",
            0.5,
            "Giảm 30% khi mua đơn hàng từ 20 triệu VND",
            date,
            5,
            false,
            "https://i.pinimg.com/564x/86/33/ca/8633caf77d28fbd35cafb5ed37d7ad4b.jpg",
        )

        val voucher3 = PostVoucher(
            "v3",
            0.1,
            "Giảm 30% khi mua đơn hàng từ 20 triệu VND",
            date,
            5,
            false,
            "https://i.pinimg.com/564x/86/33/ca/8633caf77d28fbd35cafb5ed37d7ad4b.jpg",
        )

        list.add(voucher1)
        list.add(voucher2)
        list.add(voucher3)

        return list
    }

    private fun dummyData1(): java.util.ArrayList<PostVoucher> {
        val list = ArrayList<PostVoucher>()

        val cal = Calendar.getInstance()
        cal[Calendar.YEAR] = 2021
        cal[Calendar.MONTH] = Calendar.NOVEMBER
        cal[Calendar.DAY_OF_MONTH] = 20
        val date = cal.time

        val voucher1 = PostVoucher(
            "v1",
            0.5,
            "Giảm 30% khi mua đơn hàng từ 20 triệu VND",
            date,
            100,
            false,
            "https://i.pinimg.com/564x/86/33/ca/8633caf77d28fbd35cafb5ed37d7ad4b.jpg",
        )
        val voucher2 = PostVoucher(
            "v2",
            0.1,
            "Giảm 30% khi mua đơn hàng từ 20 triệu VND",
            date,
            5,
            false,
            "https://i.pinimg.com/564x/86/33/ca/8633caf77d28fbd35cafb5ed37d7ad4b.jpg",
        )


        list.add(voucher1)
        list.add(voucher2)

        return list
    }
}