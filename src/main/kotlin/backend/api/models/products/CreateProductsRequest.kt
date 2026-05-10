package backend.api.models.products

data class CreateProductsRequest(
    var name: String?,
    var price: Double?,
    var description: String?
)