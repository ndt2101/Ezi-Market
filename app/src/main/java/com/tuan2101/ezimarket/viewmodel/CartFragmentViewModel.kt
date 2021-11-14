package com.tuan2101.ezimarket.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuan2101.ezimarket.dataclasses.Location
import com.tuan2101.ezimarket.dataclasses.ProductInCart
import com.tuan2101.ezimarket.dataclasses.ProductViaShopInCart
import com.tuan2101.ezimarket.dataclasses.Shop
import com.tuan2101.ezimarket.utils.notifyObserverInUI

/**
 * Created by ndt2101 on 11/5/2021.
 */
class CartFragmentViewModel() : ViewModel() {

    var listProductInCart = MutableLiveData<ArrayList<ProductViaShopInCart>>()
    val currentVisitedShopId = MutableLiveData<String>()
    var currentSelectedShopIdToGetVoucher = MutableLiveData<String>()
    var shopPosition = MutableLiveData<Int>()
    val productPosition = MutableLiveData<Int>()
    val totalProductCount = MutableLiveData<Int>(0)
    val totalPrice = MutableLiveData<Long>(0L)
    val selectAllProduct = MutableLiveData<Boolean>(false)
    val navigateToMarketVoucherFragment = MutableLiveData(false)

    init {
        listProductInCart.value = dummyDataForCart()
        totalPrice.value = 0L
    }

    // TODO: ti sua lai
    fun clickAllProduct() {
        if (!selectAllProduct.value!!) { // chua click
            selectAllProduct.value = true // set co sang da click
            for (productViaShopInCart in listProductInCart.value!!) {
                if (!productViaShopInCart.status) { // via shop chua click
                    productViaShopInCart.status = true
                    for (product in productViaShopInCart.listProduct?.value!!) {
                        if (!product.productStatus) { // product chua click
                            product.productStatus = true
                            totalProductCount.value =
                                totalProductCount.value?.plus(product.productQuantity.toInt())
                            totalPrice.value =
                                totalPrice.value?.plus(product.productQuantity * product.newPrice)
                        }
                    }
                }
            }
        } else { // truong hop da click chon tat ca
            selectAllProduct.value = false // set co sang chua click
            // dua tat ca ve chua click
            for (productViaShopInCart in listProductInCart.value!!) {
                productViaShopInCart.status = false
                for (product in productViaShopInCart.listProduct?.value!!) {
                    product.productStatus = false
                }
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
                for (product in listProductInCart.value?.get(position)?.listProduct?.value!!) {
                    if (product.productStatus) { // th product da duoc click roi
                        product.productStatus = false
                        totalProductCount.value =
                            totalProductCount.value?.minus((product.productQuantity).toInt())
                        totalPrice.value =
                            totalPrice.value?.minus(product.productQuantity * product.newPrice)
                    }
                }
                productViaShopInCart.status = false
            } else { // th shop chua duoc click
                for (product in listProductInCart.value?.get(position)?.listProduct?.value!!) {
                    if (!product.productStatus) { // th product chua duoc click
                        product.productStatus = true
                        totalProductCount.value =
                            totalProductCount.value?.plus((product.productQuantity).toInt())
                        totalPrice.value =
                            totalPrice.value?.plus(product.productQuantity * product.newPrice)
                    }
                }
                productViaShopInCart.status = true
            }
        }
    }

    fun clickVisitShop(shopId: String) {
        currentVisitedShopId.value = shopId
        Log.i("v", "shopId $shopId")
    }

    fun clickSelectVoucher(shopId: String) {
        currentSelectedShopIdToGetVoucher.value = shopId
        Log.i("aaa", "voucher: shopId $shopId")
    }

    fun clickToPay(productInCart: ProductInCart, productViaShopInCart: ProductViaShopInCart) {
        shopPosition.value = listProductInCart.value!!.indexOf(productViaShopInCart)
        if (shopPosition.value != -1) {
            productPosition.value =
                listProductInCart.value!![shopPosition.value!!].listProduct!!.value!!.indexOf(productInCart)
            if (productPosition.value != -1) {
                // th product chua duoc click
                if (!listProductInCart.value!![shopPosition.value!!].listProduct!!.value!![productPosition.value!!].productStatus) {
                    // tang bien count so luong
                    totalProductCount.value = totalProductCount.value?.plus(
                        (listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.value?.get(
                            productPosition.value!!
                        )?.productQuantity)?.toInt()!!
                    )
                    // tang tong so tien
                    totalPrice.value = totalPrice.value?.plus(
                        (listProductInCart.value!![shopPosition.value!!].listProduct?.value?.get(
                            productPosition.value!!
                        )!!.productQuantity * listProductInCart.value!![shopPosition.value!!].listProduct?.value?.get(
                            productPosition.value!!
                        )?.newPrice!!)
                    )
                    // set co la da click
                    listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.value?.get(
                        productPosition.value!!
                    )?.productStatus = true
                } else { // th bo click
                    // gan lai la chua duoc click
                    listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.value?.get(
                        productPosition.value!!
                    )?.productStatus = false
                    // tru bien dem tong so sp
                    totalProductCount.value = totalProductCount.value?.minus(
                        (listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.value?.get(
                            productPosition.value!!
                        )?.productQuantity)?.toInt()!!
                    )

                    // tru tong so tien
                    totalPrice.value = totalPrice.value?.minus(
                        (listProductInCart.value!![shopPosition.value!!].listProduct?.value?.get(
                            productPosition.value!!
                        )!!.productQuantity * listProductInCart.value!![shopPosition.value!!].listProduct?.value?.get(
                            productPosition.value!!
                        )?.newPrice!!)
                    )

                }

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
                listProductInCart.value!![shopPosition.value!!].listProduct!!.value!!.indexOf(
                    productInCart
                )
            if (productPosition.value != -1) {
                // th peoduct da duoc click
                if (listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.value?.get(
                        productPosition.value!!
                    )!!.productStatus
                ) {
                    // tang bien count len 1
                    totalProductCount.value = totalProductCount.value!!.plus(1)
                    // cong vao tong so tien gia cua san pham
                    totalPrice.value = totalPrice.value!!.plus(
                        listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.value?.get(
                            productPosition.value!!
                        )!!.newPrice
                    )
                }
                // tang so luong mua san pham len 1
                listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.value?.get(
                    productPosition.value!!
                )!!.productQuantity += 1
            }
        }
    }

    fun clickToBuyLess(productInCart: ProductInCart, productViaShopInCart: ProductViaShopInCart) {
        shopPosition.value = listProductInCart.value!!.indexOf(productViaShopInCart)
        if (shopPosition.value != -1) {
            productPosition.value =
                listProductInCart.value!![shopPosition.value!!].listProduct!!.value!!.indexOf(
                    productInCart
                )
            if (productPosition.value != -1) {
                // tru so luong mua cua san pham di 1
                listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.value?.get(
                    productPosition.value!!
                )!!.productQuantity -= 1

                // th dang duoc click
                if (listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.value?.get(
                        productPosition.value!!
                    )!!.productStatus
                ) {
                    // tru tong so san phan di 1
                    totalProductCount.value = totalProductCount.value?.minus(1)
                    // tru so tien phai tra di luong gia cua sp nay
                    totalPrice.value = totalPrice.value!!.minus(
                        listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.value?.get(
                            productPosition.value!!
                        )!!.newPrice
                    )
                }
                // neu so luong mua cua san pham = 0 thi xoa san pham
                if (listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.value?.get(
                        productPosition.value!!
                    )!!.productQuantity == 0L
                ) {
                    listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.value?.removeAt(
                        productPosition.value!!
                    )
                    // neu so luong san pham = 0 thi xoa shop
                    if (listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.value?.size == 0) {
                        listProductInCart.value?.removeAt(shopPosition.value!!)
                    }
                    listProductInCart.notifyObserverInUI()
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
                listProductInCart.value!![shopPosition.value!!].listProduct!!.value!!.indexOf(
                    productInCart
                )
            if (productPosition.value != -1) {
                // trong truong hop dang click se update lai tong so sp va tong so tien
                if (listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.value?.get(productPosition.value!!)!!.productStatus) {
                    totalProductCount.value = totalProductCount.value?.minus(
                        listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.value?.get(productPosition.value!!)!!.productQuantity.toInt()
                    )
                    totalPrice.value = totalPrice.value!!.minus(
                        listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.value?.get(
                            productPosition.value!!
                        )!!.newPrice * listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.value?.get(
                            productPosition.value!!
                        )!!.productQuantity
                    )
                }
                listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.value?.removeAt(
                    productPosition.value!!
                )
                if (listProductInCart.value?.get(shopPosition.value!!)?.listProduct?.value?.size == 0) {
                    listProductInCart.value?.removeAt(shopPosition.value!!)
                }
                listProductInCart.notifyObserverInUI()
            }
        }
    }

    fun deleteAll() {
        if (listProductInCart.value?.size != 0 && listProductInCart.value != null) {
            listProductInCart.value?.removeAll(listProductInCart.value!!)
            totalProductCount.value = 0
            totalPrice.value = 0
            selectAllProduct.value = false
            listProductInCart.notifyObserverInUI()
        }
    }

    fun onNavigateToMarketVoucherFragment() {
        navigateToMarketVoucherFragment.value = true
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
                    "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                    "Mỹ Đình 2",
                    "Nam Từ Liêm",
                    "Hà Nội"
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
                    "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                    "Mỹ Đình 2",
                    "Nam Từ Liêm",
                    "Hà Nội"
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
                    "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                    "Mỹ Đình 2",
                    "Nam Từ Liêm",
                    "Hà Nội"
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
                    "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                    "Mỹ Đình 2",
                    "Nam Từ Liêm",
                    "Hà Nội"
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
                    "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                    "Mỹ Đình 2",
                    "Nam Từ Liêm",
                    "Hà Nội"
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
                    "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                    "Mỹ Đình 2",
                    "Nam Từ Liêm",
                    "Hà Nội"
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
                    "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                    "Mỹ Đình 2",
                    "Nam Từ Liêm",
                    "Hà Nội"
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
                    "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                    "Mỹ Đình 2",
                    "Nam Từ Liêm",
                    "Hà Nội"
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
                    "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                    "Mỹ Đình 2",
                    "Nam Từ Liêm",
                    "Hà Nội"
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
                    "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                    "Mỹ Đình 2",
                    "Nam Từ Liêm",
                    "Hà Nội"
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
            listProduct
        )

        val item2 = ProductViaShopInCart(
            "2",
            "Dummy",
            false,
            listProduct1
        )

        val item3 = ProductViaShopInCart(
            "3",
            "Dummy Shop",
            false,
            listProduct2
        )

        val item4 = ProductViaShopInCart(
            "4",
            "a",
            false,
            listProduct3
        )

        val item5 = ProductViaShopInCart(
            "5",
            "b",
            false,
            listProduct4
        )
        list.add(item)
        list.add(item2)
        list.add(item3)
        list.add(item4)
        list.add(item5)
        return list
    }
}