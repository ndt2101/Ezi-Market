package com.tuan2101.ezimarket.dataclasses

data class IntroPhoto(val text: String,
                 val src: Int
)

data class AdvertisementPhoto(val img: String)

data class CategoryItem(val id: Int,
                        val img: Int,
                        val name: String
)