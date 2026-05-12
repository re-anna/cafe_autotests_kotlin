package frontend.helpers

fun String.priceToCents(): Int = filter { it.isDigit() }.toInt()

fun String.extractInt(): Int = filter { it.isDigit() }.toInt()
