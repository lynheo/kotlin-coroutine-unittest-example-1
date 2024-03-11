package net.lunapiece.kotlincoroutine.nonservice

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import net.lunapiece.kotlincoroutine.ClassForMock
import net.lunapiece.kotlincoroutine.ExampleNonServiceWithScope

class ExampleNonServiceWithScope : BehaviorSpec({
    Given("NonService Class Test. Always Success") {
        val classForMock = mockk<ClassForMock>()
        val testDispatcher = StandardTestDispatcher()
        val testScope = TestScope(testDispatcher)
        val exampleNonServiceWithScope = ExampleNonServiceWithScope(
            classForMock,
            testScope
        )

        justRun { classForMock.foo() }
        justRun { classForMock.boo() }

        When("Call Function") {
            var result: Long? = null
            testScope.runTest {
                result = exampleNonServiceWithScope.launchGlobalScopeCallWithScope()
            }

            Then("Success Boo & Foo") {
                result shouldBe 19860512
                verify(exactly = 1) { classForMock.foo() }
                verify(exactly = 1) { classForMock.boo() }
            }
        }
    }
})
