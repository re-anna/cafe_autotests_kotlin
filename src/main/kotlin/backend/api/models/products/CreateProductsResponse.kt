package backend.api.models.products

data class CreateProductsResponse(
    val id: Int,
    val name: String,
    val price: Double,
    val description: String
)