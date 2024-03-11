package net.lunapiece.kotlincoroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExampleNonServiceWithScope(
    private val classForMock: ClassForMock,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
) {
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