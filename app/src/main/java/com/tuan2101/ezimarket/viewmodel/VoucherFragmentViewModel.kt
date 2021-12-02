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
    var listVoucher = ArrayList<Voucher>()
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

    private fun fetchVoucher(shopId: String): ArrayList<Voucher> {
        return if (shopId == "") dummyData1() else dummyData()
    }

    private fun dummyData(): java.util.ArrayList<Voucher> {
        val list = ArrayList<Voucher>()

        val cal = Calendar.getInstance()
        cal[Calendar.YEAR] = 2021
        cal[Calendar.MONTH] = Calendar.NOVEMBER
        cal[Calendar.DAY_OF_MONTH] = 20
        val date = cal.time

        val voucher1 = Voucher(
            "v1",
            Shop(
                "u1",
                "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                "Thùy Dương",
                "shop",
                null,
                null,
                Location(
                    "Nguyen Dinh Tuan",
                    "0789266255",
                    "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                    Ward("-1", "Mỹ Đình 2"),
                    District("-1", "Nam Từ Liêm"),
                    Province("-1", "Hà Nội", "Thành phố")
                ),"Ad5DmFc53BAxVxr1f3_sQSz9_SiEqmlCRSkQ2BHuk0WDyhweFoxQ9hCi3TNxcrIsTdJgiBvJck1_lGTu",
            ),
            0.1,
            "Giảm 30% khi mua đơn hàng từ 20 triệu VND",
            date,
            5,
            10,
            false
        )

        val voucher2 = Voucher(
            "v2",
            Shop(
                "u1",
                "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                "Thùy Dương",
                "shop",
                null,
                null,
                Location(
                    "Nguyen Dinh Tuan",
                    "0789266255",
                    "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                    Ward("-1", "Mỹ Đình 2"),
                    District("-1", "Nam Từ Liêm"),
                    Province("-1", "Hà Nội", "Thành phố")
                ),"Ad5DmFc53BAxVxr1f3_sQSz9_SiEqmlCRSkQ2BHuk0WDyhweFoxQ9hCi3TNxcrIsTdJgiBvJck1_lGTu",
            ),
            0.5,
            "Giảm 30% khi mua đơn hàng từ 20 triệu VND",
            date,
            5,
            10,
            false
        )

        val voucher3 = Voucher(
            "v3",
            Shop(
                "u1",
                "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                "Thùy Dương",
                "shop",
                null,
                null,
                Location(
                    "Nguyen Dinh Tuan",
                    "0789266255",
                    "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                    Ward("-1", "Mỹ Đình 2"),
                    District("-1", "Nam Từ Liêm"),
                    Province("-1", "Hà Nội", "Thành phố")
                ),"Ad5DmFc53BAxVxr1f3_sQSz9_SiEqmlCRSkQ2BHuk0WDyhweFoxQ9hCi3TNxcrIsTdJgiBvJck1_lGTu",
            ),
            0.1,
            "Giảm 30% khi mua đơn hàng từ 20 triệu VND",
            date,
            5,
            10,
            false
        )

        list.add(voucher1)
        list.add(voucher2)
        list.add(voucher3)

        return list
    }

    private fun dummyData1(): java.util.ArrayList<Voucher> {
        val list = ArrayList<Voucher>()

        val cal = Calendar.getInstance()
        cal[Calendar.YEAR] = 2021
        cal[Calendar.MONTH] = Calendar.NOVEMBER
        cal[Calendar.DAY_OF_MONTH] = 20
        val date = cal.time

        val voucher1 = Voucher(
            "v1",
            Shop(
                "u1",
                "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                "Thùy Dương",
                "shop",
                null,
                null,
                Location(
                    "Nguyen Dinh Tuan",
                    "0789266255",
                    "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                    Ward("-1", "Mỹ Đình 2"),
                    District("-1", "Nam Từ Liêm"),
                    Province("-1", "Hà Nội", "Thành phố")
                ),"Ad5DmFc53BAxVxr1f3_sQSz9_SiEqmlCRSkQ2BHuk0WDyhweFoxQ9hCi3TNxcrIsTdJgiBvJck1_lGTu",
            ),
            0.5,
            "Giảm 30% khi mua đơn hàng từ 20 triệu VND",
            date,
            100,
            10,
            false
        )
        val voucher2 = Voucher(
            "v2",
            Shop(
                "u1",
                "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                "Thùy Dương",
                "shop",
                null,
                null,
                Location(
                    "Nguyen Dinh Tuan",
                    "0789266255",
                    "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                    Ward("-1", "Mỹ Đình 2"),
                    District("-1", "Nam Từ Liêm"),
                    Province("-1", "Hà Nội", "Thành phố")
                ),"Ad5DmFc53BAxVxr1f3_sQSz9_SiEqmlCRSkQ2BHuk0WDyhweFoxQ9hCi3TNxcrIsTdJgiBvJck1_lGTu",
            ),
            0.1,
            "Giảm 30% khi mua đơn hàng từ 20 triệu VND",
            date,
            5,
            10,
            false
        )


        list.add(voucher1)
        list.add(voucher2)

        return list
    }
}