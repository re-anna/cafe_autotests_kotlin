package backend.api.models.products

data class CreateProductsRequest(
    var name: String?,
    var price: Double?,
    var description: String?
)
fun defaultProduct() = CreateProductsRequest(
    "Espresso for boss",
    1000.0,
    "Best coffee ever"
)