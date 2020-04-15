package za.co.nofuss.springbootrefresh.refresh

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.*
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.*
import java.time.*
import java.util.concurrent.*

@SpringBootApplication
class RefreshApplication

fun main(args: Array<String>) {
	runApplication<RefreshApplication>(*args)
}

@RestController
class StockTickerRestController {

	@GetMapping(value = ["/stocks/{symbol}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
	fun prices(@PathVariable symbol: String): Flux<StockPrice> {
		return Flux.interval(Duration.ofSeconds(1)).map {
			StockPrice(symbol, randomStockPrice(), LocalDateTime.now())
		}
	}

	private fun randomStockPrice(): Double {
		return ThreadLocalRandom.current().nextDouble(100.0)
	}
}

data class StockPrice(val symbol: String, val price:Double, val time: LocalDateTime)
