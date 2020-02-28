package main

import kotlin.test.Test
import kotlin.test.assertTrue

class TheMainMacTests {
    @Test
    fun testHello() {
        assertTrue("Kotlin/Native" in hello())
    }
}