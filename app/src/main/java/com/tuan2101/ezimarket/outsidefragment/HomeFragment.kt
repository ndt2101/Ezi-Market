package com.tuan2101.ezimarket.outsidefragment

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.adapter.*
import com.tuan2101.ezimarket.databinding.FragmentHomeBinding
import com.tuan2101.ezimarket.dataclasses.*
import com.tuan2101.ezimarket.utils.ZoomOutPageTransformer
import com.tuan2101.ezimarket.viewmodel.HomeFragmentViewModel


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var listAdPhoto: List<AdvertisementPhoto>
    lateinit var runnable: Runnable
    val handler = Handler(Looper.getMainLooper())
    val selectedTopNewsItem = MutableLiveData<String>()
    val _testClickedTopNewsFooterItem = MutableLiveData<Boolean>()
    var testClickedTopSaleFooterItem: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    var testClickedTopFlashSaleItemId: MutableLiveData<String> = MutableLiveData<String>("teststss")
    var topCategoryClickedItem = MutableLiveData<String>()
    lateinit var homeFragmentViewModel: HomeFragmentViewModel
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
        val topSaleLayoutManager: LinearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val topNewsLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val topCategoryItemLayoutManager: GridLayoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)

        testClickedTopSaleFooterItem.value = false
        _testClickedTopNewsFooterItem.value = false

        val flashSaleAdapter = FlashSaleAdapter(
            ProductItemClickListener({ testClickFooterTopFlashSale() },
                { id -> testClickTopFlashSaleItem(id) })
        )

        val topNewsAdapter = TopNewsAdapter(TopNewsAdapter.TopNewsItemClickListener({testClickTopNewFooterItem()}, {id -> onClickTopNewsItem(id) }))

        homeFragmentViewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)

        binding.productRcv.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        val productAdapter = ProductAdapter(ProductListener { product ->
            testClickTopFlashSaleItem(
                product
            )
        })
        val topCategoryAdapter = TopCategoryItemAdapter(
            TopCategoryItemViewHolder.TopCategoryItemClickListener { id -> onClickTopCategoryItem(id) })

        binding.model = homeFragmentViewModel

        homeFragmentViewModel.suggestedClickedItem.observe(viewLifecycleOwner) {
            if (it == "0") {
                productAdapter.submitList(dummyDataForFlashSale())
                binding.suggestedAllItem.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.selected_suggest_item_bg)
            } else {
                binding.suggestedAllItem.background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.unselected_suggest_item_bg
                )
            }

            if (it == "1") {
                productAdapter.submitList(dummyDataForFlashSale())
                binding.suggestedDiscountHuntingItem.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.selected_suggest_item_bg)
            } else {
                binding.suggestedDiscountHuntingItem.background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.unselected_suggest_item_bg
                )
            }

            if (it == "2") {
                productAdapter.submitList(dummyDataForFlashSale())
                binding.suggestedOutfitItem.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.selected_suggest_item_bg)
            } else {
                binding.suggestedOutfitItem.background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.unselected_suggest_item_bg
                )
            }

            if (it == "3") {
                productAdapter.submitList(dummyDataForFlashSale())
                binding.suggestedMakeUpItem.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.selected_suggest_item_bg)
            } else {
                binding.suggestedMakeUpItem.background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.unselected_suggest_item_bg
                )
            }

            if (it == "4") {
                productAdapter.submitList(dummyDataForFlashSale())
                binding.suggestedBookItem.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.selected_suggest_item_bg)
            } else {
                binding.suggestedBookItem.background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.unselected_suggest_item_bg
                )
            }

            if (it == "5") {
                productAdapter.submitList(dummyDataForFlashSale())
                binding.suggestedFavoriteItem.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.selected_suggest_item_bg)
            } else {
                binding.suggestedFavoriteItem.background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.unselected_suggest_item_bg
                )
            }
        }

        binding.productRcv.adapter = productAdapter



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
        binding.advertisementSlide.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2500)
            }
        })


        binding.advertisementSlide.setPageTransformer(ZoomOutPageTransformer())

        binding.topCategoryItem.layoutManager = topCategoryItemLayoutManager

        topCategoryAdapter.submitList(dataForTopCategoryItem())

        binding.topCategoryItem.adapter = topCategoryAdapter


        val dummyNewsList = dummyDataForTopNews()
        binding.topNewsRcv.layoutManager = topNewsLayoutManager
        topNewsAdapter.customSubmitList(dummyNewsList)
        binding.topNewsRcv.adapter = topNewsAdapter
        testClickedTopSaleFooterItem.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, "chuyen", Toast.LENGTH_SHORT).show()
            }
        }

        homeFragmentViewModel.navToNotificationFragment.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_hostFragment_to_notificationFragment)
                homeFragmentViewModel.navToNotificationFragment.value = false
            }
        }

        homeFragmentViewModel.navToChatFragment.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(R.id.action_hostFragment_to_chatFragment)
                homeFragmentViewModel.navToNotificationFragment.value = false
            }
        }

        binding.search.imeOptions = EditorInfo.IME_ACTION_DONE

        binding.search.setOnEditorActionListener { _, p1, _ ->
            var handled = false
            if (p1 == EditorInfo.IME_ACTION_DONE) {
                if (binding.search.text.toString() != "") {
                    findNavController().navigate(
                        HostFragmentDirections.actionHostFragmentToSearchResultFragment(
                            binding.search.text.toString()
                        )
                    )
                }
                handled = true
            }
            handled
        }

        return binding.root
    }
    private fun dataForTopCategoryItem(): ArrayList<CategoryItem> {
        val listItem = ArrayList<CategoryItem>()
        listItem.add(CategoryItem("0", R.drawable.go_market, "??i ch???"))
        listItem.add(CategoryItem("1", R.drawable.cosmetics, "M??? ph???m n???"))
        listItem.add(CategoryItem("2", R.drawable.bike, "Xe m??y"))
        listItem.add(CategoryItem("3", R.drawable.sneakers, "Gi??y nam"))
        listItem.add(CategoryItem("4", R.drawable.voucher, "??u ????i"))
        listItem.add(CategoryItem("5", R.drawable.beef, "????? t????i s???ng"))
        listItem.add(CategoryItem("6", R.drawable.fast_food, "??n v???t"))
        listItem.add(CategoryItem("7", R.drawable.outfit, "Qu???n ??o n???"))
        listItem.add(CategoryItem("8", R.drawable.gift, "Qu?? t???ng"))
        listItem.add(CategoryItem("9", R.drawable.devices, "??i???n tho???i"))
        listItem.add(CategoryItem("10", R.drawable.fridge, "????? gia d???ng"))
        listItem.add(CategoryItem("11", R.drawable.furniture, "N???i th???t"))
        return listItem
    }

    fun testClickFooterTopFlashSale() {
        if (!testClickedTopSaleFooterItem.value!!) {
            testClickedTopSaleFooterItem.value = true
        }
        Log.i("aaa", "${testClickedTopSaleFooterItem.value}")
    }

    fun testClickTopFlashSaleItem(idItem: String) {
        testClickedTopFlashSaleItemId.value = idItem
        Log.i("aaa", idItem)
    }

    fun testClickTopFlashSaleItem(product: Product) {
        testClickedTopFlashSaleItemId.value = product.id
        Log.i("aaa", product.id)
    }

    fun onClickTopNewsItem(url: String) {
        selectedTopNewsItem.value = url
        findNavController().navigate(HostFragmentDirections.actionHostFragmentToNewsFragment(url))
    }

    fun testClickTopNewFooterItem() {
        if (!_testClickedTopNewsFooterItem.value!!) {
            _testClickedTopNewsFooterItem.value = true
        }

        Log.i("bbb", _testClickedTopNewsFooterItem.value.toString())
    }

    fun onClickTopCategoryItem(id: String) {
        topCategoryClickedItem.value = id
        if (id == "4") {
            findNavController().navigate(HostFragmentDirections.actionHostFragmentToCouponFragment())
        }
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

    private fun dummyDataForTopNews(): ArrayList<News> {
        val listImg = ArrayList<News>()
        listImg.add(News("a", "Ch????ng tr??nh gi???m gi?? shock trong m??a d???ch covid", "https://shopee.vn/m/tro-lai-san-deal-jan", "https://photo-cms-ngaynay.zadn.vn/666x374/Uploaded/2021/uncdwpjwq/2021_06_14/shoppe-8153.jpg"))
        listImg.add(News("b", "Ch????ng tr??nh gi???m gi?? shock trong m??a d???ch covid", "https://shopee.vn/m/tro-lai-san-deal-jan","https://shopeeplus.com/upload/images/L%E1%BA%AFc-L%C3%A0-Sale-Shopee.jpg"))
        listImg.add(News("c", "Ch????ng tr??nh gi???m gi?? shock trong m??a d???ch covid", "https://shopee.vn/m/tro-lai-san-deal-jan","https://ben.com.vn/tin-tuc/wp-content/uploads/2021/09/cach-san-sale-0d-tren-shopee-5.png"))
        listImg.add(News("d", "Ch????ng tr??nh gi???m gi?? shock trong m??a d???ch covid", "https://shopee.vn/m/tro-lai-san-deal-jan","https://channel.mediacdn.vn/thumb_w/640/2020/10/10/photo-1-16023035696791479981389.jpg"))
        listImg.add(News("e", "Ch????ng tr??nh gi???m gi?? shock trong m??a d???ch covid", "https://shopee.vn/m/tro-lai-san-deal-jan","https://cafefcdn.com/thumb_w/650/pr/2020/1606892520840-0-0-375-600-crop-1606892525006-63742515071978.jpg"))
        listImg.add(News("f", "Ch????ng tr??nh gi???m gi?? shock trong m??a d???ch covid", "https://shopee.vn/m/tro-lai-san-deal-jan","https://file.publish.vn/blogktcity/flash-sale-shopee-1593746001532.jpg"))
        return listImg
    }

    private fun dummyDataForFlashSale(): ArrayList<SearchedProduct> {
        val listProduct = ArrayList<SearchedProduct>()
        listProduct.add(
            SearchedProduct(
                System.currentTimeMillis().toString(),
                "https://cf.shopee.vn/file/af8c5c4597c61c9d5c6c1e4049ebf243",
                "??o blazer nam oversize , 2 l???p, m??u n??u t??y phong c??ch retro phong c??ch H??n Qu???c - BZ01",
                400000,
                299000,
                4.5f,
                900,
                "shipId1",
                "Ha noi"
            )
        )
        Thread.sleep(1)
        listProduct.add(
            SearchedProduct(
                System.currentTimeMillis().toString(),
                "https://thatlungnam.com.vn/wp-content/uploads/2019/04/3.jpg",
                "??o blazer nam oversize , 2 l???p, m??u n??u t??y phong c??ch retro phong c??ch H??n Qu???c - BZ01",
                299000,
                299000,
                4.5f,
                2900,
                "shipId2",
                "Ha noi"
            )
        )
        Thread.sleep(1)
        listProduct.add(
            SearchedProduct(
                System.currentTimeMillis().toString(),
                "https://media3.scdn.vn/img4/2021/06_04/epQjaa1kpxpng0MKD3rh.jpg",
                "??o blazer nam oversize , 2 l???p, m??u n??u t??y phong c??ch retro phong c??ch H??n Qu???c - BZ01",
                400000,
                299000,
                4.5f,
                29499,
                "shipId3",
                "Ha noi"
            )
        )
        Thread.sleep(1)
        listProduct.add(
            SearchedProduct(
                System.currentTimeMillis().toString(),
                "https://sakurafashion.vn/upload/a/1285-doc-menswear-7749.jpg",
                "??o blazer nam oversize , 2 l???p, m??u n??u t??y phong c??ch retro phong c??ch H??n Qu???c - BZ01",
                400000,
                299000,
                4.0f,
                900,
                "shipId4",
                "Ha noi"
            )
        )
        Thread.sleep(1)
        listProduct.add(
            SearchedProduct(
                System.currentTimeMillis().toString(),
                "https://cf.shopee.vn/file/46b13304e62d5ad704ef9ee99a1b9d22",
                "??o blazer nam oversize , 2 l???p, m??u n??u t??y phong c??ch retro phong c??ch H??n Qu???c - BZ01",
                400000,
                299000,
                3.5f,
                900,
                "shipId5",
                "Ha noi"
            )
        )
        Thread.sleep(1)
        listProduct.add(
            SearchedProduct(
                System.currentTimeMillis().toString(),
                "https://aristino.com/Data/upload/images/Product/ao-blazer/ABZ00908/ao-blazer-nam-aristino-ABZ00908-02.jpg",
                "??o blazer nam oversize , 2 l???p, m??u n??u t??y phong c??ch retro phong c??ch H??n Qu???c - BZ01",
                400000,
                299000,
                2.5f,
                900,
                "shipId1",
                "Ha noi"
            )
        )
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