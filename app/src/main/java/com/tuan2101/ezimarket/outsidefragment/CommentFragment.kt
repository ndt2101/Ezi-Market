package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
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
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by ndt2101 on 11/1/2021.
 */
class CommentFragment() : BottomSheetDialogFragment() {
    companion object {
        val TAG: String = "CommentFragment"
    }

    var post: Post? = null

    constructor(post: Post) : this() {
        this.post = post
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
        viewModelFactory = if (post != null) {
            CommentFragmentViewModelFactory(post)
        } else {
            CommentFragmentViewModelFactory(fakePost())
        }
        viewModel = ViewModelProvider(this, viewModelFactory)[CommentFragmentViewModel::class.java]
        adapter = CommentAdapter()
        adapter.submitList(viewModel.commentList)
        binding.rcv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rcv.adapter = adapter

        return binding.root
    }

    fun dummyData() : ArrayList<Comment> {
        val list = ArrayList<Comment>()
        list.addAll(mutableListOf(
            Comment("c1",
                DisplayUser("u1",
                    "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                    "Tuan",
                    "User",
                    true
                    ),
                System.currentTimeMillis(),
                ""
            )
        ))
        return list

    }

    fun fakePost(): Post {
        return Post(
            "0",
            DisplayUser(
                "u1",
                "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                "Thùy Dương",
                "shop",
                true
            ), System.currentTimeMillis(),
            resources.getString(R.string.test_text),
            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
            Product(
                "p0",
                "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/248384203_1067157620782867_6617827598330014721_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=VavUaKnuwFYAX9aToDm&_nc_ht=scontent.fhph1-1.fna&oh=92e07c5c07854ba1c60973f97c5e0ae3&oe=61A530BC",
                "Thùy Dương product",
                25000,
                25000
            ),
            PostVoucher(
                "v1",
                0.3,
                "Giảm 30% khi mua đơn hàng từ 20 triệu VND",
                Date(),
                20000000,
                false,
                "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
            ),
            ArrayList<String>(),
            6
        )
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}