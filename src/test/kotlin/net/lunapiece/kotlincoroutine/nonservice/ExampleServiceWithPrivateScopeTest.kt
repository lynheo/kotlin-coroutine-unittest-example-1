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
import net.lunapiece.kotlincoroutine.ExampleServiceWithPrivateScope

class ExampleServiceWithPrivateScopeTest : BehaviorSpec({
    val classForMock = mockk<ClassForMock>()
    val exampleServiceWithPrivateScope = ExampleServiceWithPrivateScope(
        classForMock,
    )

    val testDispatcher = StandardTestDispatcher()
    val testScope = TestScope(testDispatcher)

    beforeTest {
        val scopeField = ExampleServiceWithPrivateScope::class.java.getDeclaredField("scope")
        scopeField.isAccessible = true
        scopeField.set(exampleServiceWithPrivateScope, testScope)
    }

    afterTest {
        val scopeField = ExampleServiceWithPrivateScope::class.java.getDeclaredField("scope")
        scopeField.isAccessible = true
        scopeField.set(exampleServiceWithPrivateScope, null)
    }

    Given("Service Class Test. Always Success") {
        justRun { classForMock.foo() }
        justRun { classForMock.boo() }

        When("Call Function") {
            var result: Long? = null
            testScope.runTest {
                result = exampleServiceWithPrivateScope.launchGlobalScopeCallWithScope()
            }

            Then("Success Boo & Foo") {
                result shouldBe 19860512
                verify(exactly = 1) { classForMock.foo() }
                verify(exactly = 1) { classForMock.boo() }
            }
        }
    }
})
