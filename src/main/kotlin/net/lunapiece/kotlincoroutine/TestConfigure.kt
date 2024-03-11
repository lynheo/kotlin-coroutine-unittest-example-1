package net.lunapiece.kotlincoroutine

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TestConfigure {
    @Bean
    fun classForMock(): ClassForMock {
        return ClassForMock()
    }
}