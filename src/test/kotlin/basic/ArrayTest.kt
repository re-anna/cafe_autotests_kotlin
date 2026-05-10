package basic

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("basic")
class ArrayTest {

    @Test
    @DisplayName("Empty array should be empty")
    fun checkEmptyArray(){
        emptyArray<Int>().asList().shouldBeEmpty()
    }

    @Test
    @DisplayName("Array should not be empty")
    fun checkNotEmptyArray(){
        val array = arrayOf(2,5,7)
        array.size shouldBe 3
    }

    @Test
    fun `first element should match expected`() {
        val array = arrayOf("One", "Two", "Five")
        array.first() shouldBe "One"
        array.last() shouldBe "Five"
    }

    @Test
    fun `list should contain expected values`() {
        val list = listOf(2, 3, 6, 10, 220, 1230)
        list shouldHaveSize 6
        list shouldContain 6
    }
}