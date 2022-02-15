package com.tuan2101.ezimarket.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuan2101.ezimarket.dataclasses.*
import kotlin.collections.ArrayList

/**
 * Created by ndt2101 on 1/25/2022.
 */
class PersonalPageFragmentViewModel(val userId: String): ViewModel() {
    var user: User
    var postList = MutableLiveData<List<Post>>()

    init {
        user = dummyUser()
    }

    private fun dummyUser(): User {
        return User("1", "Nguyễn Đình Tuấn", "https://i.ytimg.com/vi/989-7xsRLR4/maxresdefault.jpg", "customer", ArrayList<String>(),ArrayList<String>(), Location("","","",
            Ward("", ""), District("", ""), Province("", "", "")
        ))
    }

}