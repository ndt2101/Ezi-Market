package com.tuan2101.ezimarket.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tuan2101.ezimarket.dataclasses.DistrictsResult
import com.tuan2101.ezimarket.dataclasses.ProvincesResult
import com.tuan2101.ezimarket.dataclasses.WardsResult
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by ndt2101 on 11/21/2021.
 */

const val BASE_URL: String = "https://vapi.vnappmob.com"

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface LocationApiCall {
    @GET("/api/province/")
    fun getProvinces(): Deferred<ProvincesResult>

    @GET(" /api/province/district/{id}")
    fun getDistrict(@Path("id") id: String): Deferred<DistrictsResult>

    @GET("/api/province/ward/{id}")
    fun getWards(@Path("id") id: String): Deferred<WardsResult>
}

object CallLocationApi {
    val retrofitService: LocationApiCall by lazy {
        retrofit.create(LocationApiCall::class.java)
    }
}