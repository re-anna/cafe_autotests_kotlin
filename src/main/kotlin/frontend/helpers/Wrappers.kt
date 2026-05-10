package frontend.helpers

import com.codeborne.selenide.Selectors
import org.openqa.selenium.By

object Wrappers {
        fun byDataTestGroup(target: String): By =
            Selectors.by("data-test-group",target)

        fun byDataTestId(target: String): By =
            Selectors.by("data-test-id",target)
}