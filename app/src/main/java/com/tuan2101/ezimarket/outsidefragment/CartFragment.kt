package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.adapter.CartAdapter
import com.tuan2101.ezimarket.adapter.CartViaShopAdapter
import com.tuan2101.ezimarket.databinding.FragmentCartBinding
import com.tuan2101.ezimarket.dataclasses.*
import com.tuan2101.ezimarket.viewmodel.CartFragmentViewModel

class CartFragment : Fragment() {

    lateinit var binding: FragmentCartBinding
    lateinit var adapter: CartAdapter
    val shareViewModel: CartFragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)
//        shareViewModel = ViewModelProvider(this)[CartFragmentViewModel::class.java]
        binding.productViaShopRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = CartAdapter(CartAdapter.ClickListener(
            {productViaShopInCart -> shareViewModel.clickAllProductViaShop(productViaShopInCart) },
            {shopId -> shareViewModel.clickVisitShop(shopId) },
            {productViaShopInCart -> shareViewModel.clickSelectVoucher(productViaShopInCart) }
        ), shareViewModel, viewLifecycleOwner, CartViaShopAdapter.ClickListener(
            {productInCart, productViaShopInCart -> shareViewModel.clickToPay(productInCart, productViaShopInCart) },
            {productInCart -> shareViewModel.clickToVisitProductDetail(productInCart) },
            {productInCart, productViaShopInCart -> shareViewModel.clickToBuyMore(productInCart, productViaShopInCart) },
            {productInCart, productViaShopInCart -> shareViewModel.clickToBuyLess(productInCart, productViaShopInCart) },
            {productInCart, productViaShopInCart -> shareViewModel.clickToDeleteProduct(productInCart, productViaShopInCart) }
        ))
        binding.productViaShopRecyclerView.adapter = adapter
        shareViewModel.listProductInCart.observe(viewLifecycleOwner, {
            adapter.submitList(it.clone() as ArrayList<ProductViaShopInCart>)
            Log.i("aaa", "vua xoa shop")
        })

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = shareViewModel

        shareViewModel.currentShopToGetVoucher.observe(viewLifecycleOwner, {
            if (it != null) {
                val voucherFragment = VoucherFragment(it) { voucher -> shareViewModel.setVoucher(voucher) }
                voucherFragment.show(childFragmentManager, VoucherFragment.TAG)
            }
        })

        shareViewModel.navigateToMarketVoucherFragment.observe(viewLifecycleOwner, {
            if (it == true) {
                val voucherFragment = VoucherFragment(shareViewModel.eziVoucher.value) { voucher -> shareViewModel.setMarketVoucher(voucher) }
                voucherFragment.show(childFragmentManager, VoucherFragment.TAG)
                shareViewModel.navigateToMarketVoucherFragment.value = false
            }
        })

        shareViewModel.totalPrice.observe(viewLifecycleOwner, {
            shareViewModel.applyMarkerVoucher()
        })

        shareViewModel.eziVoucher.observe(viewLifecycleOwner, {
            shareViewModel.applyMarkerVoucher()
        })

        shareViewModel.navToConfirmFragment.observe(viewLifecycleOwner, {
            if (it) {
                if (shareViewModel.needUpdatingList.size != 0) {
                    findNavController().navigate(R.id.action_cartFragment_to_billConfirmationFragment)
                } else {
                    Toast.makeText(context, "Bạn chưa chọn sản phẩm nào", Toast.LENGTH_SHORT).show()
                }
                shareViewModel.navToConfirmFragment.value = false
            }
        })

        shareViewModel.navToLocationFragment.observe(viewLifecycleOwner, {
            if (it) {
                findNavController().navigate(CartFragmentDirections.actionCartFragmentToLocationFragment())
                shareViewModel.navToLocationFragment.value = false
            }
        })
        return binding.root
    }



}