package com.tuan2101.ezimarket.dataclasses

data class IntroPhoto(val text: String,
                 val src: Int
)

data class AdvertisementPhoto(val img: String)


open class CategoryItem(val id: String,
                        val img: Int,
                        val name: String
) {
    override fun equals(other: Any?): Boolean {
        return other is CategoryItem &&
                other.name == name &&
                other.id == id &&
                other.img == img
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + img
        result = 31 * result + name.hashCode()
        return result
    }


}

class ParentCategory(id: String, img: Int, name: String, val subCategoryList: List<CategoryItem>, val banner: String) : CategoryItem(id, img, name)