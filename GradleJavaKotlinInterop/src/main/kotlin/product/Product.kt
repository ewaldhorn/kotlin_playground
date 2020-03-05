package product

import java.math.*

data class Product(val description: String, val unitPrice: BigDecimal, val salesTaxRate: BigDecimal = BigDecimal("12.5"))