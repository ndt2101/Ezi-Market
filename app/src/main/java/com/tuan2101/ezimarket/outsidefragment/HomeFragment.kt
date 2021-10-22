package com.tuan2101.ezimarket.outsidefragment

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.adapter.*
import com.tuan2101.ezimarket.databinding.FragmentHomeBinding
import com.tuan2101.ezimarket.dataclasses.AdvertisementPhoto
import com.tuan2101.ezimarket.dataclasses.Product
import com.tuan2101.ezimarket.dataclasses.CategoryItem
import com.tuan2101.ezimarket.utils.ZoomOutPageTransformer
import java.time.LocalDate


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var listAdPhoto: List<AdvertisementPhoto>
    lateinit var runnable: Runnable
    val handler = Handler(Looper.getMainLooper())

    var testCheck: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    var testCheck2: MutableLiveData<String> = MutableLiveData<String>("teststss")
    var topCategoryClickedItem = MutableLiveData<Int>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.bitmap)
        Palette.from(bitmap).generate { palette ->
            val color1 = palette?.getVibrantColor(resources.getColor(R.color.themeColor))
            if (color1 != null) {
                binding.collapsingToolbarLayout.setContentScrimColor(color1)
            }
        }

        listAdPhoto = dummyDataForAdvertisement()

        val dummyProductList = dummyDataForFlashSale()
        val advertisementAdapter = AdvertisementAdapter(listAdPhoto)
        val topSaleLayoutManager: LinearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        testCheck.value = false
        val flashSaleAdapter = FlashSaleAdapter(ProductItemClickListener({ testClick1() }, { id -> testClick2(id) }))
        val topCategoryItemLayoutManager: GridLayoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
        flashSaleAdapter.customSubmitList(dummyProductList)
        binding.topSaleRcv.layoutManager = topSaleLayoutManager
        binding.topSaleRcv.adapter = flashSaleAdapter
        binding.advertisementSlide.adapter = advertisementAdapter
        binding.ciIndicator.setViewPager(binding.advertisementSlide)
        runnable = Runnable {
            if (binding.advertisementSlide.currentItem == listAdPhoto.size - 1) {
                binding.advertisementSlide.currentItem = 0
            } else {
                binding.advertisementSlide.currentItem = binding.advertisementSlide.currentItem + 1
            }
            //                handler.postDelayed(this, 2500)
        }
        binding.advertisementSlide.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2500)
            }
        })
        binding.advertisementSlide.setPageTransformer(ZoomOutPageTransformer())

        binding.topCategoryItem.layoutManager = topCategoryItemLayoutManager
        binding.topCategoryItem.adapter = TopCategoryItemAdapter(dataForTopCategoryItem(), TopCategoryItemViewHolder.TopCategoryItemClickListener { id -> onClickTopCategoryItem(id) })
        testCheck.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(context, "chuyen", Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }

    fun testClick1(){
        if (!testCheck.value!!) {
            testCheck.value = true
        }
        Log.i("aaa", "${testCheck.value}")
    }

    fun testClick2(idItem: String) {
        testCheck2.value = idItem
        Log.i("aaa", idItem)
    }

    fun onClickTopCategoryItem(id: Int) {
        topCategoryClickedItem.value = id
        Log.i("ItemId", id.toString())
    }

    private fun dummyDataForAdvertisement(): ArrayList<AdvertisementPhoto> {
        val listImg = ArrayList<AdvertisementPhoto>()
        listImg.add(AdvertisementPhoto("https://photo-cms-ngaynay.zadn.vn/666x374/Uploaded/2021/uncdwpjwq/2021_06_14/shoppe-8153.jpg"))
        listImg.add(AdvertisementPhoto("https://shopeeplus.com/upload/images/L%E1%BA%AFc-L%C3%A0-Sale-Shopee.jpg"))
        listImg.add(AdvertisementPhoto("https://ben.com.vn/tin-tuc/wp-content/uploads/2021/09/cach-san-sale-0d-tren-shopee-5.png"))
        listImg.add(AdvertisementPhoto("https://channel.mediacdn.vn/thumb_w/640/2020/10/10/photo-1-16023035696791479981389.jpg"))
        listImg.add(AdvertisementPhoto("https://cafefcdn.com/thumb_w/650/pr/2020/1606892520840-0-0-375-600-crop-1606892525006-63742515071978.jpg"))
        listImg.add(AdvertisementPhoto("https://file.publish.vn/blogktcity/flash-sale-shopee-1593746001532.jpg"))
        return listImg
    }

    private fun dataForTopCategoryItem() : ArrayList<CategoryItem> {
        val listItem = ArrayList<CategoryItem>()
        listItem.add(CategoryItem(0, R.drawable.go_market, "Đi chợ"))
        listItem.add(CategoryItem(1, R.drawable.cosmetics, "Mỹ phẩm"))
        listItem.add(CategoryItem(2, R.drawable.customer_care, "Chat hỗ trợ"))
        listItem.add(CategoryItem(3, R.drawable.sneakers, "Giày dép"))
        listItem.add(CategoryItem(4, R.drawable.voucher, "Ưu đãi"))
        listItem.add(CategoryItem(5, R.drawable.beef, "Đồ tươi sống"))
        listItem.add(CategoryItem(6, R.drawable.fast_food, "Ăn vặt"))
        listItem.add(CategoryItem(7, R.drawable.outfit, "Quần áo"))
        listItem.add(CategoryItem(8, R.drawable.gift, "Quà tặng"))
        listItem.add(CategoryItem(9, R.drawable.devices, "Đồ điện tử"))
        listItem.add(CategoryItem(10, R.drawable.fridge, "Đồ gia dụng"))
        listItem.add(CategoryItem(11, R.drawable.engineer, "Sửa chữa đồ gia dụng"))
        return listItem
    }

    private fun dummyDataForFlashSale(): ArrayList<Product> {
        val listProduct = ArrayList<Product>()
        listProduct.add(Product(System.currentTimeMillis().toString(),
            "https://cf.shopee.vn/file/af8c5c4597c61c9d5c6c1e4049ebf243",
            "Áo blazer nam oversize , 2 lớp, màu nâu tây phong cách retro phong cách Hàn Quốc - BZ01",
            400000,
            299000,
            4.5,
            "Ho Chi Minh",
            2900
        ))
        Thread.sleep(1)
        listProduct.add(Product(System.currentTimeMillis().toString(),
            "https://thatlungnam.com.vn/wp-content/uploads/2019/04/3.jpg",
            "Áo blazer nam oversize , 2 lớp, màu nâu tây phong cách retro phong cách Hàn Quốc - BZ01",
            400000,
            299000,
            4.5,
            "Ho Chi Minh",
            2900
        ))
        Thread.sleep(1)
        listProduct.add(Product(System.currentTimeMillis().toString(),
            "https://media3.scdn.vn/img4/2021/06_04/epQjaa1kpxpng0MKD3rh.jpg",
            "Áo blazer nam oversize , 2 lớp, màu nâu tây phong cách retro phong cách Hàn Quốc - BZ01",
            400000,
            299000,
            4.5,
            "Ho Chi Minh",
            2900
        ))
        Thread.sleep(1)
        listProduct.add(Product(System.currentTimeMillis().toString(),
            "https://sakurafashion.vn/upload/a/1285-doc-menswear-7749.jpg",
            "Áo blazer nam oversize , 2 lớp, màu nâu tây phong cách retro phong cách Hàn Quốc - BZ01",
            400000,
            299000,
            4.5,
            "Ho Chi Minh",
            2900
        ))
        Thread.sleep(1)
        listProduct.add(Product(System.currentTimeMillis().toString(),
            "https://cf.shopee.vn/file/46b13304e62d5ad704ef9ee99a1b9d22",
            "Áo blazer nam oversize , 2 lớp, màu nâu tây phong cách retro phong cách Hàn Quốc - BZ01",
            400000,
            299000,
            4.5,
            "Ho Chi Minh",
            2900
        ))
        Thread.sleep(1)
        listProduct.add(Product(System.currentTimeMillis().toString(),
            "https://aristino.com/Data/upload/images/Product/ao-blazer/ABZ00908/ao-blazer-nam-aristino-ABZ00908-02.jpg",
            "Áo blazer nam oversize , 2 lớp, màu nâu tây phong cách retro phong cách Hàn Quốc - BZ01",
            400000,
            299000,
            4.5,
            "Ho Chi Minh",
            2900
        ))
        return listProduct
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 2500)
    }
}