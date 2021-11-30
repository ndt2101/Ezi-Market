package com.tuan2101.ezimarket.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuan2101.ezimarket.dataclasses.*
import com.tuan2101.ezimarket.utils.notifyObserverInUI

/**
 * Created by ndt2101 on 11/5/2021.
 */
class CartFragmentViewModel() : ViewModel() {

    var listProductInCart = MutableLiveData<ArrayList<ProductViaShopInCart>>()
    val currentVisitedShopId = MutableLiveData<String>()
    var currentShopToGetVoucher = MutableLiveData<ProductViaShopInCart>()
    var shopPosition = MutableLiveData<Int>()
    val productPosition = MutableLiveData<Int>()
    val totalProductCount = MutableLiveData<Int>(0)
    val totalPrice = MutableLiveData<Long>(0L)
    val selectAllProduct = MutableLiveData<Boolean>(false)
    val navigateToMarketVoucherFragment = MutableLiveData(false)
    var totalProduct = MutableLiveData<Int>(0)

    var eziVoucher = MutableLiveData<Voucher>()
    val finalPrice = MutableLiveData<Long>(0)
    val navToPaymentFragment = MutableLiveData<Boolean>(false)
    val navToLocationFragment = MutableLiveData<Boolean>(false)
    var location: Location? =
        Location(
            "Nguyen Dinh Tuan",
            "0789266255",
            "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
            Ward("00626", "Phường Mỹ Đình 2"),
            District("019", "Quận Nam Từ Liêm"),
            Province("01", "Thành Phố Hà Nội", "Thành phố Trung ương")
        )

    companion object {
        var needUpdatingList = ArrayList<ProductViaShopInCart>()
    }

    init {
        listProductInCart.value = dummyDataForCart()
        totalPrice.value = 0L
        for (shop in listProductInCart.value!!) {
            totalProduct.value = totalProduct.value?.plus(shop.listProduct?.size ?: 0)
        }
//        location = null
    }

    fun clickAllProduct() {
        if (!selectAllProduct.value!!) { // chua click
            selectAllProduct.value = true // set co sang da click
            for (productViaShopInCart in listProductInCart.value!!) {
                if (!productViaShopInCart.status) { // via shop chua click
                    productViaShopInCart.status = true
                    for (product in productViaShopInCart.listProduct!!) {
                        if (!product.productStatus) { // product chua click
                            product.productStatus = true
                            productViaShopInCart.oldTotalPrice += product.productQuantity * product.newPrice
                            productViaShopInCart.currentSelectedProductCount ++
                        }
                    }
                    totalPrice.value =
                        totalPrice.value?.minus(productViaShopInCart.newTotalPrice)
                    applyVoucher(productViaShopInCart.voucher, productViaShopInCart)
                    totalPrice.value =
                        totalPrice.value?.plus(productViaShopInCart.newTotalPrice)
                }
            }
            totalProductCount.value = totalProduct.value
        } else { // truong hop da click chon tat ca
            selectAllProduct.value = false // set co sang chua click
            // dua tat ca ve chua click
            for (productViaShopInCart in listProductInCart.value!!) {
                productViaShopInCart.status = false
                for (product in productViaShopInCart.listProduct!!) {
                    product.productStatus = false
                }
                productViaShopInCart.currentSelectedProductCount = 0
                productViaShopInCart.newTotalPrice = 0
                productViaShopInCart.oldTotalPrice = 0
                productViaShopInCart.voucher = null
            }
            // dua het ve 0
            totalProductCount.value = 0
            totalPrice.value = 0
        }
    }

    fun clickAllProductViaShop(productViaShopInCart: ProductViaShopInCart) {
        val position = listProductInCart.value?.indexOf(productViaShopInCart)
        if (position != null) {
            if (productViaShopInCart.status) { // th shop da duoc click roi
                for (product in listProductInCart.value?.get(position)?.listProduct!!) {
                    product.productStatus = false
                }
                totalProductCount.value = totalProductCount.value?.minus(productViaShopInCart.currentSelectedProductCount)
                totalPrice.value = totalPrice.value?.minus(productViaShopInCart.newTotalPrice)
                productViaShopInCart.currentSelectedProductCount = 0
                productViaShopInCart.oldTotalPrice = 0
                productViaShopInCart.newTotalPrice = 0
                productViaShopInCart.status = false
                productViaShopInCart.voucher = null
            } else { // th shop chua duoc click
                for (product in listProductInCart.value?.get(position)?.listProduct!!) {
                    if (!product.productStatus) { // th product chua duoc click
                        product.productStatus = true
                        totalProductCount.value =
                            totalProductCount.value?.plus(1)
                        productViaShopInCart.oldTotalPrice += product.productQuantity * product.newPrice
                    }
                }
                applyVoucher(productViaShopInCart.voucher, productViaShopInCart)
                totalPrice.value = totalPrice.value?.plus(productViaShopInCart.newTotalPrice)
                productViaShopInCart.currentSelectedProductCount = productViaShopInCart.listProduct!!.size
                productViaShopInCart.status = true
            }
            selectAllProduct.value = totalProductCount.value == totalProduct.value
        }
    }

    fun clickVisitShop(shopId: String) {
        currentVisitedShopId.value = shopId
        Log.i("v", "shopId $shopId")
    }

    fun clickSelectVoucher(productViaShopInCart: ProductViaShopInCart) {
        currentShopToGetVoucher.value = productViaShopInCart
        Log.i("aaa", "voucher: shopId ${currentShopToGetVoucher.value!!.shopId}")
    }

    fun setVoucher(voucher: Voucher) {
        val position = listProductInCart.value?.indexOf(currentShopToGetVoucher.value)
        if (currentShopToGetVoucher.value?.voucher != null) {
            currentShopToGetVoucher.value?.voucher = null
        }
        if (position != -1 && currentShopToGetVoucher.value!!.oldTotalPrice >= voucher.priceCondition) {
            currentShopToGetVoucher.value!!.voucher = voucher
            voucher.voucherStatus = true
            Log.i("voucher1", "chay vao day1")
            totalPrice.value = totalPrice.value?.minus(currentShopToGetVoucher.value!!.newTotalPrice)
            applyVoucher(voucher, currentShopToGetVoucher.value!!)
            totalPrice.value = totalPrice.value?.plus(currentShopToGetVoucher.value!!.newTotalPrice)
            }
    }

    fun applyVoucher(voucher: Voucher?, productViaShopInCart: ProductViaShopInCart) {
        if (productViaShopInCart.voucher != null) {
            if (productViaShopInCart.oldTotalPrice >= voucher!!.priceCondition) {
                productViaShopInCart.apply {
                    newTotalPrice = (oldTotalPrice - oldTotalPrice * voucher.discount).toLong()
                    Log.i("voucher1", "chay vao day2")
                }
            } else {
                productViaShopInCart.apply {
                    newTotalPrice = oldTotalPrice
                    this.voucher?.voucherStatus = false
                    this.voucher = null
                }
            }
        } else {
            productViaShopInCart.apply {
                newTotalPrice = oldTotalPrice
                this.voucher?.voucherStatus = false
                this.voucher = null
            }
        }
    }


    fun clickToPay(productInCart: ProductInCart, productViaShopInCart: ProductViaShopInCart) {
        shopPosition.value = listProductInCart.value!!.indexOf(productViaShopInCart)
        if (shopPosition.value != -1) {
            productPosition.value =
                listProductInCart.value!![shopPosition.value!!].listProduct!!.indexOf(productInCart)
            if (productPosition.value != -1) {
                // th product chua duoc click
                if (!listProductInCart.value!![shopPosition.value!!].listProduct!![productPosition.value!!].productStatus) {
                    // tang bien count so luong
                    totalProductCount.value = totalProductCount.value?.plus(1)
                    // tinh gia goc cho shop
                    listProductInCart.value!![shopPosition.value!!].listProduct?.get(
                        productPosition.value!!
                    )?.apply {
                        listProductInCart.value!![shopPosition.value!!].oldTotalPrice += this.productQuantity * this.newPrice
                    }
                    // tang tong so tien
                    listProductInCart.value!![shopPosition.value!!].apply {
                        totalPrice.value = totalPrice.value?.minus(newTotalPrice)
                        applyVoucher(this.voucher, this)
                        totalPrice.value = totalPrice.value?.plus(this.newTotalPrice)
                    }
                    // set co la da click
                    listProductInCart.value?.get(shopPosition.value!!)?.apply {
                        this.listProduct?.get(productPosition.value!!)?.productStatus = true
                        this.currentSelectedProductCount =
                            this.currentSelectedProductCount.plus(1)
                    }
                } else { // th bo click
                    // gan lai la chua duoc click
                    listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.get(productPosition.value!!)?.productStatus = false

                    // tru bien dem tong so sp
                    totalProductCount.value = totalProductCount.value?.minus(1)

                    // tru tong so tien di gia cua shop de tinh lai theo voucher
                    listProductInCart.value!![shopPosition.value!!].apply {
                        totalPrice.value = totalPrice.value?.minus(this.newTotalPrice)
                    }

                    // tinh gia goc cho shop, apply voucher, cong lai tong so tien
                    listProductInCart.value!![shopPosition.value!!].apply {
                        this.listProduct?.get(productPosition.value!!)?.apply {
                            oldTotalPrice -= this.productQuantity * this.newPrice
                        }
                        applyVoucher(voucher, this)
                        totalPrice.value = totalPrice.value?.plus(newTotalPrice)
                    }

                    // tru di tong so san pham dang duoc chon theo shop
                    listProductInCart.value?.get(shopPosition.value!!)?.currentSelectedProductCount =
                        listProductInCart.value?.get(shopPosition.value!!)?.currentSelectedProductCount?.minus(1)!!
                }

                // check chon tat ca theo shop
                listProductInCart.value?.get(shopPosition.value!!)!!.status = listProductInCart.value?.get(shopPosition.value!!)?.currentSelectedProductCount ==
                        listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.size

                // check chon tat ca san pham
                selectAllProduct.value = totalProductCount.value == totalProduct.value
            }
        }
    }

    fun clickToVisitProductDetail(productInCart: ProductInCart) {
        Log.i("aaa", "product detail ${productInCart.shop!!.id} .. ${productInCart.id}")
    }

    fun clickToBuyMore(productInCart: ProductInCart, productViaShopInCart: ProductViaShopInCart) {
        shopPosition.value = listProductInCart.value!!.indexOf(productViaShopInCart)
        if (shopPosition.value != -1) {
            productPosition.value =
                listProductInCart.value!![shopPosition.value!!].listProduct!!.indexOf(
                    productInCart
                )
            if (productPosition.value != -1) {
                // th product da duoc click
                if (listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.get(
                        productPosition.value!!
                    )!!.productStatus
                ) {

                    // cong vao tong so tien gia cua san pham
                    totalPrice.value = totalPrice.value!!.minus(productViaShopInCart.newTotalPrice)
                    productViaShopInCart.oldTotalPrice += productInCart.newPrice
                    applyVoucher(productViaShopInCart.voucher, productViaShopInCart)
                    totalPrice.value = totalPrice.value!!.plus(productViaShopInCart.newTotalPrice)

                }
                // tang so luong mua san pham len 1
                listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.get(
                    productPosition.value!!
                )!!.productQuantity += 1
            }
        }
    }

    fun clickToBuyLess(productInCart: ProductInCart, productViaShopInCart: ProductViaShopInCart) {
        shopPosition.value = listProductInCart.value!!.indexOf(productViaShopInCart)
        if (shopPosition.value != -1) {
            productPosition.value =
                listProductInCart.value!![shopPosition.value!!].listProduct!!.indexOf(
                    productInCart
                )
            if (productPosition.value != -1) {
                // tru so luong mua cua san pham di 1
                productInCart.productQuantity -= 1

                // th dang duoc click
                if (productInCart.productStatus) {
                    // cap nhat lai gia tong
                    totalPrice.value = totalPrice.value!!.minus(productViaShopInCart.newTotalPrice)
                    productViaShopInCart.oldTotalPrice -= productInCart.newPrice
                    applyVoucher(productViaShopInCart.voucher, productViaShopInCart)
                    totalPrice.value = totalPrice.value!!.plus(productViaShopInCart.newTotalPrice)
                }
                // neu so luong mua cua san pham = 0 thi xoa san pham
                if (productInCart.productQuantity == 0L) {
                    // th dang chon
                    if (productInCart.productStatus) {
                        totalProductCount.value = totalProductCount.value?.minus(1)
                        productViaShopInCart.currentSelectedProductCount -= 1
                    }
                    totalProduct.value = totalProduct.value?.minus(1)
                    listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.removeAt(
                        productPosition.value!!
                    )

                    // check chon tat ca theo shop
                    productViaShopInCart.status = productViaShopInCart.currentSelectedProductCount ==
                            productViaShopInCart.listProduct?.size

                    // neu so luong san pham = 0 thi xoa shop
                    if (productViaShopInCart.listProduct?.size == 0) {
                        listProductInCart.value?.removeAt(shopPosition.value!!)
                    }
                    listProductInCart.notifyObserverInUI()
                    // check chon tat ca san phan
                    selectAllProduct.value = totalProductCount.value == totalProduct.value
                }
            }
        }
    }

    fun clickToDeleteProduct(
        productInCart: ProductInCart,
        productViaShopInCart: ProductViaShopInCart
    ) {
        shopPosition.value = listProductInCart.value!!.indexOf(productViaShopInCart)
        if (shopPosition.value != -1) {
            productPosition.value =
                listProductInCart.value!![shopPosition.value!!].listProduct!!.indexOf(
                    productInCart
                )
            if (productPosition.value != -1) {
                // trong truong hop dang click se update lai tong so sp va tong so tien
                if (productInCart.productStatus) {
                    totalProductCount.value = totalProductCount.value?.minus(1)
                    productViaShopInCart.currentSelectedProductCount -= 1

                    totalPrice.value = totalPrice.value!!.minus(productViaShopInCart.newTotalPrice)
                    productViaShopInCart.oldTotalPrice -= productInCart.newPrice * productInCart.productQuantity
                    applyVoucher(productViaShopInCart.voucher, productViaShopInCart)
                    totalPrice.value = totalPrice.value!!.plus(productViaShopInCart.newTotalPrice)
                }
                totalProduct.value = totalProduct.value?.minus(1)
                listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.removeAt(productPosition.value!!)

                // check chon tat ca theo shop
                productViaShopInCart.status = productViaShopInCart.currentSelectedProductCount ==
                        productViaShopInCart.listProduct?.size
                // xoa shop neu so san pham = 0
                if (productViaShopInCart.listProduct?.size == 0) {
                    listProductInCart.value?.removeAt(shopPosition.value!!)
                }
                listProductInCart.notifyObserverInUI()

                // check chon tat ca san pham
                selectAllProduct.value = totalProductCount.value == totalProduct.value
            }
        }
    }

    fun deleteAll() {
        if (listProductInCart.value?.size != 0 && listProductInCart.value != null) {
            listProductInCart.value?.removeAll(listProductInCart.value!!)
            totalProductCount.value = 0
            totalPrice.value = 0
            totalProduct.value = 0
            selectAllProduct.value = false
            needUpdatingList.removeAll(needUpdatingList.toSet())
            listProductInCart.notifyObserverInUI()
        }
    }

    fun onNavigateToMarketVoucherFragment() {
        navigateToMarketVoucherFragment.value = true
    }

    fun setMarketVoucher(voucher: Voucher) {
//        if (eziVoucher.value != null) {
//            eziVoucher.value = null
//        }
        if (totalPrice.value!! >= voucher.priceCondition){
            eziVoucher.value = voucher
            voucher.voucherStatus = true
        }
    }

    fun applyMarkerVoucher() {
        if (eziVoucher.value != null) {
            if (totalPrice.value!! >= eziVoucher.value?.priceCondition!!) {
                finalPrice.value = (totalPrice.value!! - totalPrice.value!! * eziVoucher.value?.discount!!).toLong()
            } else {
                finalPrice.value = totalPrice.value
                eziVoucher.value!!.voucherStatus = false
                eziVoucher.value = null
            }
        } else {
            finalPrice.value = totalPrice.value
        }
    }

    fun purchase() {
        listProductInCart.value?.forEach { productViaShopInCart ->
            val listProductClone = ArrayList<ProductInCart>()
            productViaShopInCart.listProduct?.forEach { product ->
                if (product.productStatus) {
                    listProductClone.add(product)
                }
            }
            if (listProductClone.size != 0){
                val productViaShopInCartClone =
                    ProductViaShopInCart(
                        productViaShopInCart.shopId,
                        productViaShopInCart.shopName,
                        productViaShopInCart.status,
                        productViaShopInCart.oldTotalPrice,
                        productViaShopInCart.newTotalPrice,
                        listProductClone.size,
                        productViaShopInCart.voucher,
                        listProductClone
                    )
                needUpdatingList.add(productViaShopInCartClone)
            }
        }
        navToPaymentFragment.value = true
    }

    fun onNavToLocationFragment() {
        navToLocationFragment.value = true
    }

    fun dummyDataForCart(): ArrayList<ProductViaShopInCart> {
        val list = ArrayList<ProductViaShopInCart>()
        val listProduct = MutableLiveData<ArrayList<ProductInCart>>()
        val listProductInCart = ArrayList<ProductInCart>()
        val productInCart = ProductInCart(
            "p0",
            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/248384203_1067157620782867_6617827598330014721_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=VavUaKnuwFYAX9aToDm&_nc_ht=scontent.fhph1-1.fna&oh=92e07c5c07854ba1c60973f97c5e0ae3&oe=61A530BC",
            "Thùy Dương product",
            25000,
            1,
            5.0f,
            2,
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
                )
            ),
            false
        )

        val productInCart2 = ProductInCart(
            "p1",
            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/248384203_1067157620782867_6617827598330014721_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=VavUaKnuwFYAX9aToDm&_nc_ht=scontent.fhph1-1.fna&oh=92e07c5c07854ba1c60973f97c5e0ae3&oe=61A530BC",
            "Thùy Dương product",
            25000,
            2,
            5.0f,
            2,
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
                )
            ),
            false
        )

        val productInCart3 = ProductInCart(
            "p0",
            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/248384203_1067157620782867_6617827598330014721_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=VavUaKnuwFYAX9aToDm&_nc_ht=scontent.fhph1-1.fna&oh=92e07c5c07854ba1c60973f97c5e0ae3&oe=61A530BC",
            "Thùy Dương product",
            25000,
            3,
            5.0f,
            2,
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
                )
            ),
            false
        )

        val productInCart4 = ProductInCart(
            "p1",
            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/248384203_1067157620782867_6617827598330014721_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=VavUaKnuwFYAX9aToDm&_nc_ht=scontent.fhph1-1.fna&oh=92e07c5c07854ba1c60973f97c5e0ae3&oe=61A530BC",
            "Thùy Dương product",
            25000,
            4,
            5.0f,
            2,
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
                )
            ),
            false
        )

        val productInCart5 = ProductInCart(
            "p0",
            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/248384203_1067157620782867_6617827598330014721_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=VavUaKnuwFYAX9aToDm&_nc_ht=scontent.fhph1-1.fna&oh=92e07c5c07854ba1c60973f97c5e0ae3&oe=61A530BC",
            "Thùy Dương product",
            25000,
            5,
            5.0f,
            2,
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
                )
            ),
            false
        )

        val productInCart6 = ProductInCart(
            "p1",
            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/248384203_1067157620782867_6617827598330014721_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=VavUaKnuwFYAX9aToDm&_nc_ht=scontent.fhph1-1.fna&oh=92e07c5c07854ba1c60973f97c5e0ae3&oe=61A530BC",
            "Thùy Dương product",
            25000,
            6,
            5.0f,
            2,
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
                )
            ),
            false
        )

        val productInCart7 = ProductInCart(
            "p0",
            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/248384203_1067157620782867_6617827598330014721_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=VavUaKnuwFYAX9aToDm&_nc_ht=scontent.fhph1-1.fna&oh=92e07c5c07854ba1c60973f97c5e0ae3&oe=61A530BC",
            "Thùy Dương product",
            25000,
            7,
            5.0f,
            2,
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
                )
            ),
            false
        )

        val productInCart8 = ProductInCart(
            "p1",
            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/248384203_1067157620782867_6617827598330014721_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=VavUaKnuwFYAX9aToDm&_nc_ht=scontent.fhph1-1.fna&oh=92e07c5c07854ba1c60973f97c5e0ae3&oe=61A530BC",
            "Thùy Dương product",
            25000,
            8,
            5.0f,
            2,
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
                )
            ),
            false
        )

        val productInCart9 = ProductInCart(
            "p0",
            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/248384203_1067157620782867_6617827598330014721_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=VavUaKnuwFYAX9aToDm&_nc_ht=scontent.fhph1-1.fna&oh=92e07c5c07854ba1c60973f97c5e0ae3&oe=61A530BC",
            "Thùy Dương product",
            25000,
            9,
            5.0f,
            2,
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
                )
            ),
            false
        )

        val productInCart10 = ProductInCart(
            "p1",
            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/248384203_1067157620782867_6617827598330014721_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=VavUaKnuwFYAX9aToDm&_nc_ht=scontent.fhph1-1.fna&oh=92e07c5c07854ba1c60973f97c5e0ae3&oe=61A530BC",
            "Thùy Dương product",
            25000,
            10,
            5.0f,
            2,
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
                )
            ),
            false
        )
        val listProduct1 = MutableLiveData<ArrayList<ProductInCart>>()
        val listProduct2 = MutableLiveData<ArrayList<ProductInCart>>()
        val listProduct3 = MutableLiveData<ArrayList<ProductInCart>>()
        val listProduct4 = MutableLiveData<ArrayList<ProductInCart>>()
        val listProductInCart2 = ArrayList<ProductInCart>()
        val listProductInCart3 = ArrayList<ProductInCart>()
        val listProductInCart4 = ArrayList<ProductInCart>()
        val listProductInCart5 = ArrayList<ProductInCart>()

        listProductInCart.add(productInCart)
        listProductInCart.add(productInCart2)

        listProductInCart2.add(productInCart3)
        listProductInCart2.add(productInCart4)

        listProductInCart3.add(productInCart5)
        listProductInCart3.add(productInCart6)

        listProductInCart4.add(productInCart7)
        listProductInCart4.add(productInCart8)

        listProductInCart5.add(productInCart9)
        listProductInCart5.add(productInCart10)

        listProduct.value = listProductInCart
        listProduct1.value = listProductInCart2
        listProduct2.value = listProductInCart3

        listProduct3.value = listProductInCart4
        listProduct4.value = listProductInCart5

        val item = ProductViaShopInCart(
            "1",
            "Shop",
            false,
            0L, 0L, 0, null,
            listProductInCart
        )

        val item2 = ProductViaShopInCart(
            "2",
            "Dummy",
            false,
            0L, 0L, 0, null,
            listProductInCart2
        )

        val item3 = ProductViaShopInCart(
            "3",
            "Dummy Shop",
            false,
            0L, 0L, 0, null,
            listProductInCart3
        )

        val item4 = ProductViaShopInCart(
            "4",
            "a",
            false,
            0L, 0L, 0, null,
            listProductInCart4
        )

        val item5 = ProductViaShopInCart(
            "5",
            "b",
            false,
            0L, 0L, 0, null,
            listProductInCart5
        )
        list.add(item)
        list.add(item2)
        list.add(item3)
        list.add(item4)
        list.add(item5)
        return list
    }
}
