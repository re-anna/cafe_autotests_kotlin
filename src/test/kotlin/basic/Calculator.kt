package basic

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("basic")
class Calculator {

    val a=5
    val b=5


    @Tag("multiply")
    @Test
    @DisplayName("Multiply 2 numbers")
    fun testMultiply() {
        val actualResult = a*b
        val expectedResult = 25

        expectedResult shouldBe actualResult
    }

    @Test
    @DisplayName("Multiply x100")
    @Tag("multiply")
    fun testMinus() {
        val actualResult = a*100
        val expectedResult = 500

        expectedResult shouldBe actualResult
    }

        @Test
        @DisplayName("Sum of 2 numbers")
        @Tag("regression")
        fun testSum() {
            val actualResult = a+b
            val expectedResult = 10

            expectedResult shouldBe actualResult

            assertEquals(expectedResult,actualResult, "Сумма а+б не верная")
        }
}