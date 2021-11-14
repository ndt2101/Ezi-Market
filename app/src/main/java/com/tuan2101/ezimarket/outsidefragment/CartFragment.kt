package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.adapter.CartAdapter
import com.tuan2101.ezimarket.adapter.CartViaShopAdapter
import com.tuan2101.ezimarket.databinding.FragmentCartBinding
import com.tuan2101.ezimarket.dataclasses.Location
import com.tuan2101.ezimarket.dataclasses.Product
import com.tuan2101.ezimarket.dataclasses.ProductInCart
import com.tuan2101.ezimarket.dataclasses.ProductViaShopInCart
import com.tuan2101.ezimarket.viewmodel.CartFragmentViewModel

class CartFragment : Fragment() {

    lateinit var binding: FragmentCartBinding
    lateinit var adapter: CartAdapter
    lateinit var viewModel: CartFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[CartFragmentViewModel::class.java]
        binding.productViaShopRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = CartAdapter(CartAdapter.ClickListener(
            {productViaShopInCart -> viewModel.clickAllProductViaShop(productViaShopInCart) },
            {shopId -> viewModel.clickVisitShop(shopId) },
            {shopId -> viewModel.clickSelectVoucher(shopId) }
        ), viewModel, viewLifecycleOwner, CartViaShopAdapter.ClickListener(
            {productInCart, productViaShopInCart -> viewModel.clickToPay(productInCart, productViaShopInCart) },
            {productInCart -> viewModel.clickToVisitProductDetail(productInCart) },
            {productInCart, productViaShopInCart -> viewModel.clickToBuyMore(productInCart, productViaShopInCart) },
            {productInCart, productViaShopInCart -> viewModel.clickToBuyLess(productInCart, productViaShopInCart) },
            {productInCart, productViaShopInCart -> viewModel.clickToDeleteProduct(productInCart, productViaShopInCart) }
        ))
        binding.productViaShopRecyclerView.adapter = adapter
        viewModel.listProductInCart.observe(viewLifecycleOwner, {
            adapter.submitList(it.clone() as ArrayList<ProductViaShopInCart>)
            Log.i("aaa", "vua xoa shop")
        })

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.address.text = Location("Doi 6 An Doai", "An binh", "Nam Sach", "Hai Duong").toString()
        binding.phoneNumber.text = "0789266255"
        binding.receiver.text = "Nguyen Dinh Tuan"

        return binding.root
    }


}