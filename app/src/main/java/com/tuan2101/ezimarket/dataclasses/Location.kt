package com.tuan2101.ezimarket.dataclasses

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
class Location(
    val name: String,
    val phoneNumber: String,
    val detailAddress: String,
    val ward: Ward,
    val district: District,
    val province: Province,
): Parcelable {
    override fun toString(): String {
        return "$detailAddress ${ward.name} ${district.name} ${province.name}"
    }

    fun clone(): Location {
        return Location(name, phoneNumber, detailAddress, ward, district, province)
    }
}
open class LocationParent(open val name: String)

@Parcelize
class Province(
    @Json(name = "province_id")
    val Id: String,
    @Json(name = "province_name")
    override val name: String,
    @Json(name = "province_type")
    val type: String
): Parcelable, LocationParent(name)

@Parcelize
class District(
    @Json(name = "district_id")
    val Id: String,
    @Json(name = "district_name")
    override val name: String
): Parcelable, LocationParent(name)

@Parcelize
class Ward(
    @Json(name = "ward_id")
    val Id: String,
    @Json(name = "ward_name")
    override val name: String
): Parcelable, LocationParent(name)

class ProvincesResult(@Json(name = "results") val result: List<Province>)

class DistrictsResult(@Json(name = "results") val result: List<District>)

class WardsResult(@Json(name = "results") val result: List<Ward>)