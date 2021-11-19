package com.tuan2101.ezimarket.outsidefragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tuan2101.ezimarket.R
import com.tuan2101.ezimarket.adapter.PostAdapter
import com.tuan2101.ezimarket.databinding.FragmentNewsFeedItemBinding
import com.tuan2101.ezimarket.dataclasses.*
import com.tuan2101.ezimarket.viewmodel.NewsFeedItemFragmentViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NewsFeedItemFragment() : Fragment() {

    lateinit var viewModel: NewsFeedItemFragmentViewModel
    lateinit var adapter: PostAdapter
    lateinit var commentFragment: CommentFragment
    var type: String = ""

    constructor(type: String) : this() {
        this.type = type
    }

    lateinit var binding: FragmentNewsFeedItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsFeedItemBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[NewsFeedItemFragmentViewModel::class.java]

        commentFragment = CommentFragment(viewModel)
        Log.i("FragmentType", type)
        adapter = PostAdapter(
            dummyDataForPost(),
            PostAdapter.OnPostClickListener { post -> viewModel.setCurrentPost(post) })
        binding.rcv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rcv.adapter = adapter

        viewModel.currentPost.observe(viewLifecycleOwner, {
            if (it != null) {
                commentFragment.show(childFragmentManager, CommentFragment.TAG)
            }
        })

        return binding.root
    }

    fun dummyDataForPost(): ArrayList<Post> {
        val list = ArrayList<Post>()
        val cal = Calendar.getInstance()
        cal[Calendar.YEAR] = 2021
        cal[Calendar.MONTH] = Calendar.NOVEMBER
        cal[Calendar.DAY_OF_MONTH] = 16
        val date: Date = cal.time

        Log.i("date", "$date")

        list.add(
            Post(
                "0",
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
                ), System.currentTimeMillis(),
                resources.getString(R.string.test_text),
                "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                Product(
                    "p0",
                    "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/248384203_1067157620782867_6617827598330014721_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=VavUaKnuwFYAX9aToDm&_nc_ht=scontent.fhph1-1.fna&oh=92e07c5c07854ba1c60973f97c5e0ae3&oe=61A530BC",
                    "Thùy Dương product",
                    25000,
                    25000,
                    5.0f,
                    0,
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
                    )
                ),
                Voucher(
                    "v1",
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
                    0.3,
                    "Giảm 30% khi mua đơn hàng từ 20 triệu VND",
                    date,
                    20000000,
                    10,
                    false
                ),
                ArrayList<String>(),
                mutableListOf(
                    Comment(
                        "c1",
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
                        System.currentTimeMillis(),
                        "resources.getString(R.string.test_text)"
                    ), Comment(
                        "c1",
                        Shop(
                            "u1",
                            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                            "Thùy Dương",
                            "Customer",
                            null,
                            null,
                            Location(
                                "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                                "Mỹ Đình 2",
                                "Nam Từ Liêm",
                                "Hà Nội"
                            )
                        ),
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
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
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
                        Shop(
                            "u1",
                            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                            "Thùy Dương",
                            "customer",
                            null,
                            null,
                            Location(
                                "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                                "Mỹ Đình 2",
                                "Nam Từ Liêm",
                                "Hà Nội"
                            )
                        ),
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
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
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
                        Shop(
                            "u1",
                            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                            "Thùy Dương",
                            "customer",
                            null,
                            null,
                            Location(
                                "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                                "Mỹ Đình 2",
                                "Nam Từ Liêm",
                                "Hà Nội"
                            )
                        ),
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    )
                )
            )
        )
        list.add(
            Post(
                "0",
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
                ), System.currentTimeMillis(),
                resources.getString(R.string.test_text),
                "https://javhd.pics/photos/japanese/moe-amatsuka/117/moe-amatsuka-3.jpg",
                Product(
                    "p0",
                    "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/248384203_1067157620782867_6617827598330014721_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=VavUaKnuwFYAX9aToDm&_nc_ht=scontent.fhph1-1.fna&oh=92e07c5c07854ba1c60973f97c5e0ae3&oe=61A530BC",
                    "Thùy Dương product",
                    25000,
                    25000,
                    5.0f,
                    0,
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
                    )
                ),
                Voucher(
                    "v1",
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
                    0.3,
                    "Giảm 30% khi mua đơn hàng từ 20 triệu VND",
                    date,
                    20000000,
                    10,
                    false
                ),
                ArrayList<String>(),
                mutableListOf(
                    Comment(
                        "c1",
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
                        System.currentTimeMillis(),
                        "resources.getString(R.string.test_text)"
                    ), Comment(
                        "c1",
                        Shop(
                            "u1",
                            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                            "Thùy Dương",
                            "Customer",
                            null,
                            null,
                            Location(
                                "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                                "Mỹ Đình 2",
                                "Nam Từ Liêm",
                                "Hà Nội"
                            )
                        ),
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
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
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
                        Shop(
                            "u1",
                            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                            "Thùy Dương",
                            "customer",
                            null,
                            null,
                            Location(
                                "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                                "Mỹ Đình 2",
                                "Nam Từ Liêm",
                                "Hà Nội"
                            )
                        ),
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
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
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
                        Shop(
                            "u1",
                            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                            "Thùy Dương",
                            "customer",
                            null,
                            null,
                            Location(
                                "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                                "Mỹ Đình 2",
                                "Nam Từ Liêm",
                                "Hà Nội"
                            )
                        ),
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    )
                )
            )
        )
        list.add(
            Post(
                "0",
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
                ), System.currentTimeMillis(),
                resources.getString(R.string.test_text),
                "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                Product(
                    "p0",
                    "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/248384203_1067157620782867_6617827598330014721_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=VavUaKnuwFYAX9aToDm&_nc_ht=scontent.fhph1-1.fna&oh=92e07c5c07854ba1c60973f97c5e0ae3&oe=61A530BC",
                    "Thùy Dương product",
                    25000,
                    25000,
                    5.0f,
                    0,
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
                    )
                ),
                Voucher(
                    "v1",
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
                    0.3,
                    "Giảm 30% khi mua đơn hàng từ 20 triệu VND",
                    date,
                    20000000,
                    10, false
                ),
                ArrayList<String>(),
                mutableListOf(
                    Comment(
                        "c1",
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
                        System.currentTimeMillis(),
                        "resources.getString(R.string.test_text)"
                    ), Comment(
                        "c1",
                        Shop(
                            "u1",
                            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                            "Thùy Dương",
                            "Customer",
                            null,
                            null,
                            Location(
                                "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                                "Mỹ Đình 2",
                                "Nam Từ Liêm",
                                "Hà Nội"
                            )
                        ),
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
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
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
                        Shop(
                            "u1",
                            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                            "Thùy Dương",
                            "customer",
                            null,
                            null,
                            Location(
                                "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                                "Mỹ Đình 2",
                                "Nam Từ Liêm",
                                "Hà Nội"
                            )
                        ),
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
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
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
                        Shop(
                            "u1",
                            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                            "Thùy Dương",
                            "customer",
                            null,
                            null,
                            Location(
                                "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                                "Mỹ Đình 2",
                                "Nam Từ Liêm",
                                "Hà Nội"
                            )
                        ),
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    )
                )
            )
        )
        list.add(
            Post(
                "0",
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
                ), System.currentTimeMillis(),
                resources.getString(R.string.test_text),
                "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                Product(
                    "p0",
                    "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/248384203_1067157620782867_6617827598330014721_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=VavUaKnuwFYAX9aToDm&_nc_ht=scontent.fhph1-1.fna&oh=92e07c5c07854ba1c60973f97c5e0ae3&oe=61A530BC",
                    "Thùy Dương product",
                    25000,
                    25000,
                    5.0f,
                    0,
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
                    )
                ),
                Voucher(
                    "v1",
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
                    0.3,
                    "Giảm 30% khi mua đơn hàng từ 20 triệu VND",
                    date,
                    20000000,
                    10, false
                ),
                ArrayList<String>(),
                mutableListOf(
                    Comment(
                        "c1",
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
                        System.currentTimeMillis(),
                        "resources.getString(R.string.test_text)"
                    ), Comment(
                        "c1",
                        Shop(
                            "u1",
                            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                            "Thùy Dương",
                            "Customer",
                            null,
                            null,
                            Location(
                                "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                                "Mỹ Đình 2",
                                "Nam Từ Liêm",
                                "Hà Nội"
                            )
                        ),
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
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
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
                        Shop(
                            "u1",
                            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                            "Thùy Dương",
                            "customer",
                            null,
                            null,
                            Location(
                                "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                                "Mỹ Đình 2",
                                "Nam Từ Liêm",
                                "Hà Nội"
                            )
                        ),
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
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
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
                        Shop(
                            "u1",
                            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                            "Thùy Dương",
                            "customer",
                            null,
                            null,
                            Location(
                                "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                                "Mỹ Đình 2",
                                "Nam Từ Liêm",
                                "Hà Nội"
                            )
                        ),
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    )
                )
            )
        )
        list.add(
            Post(
                "0",
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
                ), System.currentTimeMillis(),
                resources.getString(R.string.test_text),
                "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                Product(
                    "p0",
                    "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/248384203_1067157620782867_6617827598330014721_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=VavUaKnuwFYAX9aToDm&_nc_ht=scontent.fhph1-1.fna&oh=92e07c5c07854ba1c60973f97c5e0ae3&oe=61A530BC",
                    "Thùy Dương product",
                    25000,
                    25000,
                    5.0f,
                    0,
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
                    )
                ),
                Voucher(
                    "v1",
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
                    0.3,
                    "Giảm 30% khi mua đơn hàng từ 20 triệu VND",
                    date,
                    20000000,
                    10,
                    false
                ),
                ArrayList<String>(),
                mutableListOf(
                    Comment(
                        "c1",
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
                        System.currentTimeMillis(),
                        "resources.getString(R.string.test_text)"
                    ), Comment(
                        "c1",
                        Shop(
                            "u1",
                            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                            "Thùy Dương",
                            "Customer",
                            null,
                            null,
                            Location(
                                "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                                "Mỹ Đình 2",
                                "Nam Từ Liêm",
                                "Hà Nội"
                            )
                        ),
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
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
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
                        Shop(
                            "u1",
                            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                            "Thùy Dương",
                            "customer",
                            null,
                            null,
                            Location(
                                "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                                "Mỹ Đình 2",
                                "Nam Từ Liêm",
                                "Hà Nội"
                            )
                        ),
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
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
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
                        Shop(
                            "u1",
                            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                            "Thùy Dương",
                            "customer",
                            null,
                            null,
                            Location(
                                "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                                "Mỹ Đình 2",
                                "Nam Từ Liêm",
                                "Hà Nội"
                            )
                        ),
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    )
                )
            )
        )
        list.add(
            Post(
                "0",
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
                ), System.currentTimeMillis(),
                resources.getString(R.string.test_text),
                "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                Product(
                    "p0",
                    "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/248384203_1067157620782867_6617827598330014721_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=VavUaKnuwFYAX9aToDm&_nc_ht=scontent.fhph1-1.fna&oh=92e07c5c07854ba1c60973f97c5e0ae3&oe=61A530BC",
                    "Thùy Dương product",
                    25000,
                    25000,
                    5.0f,
                    0,
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
                    )
                ),
                Voucher(
                    "v1",
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
                    0.3,
                    "Giảm 30% khi mua đơn hàng từ 20 triệu VND",
                    date,
                    20000000,
                    10,
                    false
                ),
                ArrayList<String>(),
                mutableListOf(
                    Comment(
                        "c1",
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
                        System.currentTimeMillis(),
                        "resources.getString(R.string.test_text)"
                    ), Comment(
                        "c1",
                        Shop(
                            "u1",
                            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                            "Thùy Dương",
                            "Customer",
                            null,
                            null,
                            Location(
                                "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                                "Mỹ Đình 2",
                                "Nam Từ Liêm",
                                "Hà Nội"
                            )
                        ),
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
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
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
                        Shop(
                            "u1",
                            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                            "Thùy Dương",
                            "customer",
                            null,
                            null,
                            Location(
                                "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                                "Mỹ Đình 2",
                                "Nam Từ Liêm",
                                "Hà Nội"
                            )
                        ),
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
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
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    ), Comment(
                        "c1",
                        Shop(
                            "u1",
                            "https://scontent.fhph1-1.fna.fbcdn.net/v/t1.6435-9/125226349_844433706388594_2385910073448397181_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=174925&_nc_ohc=U2SplMZze9IAX8Ajsi6&tn=X5YmyF0NGX8K6WZV&_nc_ht=scontent.fhph1-1.fna&oh=79b402763192f8abea8dc870ff8f2e92&oe=61A5878E",
                            "Thùy Dương",
                            "customer",
                            null,
                            null,
                            Location(
                                "Nhà xứng số 4, Nghách 63/194, Đường Lê Đức Thọ",
                                "Mỹ Đình 2",
                                "Nam Từ Liêm",
                                "Hà Nội"
                            )
                        ),
                        System.currentTimeMillis(),
                        resources.getString(R.string.test_text)
                    )
                )
            )
        )
        return list
    }
}