package frontend.helpers

import com.codeborne.selenide.ElementsCollection
import io.kotest.assertions.AssertionErrorBuilder.Companion.fail

class Extensions {

    companion object {
        fun ElementsCollection.getFirstOrAsserted(text: String) {
            this.firstOrNull { it.text == text } ?: fail("Элемент $text не найден")
        }
    }
}