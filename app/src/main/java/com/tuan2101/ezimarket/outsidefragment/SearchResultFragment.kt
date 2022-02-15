package com.tuan2101.ezimarket.outsidefragment

import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.tuan2101.ezimarket.adapter.ProductAdapter
import com.tuan2101.ezimarket.adapter.ProductListener
import com.tuan2101.ezimarket.databinding.FilterDialogBinding
import com.tuan2101.ezimarket.databinding.FragmentSearchResultBinding
import com.tuan2101.ezimarket.dataclasses.Product
import com.tuan2101.ezimarket.dataclasses.SearchedProduct
import com.tuan2101.ezimarket.viewmodel.SearchResultFragmentViewModel
import com.tuan2101.ezimarket.viewmodel.SearchResultFragmentViewModelFactory

class SearchResultFragment : Fragment() {

    lateinit var binding: FragmentSearchResultBinding
    lateinit var viewModel: SearchResultFragmentViewModel
    lateinit var container: ViewGroup
    lateinit var launcher: ActivityResultLauncher<Intent>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        this.container = container!!
        val target = SearchResultFragmentArgs.fromBundle(requireArguments()).target
        viewModel = ViewModelProvider(
            this,
            SearchResultFragmentViewModelFactory(target)
        )[SearchResultFragmentViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.rcv.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        val productAdapter =
            ProductAdapter(ProductListener { product -> testClickProduct(product) })
        binding.rcv.adapter = productAdapter
        productAdapter.submitList(dummyDataForFlashSale())

        binding.filterArea.setOnClickListener {
            showFilter()
        }

        binding.voiceSearch.setOnClickListener {
            try {
                val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                intent.putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                )
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech to text")
                launcher.launch(intent)
            } catch (e: ActivityNotFoundException) {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://market.android.com/details?id=com.google.android.googlequicksearchbox")
                )
                launcher.launch(browserIntent)
            }
        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val data = it.data
                if (data != null) {
                    viewModel.target.value = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0)?.toString()
                }
            }
        }
    }

    fun testClickProduct(product: Product) {
        Log.i("search", product.id)
    }

    fun showFilter() {
        val dialog = Dialog(requireContext())
        val dialogBinding = FilterDialogBinding.inflate(layoutInflater, container, false)
        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setLayout(width, height)
        //TODO: xu ly chon

        dialogBinding.confirm.setOnClickListener {
            dialog.hide()
        }
        dialog.show()
    }

    private fun dummyDataForFlashSale(): ArrayList<SearchedProduct> {
        val listProduct = ArrayList<SearchedProduct>()
        listProduct.add(
            SearchedProduct(
                System.currentTimeMillis().toString(),
                "https://cf.shopee.vn/file/af8c5c4597c61c9d5c6c1e4049ebf243",
                "Áo blazer nam oversize , 2 lớp, màu nâu tây phong cách retro phong cách Hàn Quốc - BZ01",
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
                "Áo blazer nam oversize , 2 lớp, màu nâu tây phong cách retro phong cách Hàn Quốc - BZ01",
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
                "Áo blazer nam oversize , 2 lớp, màu nâu tây phong cách retro phong cách Hàn Quốc - BZ01",
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
                "Áo blazer nam oversize , 2 lớp, màu nâu tây phong cách retro phong cách Hàn Quốc - BZ01",
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
                "Áo blazer nam oversize , 2 lớp, màu nâu tây phong cách retro phong cách Hàn Quốc - BZ01",
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
                "Áo blazer nam oversize , 2 lớp, màu nâu tây phong cách retro phong cách Hàn Quốc - BZ01",
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

}