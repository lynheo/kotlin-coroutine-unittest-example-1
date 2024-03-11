package net.lunapiece.kotlincoroutine.nonservice

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import net.lunapiece.kotlincoroutine.ClassForMock
import net.lunapiece.kotlincoroutine.ExampleNonService
import kotlin.random.Random

class ExampleNonServiceTest : BehaviorSpec({
    Given("NonService Class Test. Random Failed") {
        val classForMock = mockk<ClassForMock>()
        val exampleNonService = ExampleNonService(
            classForMock
        )

        justRun { classForMock.foo() }
        justRun { classForMock.boo() }

        When("Call Function") {
            val result = exampleNonService.launchGlobalScopeCall()
            Thread.sleep(Random.nextLong(0, 2000))

            Then("Success Boo & Foo") {
                result shouldBe 19860512
                verify(exactly = 1) { classForMock.foo() }
                verify(exactly = 1) { classForMock.boo() }
            }
        }
    }
})
