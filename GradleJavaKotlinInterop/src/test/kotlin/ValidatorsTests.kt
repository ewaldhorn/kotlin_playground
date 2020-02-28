import org.junit.Assert.assertEquals
import org.junit.Test

class ValidatorsTests {
    @Test
    fun `is GRUMPY a valid name - true`() {
        val expected = true
        val actual = isNameValid("GRUMPY")
        assertEquals(expected, actual)
    }
}