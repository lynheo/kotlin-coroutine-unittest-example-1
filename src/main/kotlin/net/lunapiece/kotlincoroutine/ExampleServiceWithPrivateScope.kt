package net.lunapiece.kotlincoroutine

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.springframework.stereotype.Service

@Service
class ExampleServiceWithPrivateScope(
    private val classForMock: ClassForMock,
) {
    private lateinit var scope: CoroutineScope

    @PostConstruct
    fun setScope() {
        scope = CoroutineScope(Dispatchers.Default)
    }

    @PreDestroy
    fun cancelScope() {
        scope.cancel()
    }

    fun launchGlobalScopeCallWithScope(): Long {
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