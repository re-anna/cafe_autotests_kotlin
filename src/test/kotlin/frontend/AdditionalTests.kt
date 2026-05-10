package frontend

import com.codeborne.selenide.Selenide
import frontend.helpers.BaseUiTest
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
class AdditionalTests : BaseUiTest() {

    @Disabled
    @Test
    fun openGoldApple(){
        Selenide.open("/")
        val title = Selenide.title()
        Selenide.sleep(10_000)
        title shouldBe "Gold Apple — checking device"
    }
}