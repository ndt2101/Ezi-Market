package com.tuan2101.ezimarket.dataclasses

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.tuan2101.ezimarket.BR
import java.util.*

/**
 * Created by ndt2101 on 11/29/2021.
 */
class ShippingMethod(val id: String, val name: String, val price: Long, val receivedDay: Date): BaseObservable() {

    @get:Bindable
    var shippingMethodStatus: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.shippingMethodStatus)
        }
}
//class TransportMethod(id: String, name: String, price: Long, receivedDay: Date, override var status: Boolean) :
//    ShippingMethod(id, name, price, receivedDay)
