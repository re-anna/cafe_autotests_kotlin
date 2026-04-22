package infra.junit

import com.codeborne.selenide.Screenshots
import io.qameta.allure.Attachment
import config.Config
import org.junit.platform.engine.TestExecutionResult
import org.junit.platform.launcher.TestExecutionListener
import org.junit.platform.launcher.TestIdentifier
import org.junit.platform.launcher.TestPlan

class GlobalTestListener : TestExecutionListener {

        override fun testPlanExecutionStarted(testPlan: TestPlan) {
            println("|--- Test plan started ---|")
            // прогреваем конфиг, чтобы упасть сразу, если он битый
            Config.get
        }

    override fun executionStarted(testIdentifier: TestIdentifier) {
        if (!testIdentifier.isTest) return
        println("|--- Test started: ${testIdentifier.displayName}")
    }

    override fun executionFinished(
        testIdentifier: TestIdentifier,
        testExecutionResult: TestExecutionResult
    ) {
        if (testIdentifier.isTest)println("Finished test: ${testIdentifier.displayName} Result: ${testExecutionResult.status}")
        if(testExecutionResult.status == TestExecutionResult.Status.FAILED && testIdentifier.displayName != "JUnit Jupiter"){
            attachScreenshot()
        }
    }

    override fun executionSkipped(testIdentifier: TestIdentifier, reason: String) {
        if (!testIdentifier.isTest) return
        println("|--- Test Ignored: ${testIdentifier.displayName} - Reason: $reason")
    }


    @Attachment(value = "{name}", type = "image/png")
        fun attachScreenshot(name: String = "SCREENSHOT"): ByteArray? =
            Screenshots.takeScreenShotAsFile()?.readBytes()
}