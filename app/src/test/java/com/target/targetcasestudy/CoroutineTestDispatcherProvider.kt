package com.target.targetcasestudy

import com.target.targetcasestudy.util.CoroutineDispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
object CoroutineTestDispatcherProvider : CoroutineDispatchers {
    val testDispatcher = UnconfinedTestDispatcher()
    override val main = testDispatcher
    override val io = testDispatcher
    override val default = testDispatcher
    override val immediate = testDispatcher
    override val computation = testDispatcher
}