package net.lunapiece.kotlincoroutine.controller

import net.lunapiece.kotlincoroutine.ExampleServiceWithDispatcher
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ExampleController(
    private val exampleServiceWithDispatcher: ExampleServiceWithDispatcher
) {
    @GetMapping("/")
    fun test(): String {
        return "Test"
    }
}