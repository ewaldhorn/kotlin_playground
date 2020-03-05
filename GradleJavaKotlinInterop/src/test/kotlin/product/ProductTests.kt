package product

import org.junit.*
import org.junit.Assert.assertEquals
import java.math.*

class ProductTests {

    @Test
    fun `ensure the product has a description`() {
        val product = Product("Potato chips", BigDecimal(0.0))
        val expected = true
        val actual = product.description.isNotBlank()
        assertEquals(expected, actual)
    }

    @Test
    fun `ensure the product has a valid unit price`() {
        val product = Product("", BigDecimal(0.05))
        val expected = true
        val actual = product.unitPrice.compareTo(BigDecimal(0.05))
        assertEquals(expected, actual)
    }
}