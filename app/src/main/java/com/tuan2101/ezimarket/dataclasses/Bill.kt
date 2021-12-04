package com.tuan2101.ezimarket.dataclasses

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.tuan2101.ezimarket.BR

/**
 * Created by ndt2101 on 11/29/2021.
 */
class Bill(val billId: String,
           val shopId: String,
           val shopName: String,
           val userId: String,
           val listProduct: List<ProductInCart>,
           var totalPrice: Long,
           var paymentMethod: String,
           val shipToLocation: Location,
): BaseObservable() {

    @get:Bindable
    var shippingMethod: ShippingMethod?= null
        set(value) {
            field = value
            notifyPropertyChanged(BR.shippingMethod)
        }

    @get:Bindable
    var noteToSeller: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.noteToSeller)
        }

    var status: String = ""

}