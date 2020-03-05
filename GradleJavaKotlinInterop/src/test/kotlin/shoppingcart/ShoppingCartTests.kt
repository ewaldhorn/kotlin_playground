package shoppingcart

import org.junit.*
import org.junit.Assert.assertEquals
import product.*
import java.math.*

class ShoppingCartTests {

    @Test
    fun `Step 1 - ensure we can add 5 items to the shopping cart with the correct balance`() {
        val shoppingCart = ShoppingCart()
        val product = Product("Dove Soap", BigDecimal("39.99"))

        for (i in 1..5) {
            shoppingCart.addItemToCart(product)
        }

        val expectedTotal = BigDecimal("199.95")
        val actualTotal = shoppingCart.calculateTotal()
        assertEquals(expectedTotal, actualTotal)

        val expectedItemCount = 5
        val actualItemCount = shoppingCart.cartItems.size
        assertEquals(expectedItemCount, actualItemCount)
    }

    @Test
    fun `Step 2 - ensure we can add 5 items then 3 more to the shopping cart with the correct balance`() {
        val shoppingCart = ShoppingCart()
        val product = Product("Dove Soap", BigDecimal("39.99"))

        for (i in 1..5) {
            shoppingCart.addItemToCart(product)
        }

        for (i in 1..3) {
            shoppingCart.addItemToCart(product)
        }

        val expectedTotal = BigDecimal("319.92")
        val actualTotal = shoppingCart.calculateTotal()
        assertEquals(expectedTotal, actualTotal)

        val expectedItemCount = 8
        val actualItemCount = shoppingCart.cartItems.size
        assertEquals(expectedItemCount, actualItemCount)
    }

    @Test
    fun `Step 3 - Add multiple products and query the count, calculate sales tax`() {
        val shoppingCart = ShoppingCart()
        val doveProduct = Product("Dove Soap", BigDecimal("39.99"))
        val axeProduct = Product("Axe Deo", BigDecimal("99.99"))

        shoppingCart.addItemToCart(doveProduct)
        shoppingCart.addItemToCart(doveProduct)

        shoppingCart.addItemToCart(axeProduct)
        shoppingCart.addItemToCart(axeProduct)

        val expectedDoveSoaps = 2
        val expectedAxeDeos = 2
        val expectedSalesTaxAmount = BigDecimal("35.00")
        val expectedTotalAmount = BigDecimal("314.96")

        val actualDoveSoaps = shoppingCart.getItemCountForProduct(doveProduct)
        val actualAxeDeos = shoppingCart.getItemCountForProduct(axeProduct)
        val actualSalesTaxAmount = shoppingCart.calculateSalesTax()
        val actualGrandTotalAmount = shoppingCart.calculateGrandTotal()

        assertEquals(expectedDoveSoaps, actualDoveSoaps)
        assertEquals(expectedAxeDeos, actualAxeDeos)

        assertEquals(expectedSalesTaxAmount, actualSalesTaxAmount)
        assertEquals(expectedTotalAmount, actualGrandTotalAmount)
    }
}