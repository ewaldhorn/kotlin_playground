package date

import org.junit.*
import org.junit.Assert.assertEquals

class DateTests {
    @Test
    fun `test that one date is equal to another`() {
        val expected = true
        val actual = MyDate(1, 1, 1) == MyDate(1, 1, 1)
        assertEquals(expected, actual)
    }

    @Test
    fun `test that one date is not equal to another`() {
        val expected = false
        val actual = MyDate(1, 1, 1) == MyDate(1, 1, 2)
        assertEquals(expected, actual)

    }
}