package frontend.helpers

fun String.priceToCents(): Int = filter { it.isDigit() }.toInt()
