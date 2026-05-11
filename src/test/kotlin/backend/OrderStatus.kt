package backend

enum class OrderStatus(val value: String) {
    PENDING("PENDING"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED")
}