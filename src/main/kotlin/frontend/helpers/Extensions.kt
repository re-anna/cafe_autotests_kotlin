package frontend.helpers

import com.codeborne.selenide.ElementsCollection
import io.kotest.assertions.AssertionErrorBuilder.Companion.fail

//Размещаем расширения для классов, которые используются в тестах
class Extensions {

    companion object {
        fun ElementsCollection.getFirstOrAsserted(text: String) {
            this.firstOrNull { it.text == text } ?: fail("Элемент $text не найден")
        }
    }
}