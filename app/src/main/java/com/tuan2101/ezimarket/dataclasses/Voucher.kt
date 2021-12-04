package com.tuan2101.ezimarket.dataclasses

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.tuan2101.ezimarket.BR
import java.util.*

/**
 * Created by ndt2101 on 10/31/2021.
 */
open class Voucher() : BaseObservable() {

    constructor(
        voucherId: String,
//        voucherShop: Shop?,
        discount: Double,
        voucherDescription: String,
        exp: Date?,
        img: String
//        priceCondition: Long,
//        quantity: Int,
//        voucherStatus: Boolean
    ) : this() {
        this.voucherId = voucherId
//        this.voucherShop = voucherShop
        this.discount = discount
        this.voucherDescription = voucherDescription
        this.exp = exp
        this.imgVoucher = img
//        this.priceCondition = priceCondition
//        this.quantity = quantity
//        this.voucherStatus = voucherStatus
    }

    @get:Bindable
    var voucherId: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.voucherId)
        }

//    @get:Bindable
//    var voucherShop: Shop? = null
//        set(value) {
//            field = value
//            notifyPropertyChanged(BR.voucherShop)
//        }

    @get:Bindable
    var discount: Double = 0.0
        set(value) {
            field = value
            notifyPropertyChanged(BR.discount)
        }

    @get:Bindable
    var voucherDescription: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.voucherDescription)
        }

    @get:Bindable
    var exp: Date? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.exp)
        }

    @get:Bindable
    var imgVoucher: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.imgVoucher)
        }

//    @get:Bindable
//    var priceCondition: Long = 0
//        set(value) {
//            field = value
//            notifyPropertyChanged(BR.priceCondition)
//        }
//
//    @get:Bindable
//    var quantity: Int = 0
//        set(value) {
//            field = value
//            notifyPropertyChanged(BR.quantity)
//        }
//
//    @get:Bindable
//    var voucherStatus: Boolean = false
//        set(value) {
//            field = value
//            notifyPropertyChanged(BR.voucherStatus)
//        }
//
//    @get:Bindable
//    var voucherStatusText: String = ""
//        set(value) {
//            field = value
//            notifyPropertyChanged(BR.voucherStatusText)
//        }
//        get() {
//            notifyPropertyChanged(BR.voucherStatusText)
//            return if (voucherStatus) "Đã chọn" else "Chọn"
//        }

//    override fun equals(other: Any?): Boolean {
//        return other is Voucher && this.voucherStatus == other.voucherStatus
//    }
//
//    override fun hashCode(): Int {
//        return voucherStatus.hashCode()
//    }
}

class ShopVoucher(
    voucherId: String,
    discount: Double,
    voucherDescription: String,
    exp: Date?,
    quantity: Int,
    priceCondition: Long,
    img: String
): Voucher(voucherId, discount, voucherDescription, exp, img) {

    @get:Bindable
    var priceCondition: Long = priceCondition
        set(value) {
            field = value
            notifyPropertyChanged(BR.priceCondition)
        }

    @get:Bindable
    var quantity: Int = quantity
        set(value) {
            field = value
            notifyPropertyChanged(BR.quantity)
        }
}

class PostVoucher(
    voucherId: String,
    discount: Double,
    voucherDescription: String,
    exp: Date?,
    priceCondition: Long,
    voucherStatus: Boolean,
    img: String
): Voucher(voucherId, discount, voucherDescription, exp, img) {
    @get:Bindable
    var priceCondition: Long = priceCondition
        set(value) {
            field = value
            notifyPropertyChanged(BR.priceCondition)
        }

    @get:Bindable
    var voucherStatus: Boolean = voucherStatus
        set(value) {
            field = value
            notifyPropertyChanged(BR.voucherStatus)
        }

    @get:Bindable
    var voucherStatusText: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.voucherStatusText)
        }
        get() {
            notifyPropertyChanged(BR.voucherStatusText)
            return if (voucherStatus) "Đã chọn" else "Chọn"
        }

    override fun equals(other: Any?): Boolean {
        return other is PostVoucher && this.voucherStatus == other.voucherStatus
    }

    override fun hashCode(): Int {
        return voucherStatus.hashCode()
    }
}