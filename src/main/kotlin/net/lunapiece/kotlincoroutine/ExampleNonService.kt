package net.lunapiece.kotlincoroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ExampleNonService(
    private val classForMock: ClassForMock,
) {
    fun launchGlobalScopeCall(): Long {
        GlobalScope.launch(Dispatchers.IO) {
            Thread.sleep(1000)
            classForMock.foo()
        }

        GlobalScope.launch(Dispatchers.IO) {
            Thread.sleep(1000)
            classForMock.boo()
        }

        return 19860512
    }
}