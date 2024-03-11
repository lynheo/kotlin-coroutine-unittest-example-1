package net.lunapiece.kotlincoroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ExampleNonServiceWithDispatcher(
    private val classForMock: ClassForMock,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    fun launchGlobalScopeCallWithDispatcher(): Long {
        GlobalScope.launch(dispatcher) {
            Thread.sleep(1000)
            classForMock.foo()
        }

        GlobalScope.launch(dispatcher) {
            Thread.sleep(1000)
            classForMock.boo()
        }

        return 19860512
    }
}