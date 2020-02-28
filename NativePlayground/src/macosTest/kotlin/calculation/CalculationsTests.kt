package calculation

import kotlin.test.Test
import kotlin.test.assertEquals

class CalculationsTests {
    @Test
    fun `test that input of 0 results in 0`() {
        val expected = 0
        val sum = compute(intArrayOf(0), 0){a, b -> a + b}
        assertEquals(expected, sum)
    }

    @Test
    fun `test that an input of 0 with an initvalue of 1 results in 1`() {
        val expected = 1
        val sum = compute(intArrayOf(0), 1){a, b -> a + b}
        assertEquals(expected, sum)
    }

    @Test
    fun `test that an input of 0,1 with an initvalue of 0 results in `() {
        val expected = 1
        val sum = compute(intArrayOf(0,1), 0){a, b -> a + b}
        assertEquals(expected, sum)
    }

    @Test
    fun `test that an input of 0,1 with an initvalue of 1 results in 2`() {
        val expected = 2
        val sum = compute(intArrayOf(0,1), 1){a, b -> a + b}
        assertEquals(expected, sum)
    }

}