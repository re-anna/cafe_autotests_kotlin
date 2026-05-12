package infra.junit

import backend.helpers.AuthHelper
import backend.helpers.GarbageCollector
import backend.helpers.ProductsHelper
import backend.controllers.Controllers
import com.codeborne.selenide.Screenshots
import com.codeborne.selenide.Selenide
import config.Config
import database.JDBCHelper
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.qameta.allure.Attachment
import org.junit.platform.engine.TestExecutionResult
import org.junit.platform.launcher.TestExecutionListener
import org.junit.platform.launcher.TestIdentifier
import org.junit.platform.launcher.TestPlan

class GlobalTestListener : Controllers(), TestExecutionListener {

    private val authHelper = AuthHelper()

    override fun testPlanExecutionStarted(testPlan: TestPlan) {
        println("|------ Test Plan Started -----|")
        println("Initializing Configurations...").also { Config.get }
       // println("Initializing Selenide WebDriver...").also { Configuration.browser = DriverProvider::class.java.name }

        val tag = System.getProperty("TAG")
        if (tag == "frontend" || tag == "e2e") {
            println("Preparing products for Frontend/E2E...")
            ProductsHelper().ensureProductsWithWordExists("Coffee",5)
        }
    }

    override fun executionStarted(testIdentifier: TestIdentifier) {
        if (!testIdentifier.isTest) return
        println("|--- Test started: ${testIdentifier.displayName}")
    }

    override fun executionFinished(
        testIdentifier: TestIdentifier,
        testExecutionResult: TestExecutionResult
    ) {
        if (testIdentifier.isTest) println("Finished test: ${testIdentifier.displayName} Result: ${testExecutionResult.status}")
        if (testExecutionResult.status == TestExecutionResult.Status.FAILED && testIdentifier.displayName != "JUnit Jupiter") {
            attachScreenshot()
        }
    }

    override fun executionSkipped(testIdentifier: TestIdentifier, reason: String) {
        if (!testIdentifier.isTest) return
        println("|--- Test Ignored: ${testIdentifier.displayName} - Reason: $reason")
    }

    override fun testPlanExecutionFinished(testPlan: TestPlan) {
        println("|------ Test Plan Finished -----|")
        Selenide.closeWebDriver()
        println("|------ GarbageCollector -----|")

        val jdbs = JDBCHelper()

        GarbageCollector.user.forEach { id ->
            users.deleteUserById(token = authHelper.getDefaultToken(), id = id)
                .also { println("Deleted User: $id") }
        }

        GarbageCollector.orders.forEach { id ->
            val deletedOrders = jdbs.deleteOrderById(id).also { println("Deleted order: $id") }
            deletedOrders shouldBeGreaterThan 0
        }

        GarbageCollector.products.forEach { id ->
            products.deleteProductById(token = authHelper.getDefaultToken(), id = id).also {
                println("Deleted Product: $id")
            }
        }
    }

    @Attachment(value = "{name}", type = "image/png")
    fun attachScreenshot(name: String = "SCREENSHOT"): ByteArray? {
        return Screenshots.takeScreenShotAsFile()?.readBytes()
    }
}