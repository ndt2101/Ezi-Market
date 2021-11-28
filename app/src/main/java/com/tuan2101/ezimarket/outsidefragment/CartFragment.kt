package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuan2101.ezimarket.adapter.CartAdapter
import com.tuan2101.ezimarket.adapter.CartViaShopAdapter
import com.tuan2101.ezimarket.databinding.FragmentCartBinding
import com.tuan2101.ezimarket.dataclasses.*
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
            {productViaShopInCart -> viewModel.clickSelectVoucher(productViaShopInCart) }
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

        viewModel.currentShopToGetVoucher.observe(viewLifecycleOwner, {
            if (it != null) {
                val voucherFragment = VoucherFragment(it) { voucher -> viewModel.setVoucher(voucher) }
                voucherFragment.show(childFragmentManager, VoucherFragment.TAG)
            }
        })

        viewModel.navigateToMarketVoucherFragment.observe(viewLifecycleOwner, {
            if (it == true) {
                val voucherFragment = VoucherFragment(viewModel.eziVoucher.value) { voucher -> viewModel.setMarketVoucher(voucher) }
                voucherFragment.show(childFragmentManager, VoucherFragment.TAG)
                viewModel.navigateToMarketVoucherFragment.value = false
            }
        })

        viewModel.totalPrice.observe(viewLifecycleOwner, {
            viewModel.applyMarkerVoucher()
        })

        viewModel.eziVoucher.observe(viewLifecycleOwner, {
            viewModel.applyMarkerVoucher()
        })

        viewModel.navToPaymentFragment.observe(viewLifecycleOwner, {
            if (it) {
                if (viewModel.needUpdatingList.size != 0) {
                    findNavController().navigate(CartFragmentDirections.actionCartFragmentToBillConfirmationFragment(viewModel.location!!,
                        viewModel.needUpdatingList.toTypedArray(), viewModel.finalPrice.value!!
                    ))
                } else {
                    Toast.makeText(context, "Bạn chưa chọn sản phẩm nào", Toast.LENGTH_SHORT).show()
                }
                viewModel.navToPaymentFragment.value = false
            }
        })

        viewModel.navToLocationFragment.observe(viewLifecycleOwner, {
            if (it) {
                findNavController().navigate(CartFragmentDirections.actionCartFragmentToLocationFragment(viewModel.location))
                viewModel.navToLocationFragment.value = false
            }
        })
        return binding.root
    }



}