package shoppingcart

import product.*
import java.math.*

class ShoppingCart {
    val cartItems = ArrayList<Product>()

    // TODO Might need to return success or not
    fun addItemToCart(product: Product) {
        cartItems.add(product)
    }

    fun getItemCountForProduct(product: Product) : Int {
        return cartItems.filter { it.description == product.description }.size
    }

    fun calculateSalesTax(): BigDecimal {
        var salesTaxTotal = BigDecimal("0")

        cartItems.forEach {
            salesTaxTotal = salesTaxTotal.add(it.unitPrice.multiply(it.salesTaxRate.divide(BigDecimal("100"))))
        }

        return salesTaxTotal.setScale(2, RoundingMode.HALF_UP)
    }

    fun calculateTotal(): BigDecimal {
        var total = BigDecimal("0")

        cartItems.forEach {
            total = total.add(it.unitPrice)
        }

        return total
    }

    fun calculateGrandTotal(): BigDecimal {
        return calculateTotal().add(calculateSalesTax())
    }
}