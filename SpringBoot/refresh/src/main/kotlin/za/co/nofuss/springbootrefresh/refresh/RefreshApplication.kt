package za.co.nofuss.springbootrefresh.refresh

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RefreshApplication

fun main(args: Array<String>) {
	runApplication<RefreshApplication>(*args)
}
