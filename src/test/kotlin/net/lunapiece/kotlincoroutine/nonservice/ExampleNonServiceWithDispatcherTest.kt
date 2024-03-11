package net.lunapiece.kotlincoroutine.nonservice

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import net.lunapiece.kotlincoroutine.ClassForMock
import net.lunapiece.kotlincoroutine.ExampleNonServiceWithDispatcher

class ExampleNonServiceWithDispatcherTest : BehaviorSpec({
    Given("NonService Class Test. Always Success") {
        val classForMock = mockk<ClassForMock>()
        val testDispatcher = StandardTestDispatcher()
        val exampleNonServiceWithDispatcher = ExampleNonServiceWithDispatcher(
            classForMock,
            testDispatcher
        )

        When("Call Function") {
            justRun { classForMock.foo() }
            justRun { classForMock.boo() }

            var result: Long? = null
            runTest(testDispatcher) {
                result = exampleNonServiceWithDispatcher.launchGlobalScopeCallWithDispatcher()
            }

            Then("Success Boo & Foo") {
                result shouldBe 19860512
                verify(exactly = 1) { classForMock.foo() }
                verify(exactly = 1) { classForMock.boo() }
            }
        }
    }
})
