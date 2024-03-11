package net.lunapiece.kotlincoroutine

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import kotlinx.coroutines.*
import org.springframework.stereotype.Service

@Service
class ExampleServiceWithDispatcher(
    private val classForMock: ClassForMock,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    private lateinit var scope: CoroutineScope

    @PostConstruct
    fun setScope() {
        scope = CoroutineScope(dispatcher)
    }

    @PreDestroy
    fun cancelScope() {
        scope.cancel()
    }

    fun launchGlobalScopeCallWithDispatcher(): Long {
        scope.launch {
            Thread.sleep(2000)
            classForMock.foo()
        }

        scope.launch {
            Thread.sleep(2000)
            classForMock.boo()
        }

        return 19860512
    }
}