package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.adapter.CommentAdapter
import com.tuan2101.ezimarket.databinding.CommentFragmentBinding
import com.tuan2101.ezimarket.dataclasses.*
import com.tuan2101.ezimarket.viewmodel.CommentFragmentViewModel
import com.tuan2101.ezimarket.viewmodel.CommentFragmentViewModelFactory
import com.tuan2101.ezimarket.viewmodel.NewsFeedItemFragmentViewModel
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by ndt2101 on 11/1/2021.
 */
class CommentFragment() : BottomSheetDialogFragment() {
    companion object {
        val TAG: String = "CommentFragment"
    }

    var newsFeedItemFragmentViewModel: NewsFeedItemFragmentViewModel? = null

    constructor(newsFeedItemFragmentViewModel: NewsFeedItemFragmentViewModel) : this() {
        this.newsFeedItemFragmentViewModel = newsFeedItemFragmentViewModel
    }
    lateinit var viewModel: CommentFragmentViewModel
    lateinit var adapter: CommentAdapter
    lateinit var binding: CommentFragmentBinding
    private lateinit var viewModelFactory: CommentFragmentViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflate layout
        binding = CommentFragmentBinding.inflate(inflater, container, false)
        if (newsFeedItemFragmentViewModel?.currentPost?.value != null) {
            viewModelFactory = CommentFragmentViewModelFactory(newsFeedItemFragmentViewModel!!.currentPost.value)
        } else {
            viewModelFactory = CommentFragmentViewModelFactory(dummyData())
        }
        viewModel = ViewModelProvider(this, viewModelFactory)[CommentFragmentViewModel::class.java]
        adapter = CommentAdapter()
        adapter.submitList(viewModel.post?.commentList)
        binding.rcv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rcv.adapter = adapter

        return binding.root
    }

    fun dummyData() : Post {
        return Post(
            "0",
            Shop(
                "",
                "",
                "",
                "",
                null,
                null,
                Location(
                    "Nguyen Dinh Tuan",
                    "0789266255",
                    "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                    Ward("-1", "Mỹ Đình 2"),
                    District("-1", "Nam Từ Liêm"),
                    Province("-1", "Hà Nội", "Thành phố")
                ),
                "Ad5DmFc53BAxVxr1f3_sQSz9_SiEqmlCRSkQ2BHuk0WDyhweFoxQ9hCi3TNxcrIsTdJgiBvJck1_lGTu",
            ), 0,
            "",
            "",
            Product(
                "",
                "",
                "",
                0,
                0,
                0f,
                0,
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
                    ),"Ad5DmFc53BAxVxr1f3_sQSz9_SiEqmlCRSkQ2BHuk0WDyhweFoxQ9hCi3TNxcrIsTdJgiBvJck1_lGTu",
                ),
            ),
            Voucher(
                "",
                Shop(
                    "",
                    "",
                    "",
                    "",
                    null,
                    null,
                    Location(
                        "Nguyen Dinh Tuan",
                        "0789266255",
                        "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                        Ward("-1", "Mỹ Đình 2"),
                        District("-1", "Nam Từ Liêm"),
                        Province("-1", "Hà Nội", "Thành phố")
                    ),
                    "Ad5DmFc53BAxVxr1f3_sQSz9_SiEqmlCRSkQ2BHuk0WDyhweFoxQ9hCi3TNxcrIsTdJgiBvJck1_lGTu"
                ),
                0.0,
                "",
                Date(2021, 11, 18),
                0,
                0,
                false
            ),
            ArrayList<String>(),
            mutableListOf(
                Comment("c1",
                    Shop(
                        "0",
                        "",
                        "",
                        "",
                        null,
                        null,
                        Location(
                            "Nguyen Dinh Tuan",
                            "0789266255",
                            "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                            Ward("-1", "Mỹ Đình 2"),
                            District("-1", "Nam Từ Liêm"),
                            Province("-1", "Hà Nội", "Thành phố")
                        ),
                        "Ad5DmFc53BAxVxr1f3_sQSz9_SiEqmlCRSkQ2BHuk0WDyhweFoxQ9hCi3TNxcrIsTdJgiBvJck1_lGTu",
                    ),
                    0,
                    ""
                )
            )
        )
    }


    override fun onDestroy() {
        super.onDestroy()
        newsFeedItemFragmentViewModel?.currentPost?.value = null
    }
}