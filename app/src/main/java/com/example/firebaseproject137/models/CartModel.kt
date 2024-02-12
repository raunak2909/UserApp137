package gov.rajasthan.firstapp137

data class CartModel(
    var cartId: String = "",
    var catId: String = "",
    var subCatId: String = "",
    var productId: String = "",
    var productTitle: String = "",
    var productDesc: String = "",
    var productQty: Int = 0,
    var productPrice: Double = 0.0,
    var productDiscountPrice: Double = 0.0,
    var productDiscountPer: Double = 0.0,
    var productImg: String = "",
    var productSelectedColor: String = "",
    var productUnit: String = "",
    var addedAt: Long = 0)
