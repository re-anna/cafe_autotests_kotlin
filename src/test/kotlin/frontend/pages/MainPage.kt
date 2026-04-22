package frontend.pages

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.element
import com.codeborne.selenide.SelenideElement
import frontend.components.HeaderComponent
import frontend.helpers.Wrappers.byDataTestId

class MainPage {
    private val title: SelenideElement get() = element(byDataTestId("main-image-text"))
    private val header = HeaderComponent()

    fun open(): MainPage = apply {Selenide.open("/")}
    fun getTitle(): String = title.text.trim()
    fun header(): HeaderComponent = header
}